package me.immersiveworldtools.immersiveworldtools.MenuSystem.ClickItems;

import me.codetoolsapi.codetoolsapi.MenuSystem.MenuItems.ClickItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.ArrayList;

public class ChangeCoordinate extends ClickItem {
    public ChangeCoordinate() {
        super(Material.DAYLIGHT_DETECTOR);
    }

    @Override
    public String getName() {
        return ChatColor.DARK_GREEN + "Change <XYZ> coordinates.";
    }

    @Override
    public ArrayList<String> getLore() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GOLD + "Supply a new <X | Y | Z> coordinate.");
        return lore;
    }

    @Override
    protected void setPersistentData(PersistentDataContainer persistentDataContainer) {

    }
}
