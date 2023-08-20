package me.immersiveworldtools.immersiveworldtools.Listeners;

import me.immersiveworldtools.immersiveworldtools.ImmersiveWorldTools;
import me.immersiveworldtools.immersiveworldtools.MenuSystem.ImmersivePlayerMenuUtility;
import me.immersiveworldtools.immersiveworldtools.Utils.Classes.InteractionObject;
import me.immersiveworldtools.immersiveworldtools.Utils.HexColorsUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.*;

import static me.immersiveworldtools.immersiveworldtools.ImmersiveWorldTools.getPlayerMenuUtil;
import static me.immersiveworldtools.immersiveworldtools.Utils.InteractableManager.*;

public class ChatListener implements Listener {
    private static final HashMap<UUID, Boolean> typingInput = new HashMap<>();
    private static final HashMap<UUID, String> expectedType = new HashMap<>();

    public static void startChatInputSession(Player p, String type) {
        p.sendMessage(ChatColor.YELLOW + "Enter a new " + type + ":");
        typingInput.put(p.getUniqueId(), true);
        expectedType.put(p.getUniqueId(), type);
    }

    public static void endChatInputSession(Player p, String previousValue, String newValue) {

        p.sendMessage(ChatColor.GOLD + "You changed the "
                + ChatColor.UNDERLINE + inputType(p)
                + ChatColor.GOLD + " of the \"" + ChatColor.BLUE + getPlayerMenuUtil(p).selectedInteractable
                + ChatColor.GOLD + "\" from: \"" + ChatColor.RESET
                + previousValue + ChatColor.GOLD + "\" to \"" + ChatColor.RESET + newValue + ChatColor.GOLD + "\"");
        typingInput.put(p.getUniqueId(), false);
        expectedType.put(p.getUniqueId(), "none");
        getPlayerMenuUtil(p).selectedInteractable = "";
    }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {
        if (ifTypingInput(e.getPlayer()) && !inputType(e.getPlayer()).equalsIgnoreCase("none")) {
            e.setCancelled(true);
        }
        Bukkit.getScheduler().runTask(ImmersiveWorldTools.getPlugin(), () -> {
            Player p = e.getPlayer();
            if (ifTypingInput(p) && !inputType(p).equalsIgnoreCase("none")) {
                ImmersivePlayerMenuUtility pUtil = getPlayerMenuUtil(p);
                List<String> args = Arrays.stream(e.getMessage().split(" ")).toList();
                InteractionObject oldIO = interactablesList.stream().filter(i -> i.interactionName.equals(pUtil.selectedInteractable)).findFirst().get();
                StringBuilder out = new StringBuilder();
                switch (inputType(p)) {
                    case "name": {
                        for (int i = 0; i < args.size(); i++) {
                            out.append(args.get(i));
                            if (i + 1 < args.size()) {
                                out.append("_");
                            }
                        }
                        InteractionObject newIO = oldIO.cloneObject();
                        newIO.interactionName = out.toString();
                        String oldName = oldIO.interactionName;
                        String newName = newIO.interactionName;
                        removeInteraction(oldIO.interactionName);
                        addInteraction(p, newIO);
                        endChatInputSession(p, oldName, newName);
                        break;
                    }
                    case "message": {
                        for (int i = 0; i < args.size(); i++) {
                            out.append(args.get(i));
                            if (i + 1 < args.size()) {
                                out.append(" ");
                            }
                        }
                        String old = viewMessage(oldIO);

                        updateInteraction(oldIO.interactionName, "message", out.toString());
                        endChatInputSession(p, old, viewMessage(oldIO));
                        break;
                    }
                    case "hexColor": {
                        if (args.get(0).startsWith("#") && args.get(0).length() == 7) {
                            String oldColor = HexColorsUtils.hexColor(oldIO.hexColor) + oldIO.hexColor;
                            String newColor = HexColorsUtils.hexColor(args.get(0)) + args.get(0);
                            updateInteraction(oldIO.interactionName, "hexColor", args.get(0));
                            endChatInputSession(p, oldColor, newColor);
                        } else {
                            p.sendMessage(ChatColor.RED + "Please enter a valid hex color code.");
                            pUtil.selectedInteractable = "";
                        }
                        break;
                    }
                    case "position": {
                        if (args.size() < 3) {
                            invalidValues(p);
                        } else {
                            try {
                                double oldX = oldIO.x - 0.5F;
                                double oldY = oldIO.y;
                                double oldZ = oldIO.z - 0.5F;
                                double x = Double.parseDouble(args.get(0));
                                double y = Double.parseDouble(args.get(1));
                                double z = Double.parseDouble(args.get(2));
                                oldIO.x = (float) x + 0.5F;
                                oldIO.y = (float) y;
                                oldIO.z = (float) z + 0.5F;
                                updateInteraction(oldIO, "position");

                                String outX = String.format("%.3f", oldX);
                                String outY = String.format("%.3f", oldY);
                                String outZ = String.format("%.3f", oldZ);
                                outX = outX.replaceAll("\\.?0*$", "");
                                outY = outY.replaceAll("\\.?0*$", "");
                                outZ = outZ.replaceAll("\\.?0*$", "");
                                // Remove trailing zeros

                                String oldPos = "X: " + outX + " Y: " + outY + " Z: " + outZ;
                                String newPos = "X: " + x + " Y: " + y + " Z: " + z;
                                endChatInputSession(p, oldPos, newPos);
                            } catch (Exception ex) {
                                invalidValues(p);
                            }
                        }
                        break;
                    }
                    case "size": {
                        if (args.size() < 2) {
                            invalidValues(p);
                            break;
                        } else {
                            try {
                                double oldHeight = oldIO.height;
                                double oldWidth = oldIO.width;
                                double height = Double.parseDouble(args.get(0));
                                double width = Double.parseDouble(args.get(1));
                                oldIO.height = (float) height;
                                oldIO.width = (float) width;
                                updateInteraction(oldIO, "size");

                                String formattedValue = String.format("%.3f", oldHeight);
                                formattedValue = formattedValue.replaceAll("\\.?0*$", ""); // Remove trailing zeros

                                String formattedValue2 = String.format("%.3f", oldWidth);
                                formattedValue2 = formattedValue2.replaceAll("\\.?0*$", ""); // Remove trailing zeros

                                String oldSize = "Height: " + formattedValue + " Width: " + formattedValue2;
                                String newSize = "Height: " + height + " Width: " + width;
                                endChatInputSession(p, oldSize, newSize);
                            } catch (Exception ex) {
                                invalidValues(p);
                            }
                        }
                        break;
                    }
                    case "blockType": {
                        Material block;
                        try {
                            block = Material.valueOf(args.get(0).toUpperCase());
                        } catch (Exception ex) {
                            invalidValues(p);
                            break;
                        }
                        if (block.isBlock()) {
                            String oldBlock = oldIO.blockType.name();
                            String newBlock = block.toString();
                            oldIO.blockType = block;
                            updateInteraction(oldIO, "blockType");
                            endChatInputSession(p, oldBlock, newBlock);
                        } else {
                            invalidValues(p);
                        }
                        break;
                    }
                    default: {
                        p.sendMessage(ChatColor.RED + "Not sure how you broke it but you did somehow.");
                        typingInput.put(p.getUniqueId(), false);
                        expectedType.put(p.getUniqueId(), "none");
                        getPlayerMenuUtil(p).selectedInteractable = "";
                    }
                }
            }
        });
    }

    private boolean ifTypingInput(Player p) {
        if (typingInput.containsKey(p.getUniqueId())) {
            return typingInput.get(p.getUniqueId());
        } else {
            typingInput.put(p.getUniqueId(), false);
            return false;
        }
    }

    private void invalidValues(Player p) {
        p.sendMessage(ChatColor.RED + "Invalid Value(s).");
        typingInput.put(p.getUniqueId(), false);
        expectedType.put(p.getUniqueId(), "none");
        getPlayerMenuUtil(p).selectedInteractable = "";

    }

    private static String inputType(Player p) {
        if (expectedType.containsKey(p.getUniqueId())) {
            return expectedType.get(p.getUniqueId());
        } else {
            expectedType.put(p.getUniqueId(), "none");
            return "none";
        }
    }
}
