package me.immersiveworldtools.immersiveworldtools.Utils;

import me.immersiveworldtools.immersiveworldtools.Files.StoredFlickerBlocks;
import me.immersiveworldtools.immersiveworldtools.Files.StoredInteractions;
import me.immersiveworldtools.immersiveworldtools.ImmersiveWorldTools;
import me.immersiveworldtools.immersiveworldtools.Tasks.BlockFlickerTask;
import me.immersiveworldtools.immersiveworldtools.Tasks.InteractableSparkleTask;
import me.immersiveworldtools.immersiveworldtools.Utils.Classes.FlickerBlock;
import me.immersiveworldtools.immersiveworldtools.Utils.Classes.InteractionObject;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.UUID;

import static me.immersiveworldtools.immersiveworldtools.ImmersiveWorldTools.storedBlocks;
import static me.immersiveworldtools.immersiveworldtools.ImmersiveWorldTools.storedInteractions;
import static me.immersiveworldtools.immersiveworldtools.Utils.HexColorsUtils.hexColor;

public class InteractableManager {
    public static ArrayList<InteractionObject> interactablesList = new ArrayList<>();
    public static ArrayList<InteractableSparkleTask> interactableSparkleTasks = new ArrayList<>();

    public static void readConfig() {
        storedInteractions = new StoredInteractions();
        FileConfiguration fconfig = storedInteractions.getConfig();
        ConfigurationSection interactionDataSection = fconfig.getConfigurationSection("");
        if (interactionDataSection != null) {
            for (String key : interactionDataSection.getKeys(false)) {
                ConfigurationSection interactionSection = interactionDataSection.getConfigurationSection(key);
                if (interactionSection != null) {
                    InteractionObject iObject = new InteractionObject(key);
                    iObject.message = interactionSection.getString("message");
                    iObject.hexColor = interactionSection.getString("hexColor");
                    interactablesList.add(iObject);
                }
            }
        }
    }

    public static void addInteraction(Player p, float x, float y, float z, float width, float height, String message) {
        Interaction interactionBox = (Interaction) p.getWorld().spawnEntity(new Location(p.getWorld(), x + 0.5F, y, z + 0.5F), EntityType.INTERACTION);
        interactionBox.setInteractionHeight(height);
        interactionBox.setInteractionWidth(width);
        InteractionObject o = new InteractionObject(interactionBox, message);
        storedInteractions.addInteractableToConfig(o);
        interactablesList.add(o);
        reload();
    }

    public static void removeInteraction(String uuid) {
        InteractionObject IObject = interactablesList.stream().filter(e -> e.uuid.equals(uuid)).findFirst().get();
        IObject.interaction.remove();
        IObject.stopSparkle();
        BukkitRunnable sa = new BukkitRunnable() {
            @Override
            public void run() {
                interactablesList.remove(IObject);
                storedInteractions.removeInteractableEntry(uuid);
            }
        };
        sa.runTaskLater(ImmersiveWorldTools.getPlugin(), 8L);

    }

    public static void reload() {
        for (InteractionObject o : interactablesList) {
            o.stopSparkle();
        }
        interactablesList.clear();
        BukkitRunnable s = new BukkitRunnable() {
            @Override
            public void run() {
                readConfig();
            }
        };
        s.runTaskLater(ImmersiveWorldTools.getPlugin(), 10L);
    }

    public static void readMessage(Player p, InteractionObject o) {
        String outMessage = ChatColor.translateAlternateColorCodes('&', o.message);
        outMessage = outMessage.replaceAll("&h", hexColor(o.hexColor));
        p.sendMessage(outMessage);
        System.out.println(outMessage);
    }
}
