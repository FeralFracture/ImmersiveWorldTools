package me.immersiveworldtools.immersiveworldtools.Utils;

import me.immersiveworldtools.immersiveworldtools.Files.StoredBlocks;
import me.immersiveworldtools.immersiveworldtools.ImmersiveWorldTools;
import me.immersiveworldtools.immersiveworldtools.Tasks.BlockFlickerTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static me.immersiveworldtools.immersiveworldtools.ImmersiveWorldTools.storedBlocks;

public class BlockFlickerManager {
    public static ArrayList<BlockFlickerTask> flickeringBlockTasks = new ArrayList<>();

    public static void readConfig() {
        storedBlocks = new StoredBlocks();
        FileConfiguration fconfig = ImmersiveWorldTools.storedBlocks.getStoredBlocks();
        ConfigurationSection blockDataSection = fconfig.getConfigurationSection("");
        if (blockDataSection != null) {
            for (String key : blockDataSection.getKeys(false)) {
                ConfigurationSection blockSection = blockDataSection.getConfigurationSection(key);
                if (blockSection != null) {
                    FlickerBlock FBlock = new FlickerBlock();
                    FBlock.blockType = blockSection.getString("blockType");
                    FBlock.x = blockSection.getInt("x");
                    FBlock.y = blockSection.getInt("y");
                    FBlock.z = blockSection.getInt("z");
                    FBlock.worldName = blockSection.getString("worldName");
                    FBlock.uuidKey = key;
                    FBlock.maxTick = blockSection.getInt("maxTick");

                    //do stuff
                    World w = Bukkit.getWorlds().get(0);
                    BlockFlickerTask task = new BlockFlickerTask(Bukkit.getWorld(FBlock.worldName), UUID.fromString(FBlock.uuidKey), FBlock.x, FBlock.y, FBlock.z, FBlock.maxTick);
                    task.runTaskTimer(ImmersiveWorldTools.getPlugin(), 0L, 5L);
                    flickeringBlockTasks.add(task);
                }
            }
        }
    }

    public static void addFlickerBlock(Player p, Block target, int maxTicks) {
        if (flickeringBlockTasks.stream().noneMatch(fBlockTask ->
                fBlockTask.flickerBlock.x == target.getX() &&
                        fBlockTask.flickerBlock.y == target.getY() &&
                        fBlockTask.flickerBlock.z == target.getZ())) {
            storedBlocks.addFlickerLightLocation(target.getLocation(), maxTicks);
            BlockFlickerTask task;
            task = new BlockFlickerTask(p.getWorld(), UUID.randomUUID(), target.getLocation().getBlockX(), target.getLocation().getBlockY(), target.getLocation().getBlockZ(), maxTicks);
            task.runTaskTimer(ImmersiveWorldTools.getPlugin(), 0L, 5L);
            flickeringBlockTasks.add(task);
            p.sendMessage(ChatColor.GREEN + "Flicker block created.");
        } else {
            p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Selected block is already a flicker block.");
        }
    }

    public static void removeFlickerBlock(Player p, Block target) {
        if (flickeringBlockTasks.stream().anyMatch(fBlockTask ->
                fBlockTask.flickerBlock.x == target.getX() &&
                        fBlockTask.flickerBlock.y == target.getY() &&
                        fBlockTask.flickerBlock.z == target.getZ())) {
            BlockFlickerTask task = flickeringBlockTasks.stream().filter(fBlockTask ->
                    fBlockTask.flickerBlock.x == target.getX() &&
                            fBlockTask.flickerBlock.y == target.getY() &&
                            fBlockTask.flickerBlock.z == target.getZ()).findFirst().get();
            task.cancel();
            storedBlocks.removeFlickerEntry(task.flickerBlock.uuidKey);
            p.sendMessage(ChatColor.GREEN + "Flicker block removed.");
        }
        else {
            p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Selected block is not a flicker block.");
        }
    }

    public static void reload() {
        for(BlockFlickerTask t : flickeringBlockTasks) {
            t.cancel();
        }
        flickeringBlockTasks.clear();
        BukkitRunnable s = new BukkitRunnable() {
            @Override
            public void run() {
                readConfig();
            }
        };
        s.runTaskLater(ImmersiveWorldTools.getPlugin(), 20L);

    }
}
