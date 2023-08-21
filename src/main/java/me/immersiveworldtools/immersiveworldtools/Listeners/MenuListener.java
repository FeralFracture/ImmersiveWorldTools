package me.immersiveworldtools.immersiveworldtools.Listeners;

import me.codetoolsapi.codetoolsapi.MenuSystem.Menu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {
    @EventHandler
    public void menuClick(InventoryClickEvent e) {
        if(e.getInventory() instanceof Menu m) {
            m.handleMenu(e);
        }
    }
}
