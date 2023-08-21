package me.immersiveworldtools.immersiveworldtools.MenuSystem.ClickItems;

import me.codetoolsapi.codetoolsapi.MenuSystem.MenuItems.ClickItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.ArrayList;

public class ChangeColorDye extends ClickItem {
    public ChangeColorDye() {
        super(Material.ORANGE_DYE);
    }

    @Override
    public String getName() {
        return ChatColor.LIGHT_PURPLE + "Change hex color.";
    }

    @Override
    public ArrayList<String> getLore() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GOLD + "Supply a new hex color.");
        lore.add(ChatColor.RED + "[Warning]" + ChatColor.GOLD + "" + ChatColor.ITALIC + " Incorrect codes will probably cause coloring issues.");
        return lore;
    }

    @Override
    protected void setPersistentData(PersistentDataContainer persistentDataContainer) {

    }
}
