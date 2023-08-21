package me.immersiveworldtools.immersiveworldtools.MenuSystem.Menus;

import me.codetoolsapi.codetoolsapi.MenuSystem.Menu;
import me.codetoolsapi.codetoolsapi.MenuSystem.MenuItems.CancelOrClose;
import me.codetoolsapi.codetoolsapi.MenuSystem.PlayerMenuUtility;
import me.immersiveworldtools.immersiveworldtools.MenuSystem.ClickItems.*;
import me.immersiveworldtools.immersiveworldtools.MenuSystem.ImmersivePlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import static me.codetoolsapi.codetoolsapi.PersistentDataUtils.hasPersistentData;
import static me.immersiveworldtools.immersiveworldtools.Listeners.ChatListener.startChatInputSession;
import static me.immersiveworldtools.immersiveworldtools.Utils.InteractableManager.interactablesList;

public class IndividualInteractableConfigMenu extends Menu {
    public IndividualInteractableConfigMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return ChatColor.DARK_AQUA + ((ImmersivePlayerMenuUtility) playerMenuUtility).selectedInteractable;
    }

    @Override
    public boolean autoCancel() {
        return true;
    }

    @Override
    public int getRows() {
        return 1;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        if (e.getRawSlot() < 9) {
            Player p = (Player) e.getWhoClicked();
            if (e.getCurrentItem().getType().equals(Material.TNT)) {
                new ConfirmDeleteMenu(playerMenuUtility).open();
            } else if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
                ((ImmersivePlayerMenuUtility) playerMenuUtility).selectedInteractable = "";
                new InteractableMenus(playerMenuUtility).open();
            } else if (e.getCurrentItem().getType().equals(Material.NAME_TAG)) {
                startChatInputSession(p, "name");
                e.getWhoClicked().closeInventory();
            } else if (e.getCurrentItem().getType().equals(Material.BOOK)) {
                startChatInputSession(p, "message");
                e.getWhoClicked().closeInventory();
            } else if (e.getCurrentItem().getType().equals(Material.ORANGE_DYE)) {
                startChatInputSession(p, "hexColor");
                e.getWhoClicked().closeInventory();
            } else if (e.getCurrentItem().getType().equals(Material.DAYLIGHT_DETECTOR)) {
                startChatInputSession(p, "position");
                e.getWhoClicked().closeInventory();
            } else if (e.getCurrentItem().getType().equals(Material.ARMOR_STAND)) {
                startChatInputSession(p, "size");
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta() != null &&
                    hasPersistentData(e.getCurrentItem().getItemMeta().getPersistentDataContainer(), "blockIcon")) {
                startChatInputSession(p, "blockType");
                e.getWhoClicked().closeInventory();
            }
        }
    }

    @Override
    public void setMenuItems() {
        inventory.setItem(0, new ChangeNameNameTag());
        inventory.setItem(1, new ChangeMessageBook());
        inventory.setItem(2, new ChangeColorDye());
        inventory.setItem(3, new BlockType(interactablesList.stream().filter(i -> i.interactionName.equalsIgnoreCase(
                        ((ImmersivePlayerMenuUtility)playerMenuUtility).selectedInteractable))
                        .findFirst().get().blockType));
        inventory.setItem(4, new ChangeCoordinate());
        inventory.setItem(5, new ChangeHeightWidth());
        inventory.setItem(7, new DeleteInteractable());
        inventory.setItem(8, new CancelOrClose(ChatColor.RED + "Back"));
    }

    @Override
    public boolean fillerGlass() {
        return true;
    }
}
