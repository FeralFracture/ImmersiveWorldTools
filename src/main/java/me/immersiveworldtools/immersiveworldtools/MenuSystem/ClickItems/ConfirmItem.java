package me.immersiveworldtools.immersiveworldtools.MenuSystem.ClickItems;

import me.codetoolsapi.codetoolsapi.MenuSystem.MenuItems.ClickItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.ArrayList;

public class ConfirmItem extends ClickItem {
    public ConfirmItem() {
        super(Material.EMERALD);
    }

    @Override
    public String getName() {
        return ChatColor.GREEN + "Confirm";
    }

    @Override
    public ArrayList<String> getLore() {
        return null;
    }

    @Override
    protected void setPersistentData(PersistentDataContainer persistentDataContainer) {

    }
}
