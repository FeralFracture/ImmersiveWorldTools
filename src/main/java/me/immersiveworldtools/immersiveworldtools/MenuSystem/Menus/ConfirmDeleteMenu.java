package me.immersiveworldtools.immersiveworldtools.MenuSystem.Menus;

import me.codetoolsapi.codetoolsapi.MenuSystem.Menu;
import me.codetoolsapi.codetoolsapi.MenuSystem.MenuItems.CancelOrClose;
import me.codetoolsapi.codetoolsapi.MenuSystem.PlayerMenuUtility;
import me.immersiveworldtools.immersiveworldtools.MenuSystem.ClickItems.ConfirmItem;
import me.immersiveworldtools.immersiveworldtools.MenuSystem.ImmersivePlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import static me.immersiveworldtools.immersiveworldtools.Utils.InteractableManager.removeInteraction;

public class ConfirmDeleteMenu extends Menu {
    public ConfirmDeleteMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return ChatColor.DARK_RED + "Delete: " + ChatColor.BOLD + ((ImmersivePlayerMenuUtility) playerMenuUtility).selectedInteractable + ChatColor.DARK_RED + "?";
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
            if(e.getCurrentItem().getType().equals(Material.EMERALD)) {
                removeInteraction(((ImmersivePlayerMenuUtility)playerMenuUtility).selectedInteractable);
                e.getWhoClicked().sendMessage(ChatColor.RED + "Deleted: " + ChatColor.BOLD + ((ImmersivePlayerMenuUtility)playerMenuUtility).selectedInteractable);
                ((ImmersivePlayerMenuUtility)playerMenuUtility).selectedInteractable = "";
                e.getWhoClicked().closeInventory();
            } else if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
                new IndividualInteractableConfigMenu(playerMenuUtility).open();
            }
        }
    }

    @Override
    public void setMenuItems() {
        inventory.setItem(0, new ConfirmItem());
        inventory.setItem(8, new CancelOrClose(ChatColor.RED + "Cancel"));
    }

    @Override
    public boolean fillerGlass() {
        return true;
    }
}
