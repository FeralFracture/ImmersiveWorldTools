package me.immersiveworldtools.immersiveworldtools.Tasks;

import me.immersiveworldtools.immersiveworldtools.ImmersiveWorldTools;
import me.immersiveworldtools.immersiveworldtools.Utils.Classes.FlickerBlock;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftRedstoneLamp;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;
import java.util.UUID;


public class BlockFlickerTask extends BukkitRunnable {
    private Block lamp = null;
    private Player activator = null;
    private BlockState lampState = null;
    private CraftRedstoneLamp lampData = null;
    private Random r = new Random();
    public FlickerBlock flickerBlock = new FlickerBlock();
    private int maxTicks;
    public BlockFlickerTask(World w, UUID id, int X, int Y, int Z, int maxTicks) {
        this.lamp = w.getBlockAt(X, Y, Z);
        lamp.setType(Material.REDSTONE_LAMP);
        this.lampState = lamp.getState();
        this.lampData = (CraftRedstoneLamp) lampState.getBlockData();
        this.maxTicks = maxTicks;
        this.flickerBlock.x = X;
        this.flickerBlock.y = Y;
        this.flickerBlock.z = Z;
        this.flickerBlock.blockType = "lamp";
        this.flickerBlock.maxTick = maxTicks;
        this.flickerBlock.uuidKey = id.toString();
    }
    @Override
    public void run() {
        lampData.setLit(!lampData.isLit());
        int delay = r.nextInt(maxTicks - 0 + 1) + 0;
        new BukkitRunnable() {
            @Override
            public void run() {
                lamp.setBlockData(lampData);
            }
        }.runTaskLater(ImmersiveWorldTools.getPlugin(), delay);
    }
}
