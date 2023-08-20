package me.immersiveworldtools.immersiveworldtools.MenuSystem.ClickItems;

import me.codetoolsapi.codetoolsapi.MenuSystem.MenuItems.ClickItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.persistence.PersistentDataContainer;
import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DeleteInteractable extends ClickItem {
    public DeleteInteractable() {
        super(Material.TNT);
    }

    @Override
    public String getName() {
        return ChatColor.DARK_RED + "Delete Interactable.";
    }

    @Override
    public ArrayList<String> getLore() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GOLD + "Delete interactable entirely from list and world.");
        return lore;
    }

    @Override
    protected void setPersistentData(PersistentDataContainer persistentDataContainer) {

    }
}
