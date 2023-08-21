package me.immersiveworldtools.immersiveworldtools.MenuSystem.ClickItems;

import me.codetoolsapi.codetoolsapi.MenuSystem.MenuItems.ClickItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.ArrayList;

public class ChangeNameNameTag extends ClickItem {
    public ChangeNameNameTag() {
        super(Material.NAME_TAG);
    }

    @Override
    public String getName() {
        return ChatColor.GRAY + "Change Name";
    }

    @Override
    public ArrayList<String> getLore() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GOLD + "There isn't much reason to change this.");
        lore.add(ChatColor.GOLD + "This is only seen from the config menu.");
        return lore;
    }

    @Override
    protected void setPersistentData(PersistentDataContainer persistentDataContainer) {

    }
}
