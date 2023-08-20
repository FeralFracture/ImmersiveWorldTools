package me.immersiveworldtools.immersiveworldtools.MenuSystem.Menus;

import me.codetoolsapi.codetoolsapi.MenuSystem.PaginatedMenu;
import me.codetoolsapi.codetoolsapi.MenuSystem.PlayerMenuUtility;
import me.immersiveworldtools.immersiveworldtools.MenuSystem.ImmersivePlayerMenuUtility;
import me.immersiveworldtools.immersiveworldtools.Utils.Classes.InteractionObject;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static me.codetoolsapi.codetoolsapi.PersistentDataUtils.addPersistentData;
import static me.immersiveworldtools.immersiveworldtools.Utils.HexColorsUtils.hexColor;
import static me.immersiveworldtools.immersiveworldtools.Utils.InteractableManager.interactablesList;

public class InteractableMenus extends PaginatedMenu {
    public InteractableMenus(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);

    }

    @Override
    public String getMenuName() {
        return ChatColor.BLUE + "Interactable Objects";
    }

    @Override
    public boolean autoCancel() {
        return true;
    }

    @Override
    public int getRows() {
        return 6;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
            //close inventory
            p.closeInventory();
        }
        else if (e.getCurrentItem().getType().equals(Material.DARK_OAK_BUTTON)) {
            if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Left")) {
                if (page == 0) {
                    p.sendMessage(ChatColor.GRAY + "Already on page 0.");
                } else {
                    page = page - 1;
                    super.open();
                }
            }
            else if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Right")) {
                if (!((index + 1) >= interactablesList.size())) {
                    page = page + 1;
                    super.open();
                } else {
                    p.sendMessage(ChatColor.GRAY + "Final page.");
                }
            }
        }
        else if(e.getRawSlot() < 45 && e.getCurrentItem() != null && !e.getCurrentItem().getItemMeta().getDisplayName().isEmpty()){
            ((ImmersivePlayerMenuUtility) playerMenuUtility).selectedInteractable = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
            new IndividualInteractableConfigMenu(playerMenuUtility).open();
        }
    }


    @Override
    public void setMenuItems() {
        if (interactablesList != null && !interactablesList.isEmpty()) {
            addMenuBorder();
            for (int i = 0; i < getMaxItemsPerPage(); i++) {
                index = getMaxItemsPerPage() * page + i;
                if (index >= interactablesList.size()) break;
                if (interactablesList.get(index) != null) {
                    ///////////////////////////

                    //Create an item from our collection and place it into the inventory
                    ItemStack interactBlockSource = new ItemStack(interactablesList.get(index).blockType, 1);
                    ItemMeta blockMeta = interactBlockSource.getItemMeta();
                    blockMeta.setDisplayName(ChatColor.BLUE + interactablesList.get(index).interactionName);
                    addPersistentData(blockMeta.getPersistentDataContainer(), "uuid", interactablesList.get(index).uuid);
                    ArrayList<String> blockLore = new ArrayList<>();
                    blockLore.add(ChatColor.DARK_GREEN + "XYZ: " + ChatColor.YELLOW + (interactablesList.get(index).x - 0.5F)+ " | " + interactablesList.get(index).y + " | " +
                            (interactablesList.get(index).z-0.5F));
                    blockLore.add(ChatColor.DARK_GREEN + "Height/Width: " + ChatColor.YELLOW + interactablesList.get(index).height + " | " + interactablesList.get(index).width);

                    String outMessage = ChatColor.translateAlternateColorCodes('&', interactablesList.get(index).message);
                    outMessage = outMessage.replaceAll("&h", hexColor(interactablesList.get(index).hexColor));
                    blockLore.add(ChatColor.DARK_GREEN + "Interaction Message: \"" + ChatColor.WHITE + outMessage + ChatColor.DARK_GREEN + "\"");
                    blockLore.add(ChatColor.DARK_GREEN + "Message Hex Color: " + hexColor(interactablesList.get(index).hexColor) + interactablesList.get(index).hexColor);
                    blockMeta.setLore(blockLore);

                    interactBlockSource.setItemMeta(blockMeta);
                    inventory.addItem(interactBlockSource);
                    ////////////////////////
                }
            }
        }

    }

    @Override
    public boolean fillerGlass() {
        return false;
    }
}
