package me.immersiveworldtools.immersiveworldtools.MenuSystem.ClickItems;

import me.codetoolsapi.codetoolsapi.MenuSystem.MenuItems.ClickItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.ArrayList;

public class ChangeMessageBook extends ClickItem {
    public ChangeMessageBook() {
        super(Material.BOOK);
    }

    @Override
    public String getName() {
        return ChatColor.DARK_AQUA + "Change interaction message.";
    }

    @Override
    public ArrayList<String> getLore() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GOLD + "Allows you to change the message when interacting.");
        lore.add(ChatColor.GOLD + "use default mc color codes for extra changes.");
        lore.add(ChatColor.GOLD + "use " + ChatColor.AQUA + "" +ChatColor.ITALIC + "'&h' " + ChatColor.GOLD + "to use the custom hex color.");
        return lore;
    }

    @Override
    protected void setPersistentData(PersistentDataContainer persistentDataContainer) {

    }
}
