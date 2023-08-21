package me.immersiveworldtools.immersiveworldtools.MenuSystem.ClickItems;

import me.codetoolsapi.codetoolsapi.MenuSystem.MenuItems.ClickItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.ArrayList;

import static me.codetoolsapi.codetoolsapi.PersistentDataUtils.addPersistentData;

public class BlockType extends ClickItem {
    public BlockType(Material material) {
        super(material);
    }

    @Override
    public String getName() {
        return ChatColor.DARK_PURPLE + "Change Listed Type";
    }

    @Override
    public ArrayList<String> getLore() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GOLD + "Changes the block shown in the config menu.");
        lore.add(ChatColor.RED + "[NOTE] " + ChatColor.GOLD + "Does not actually change any blocks in the world.");
        return lore;
    }

    @Override
    protected void setPersistentData(PersistentDataContainer persistentDataContainer) {
        addPersistentData(persistentDataContainer, "blockIcon", true);
    }
}
