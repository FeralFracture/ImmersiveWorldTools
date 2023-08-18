package me.immersiveworldtools.immersiveworldtools;

import me.immersiveworldtools.immersiveworldtools.Commands.CommandManager;
import me.immersiveworldtools.immersiveworldtools.Files.StoredBlocks;
import org.bukkit.plugin.java.JavaPlugin;

import static me.immersiveworldtools.immersiveworldtools.Utils.BlockFlickerManager.readConfig;

public final class ImmersiveWorldTools extends JavaPlugin {

    private static ImmersiveWorldTools plugin;
    public  static StoredBlocks storedBlocks;
    @Override
    public void onEnable() {
        plugin = this;
        storedBlocks = new StoredBlocks();
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
        this.saveConfig();
        readConfig();
        getCommand(new CommandManager().command_prefix()).setExecutor(new CommandManager());
    }

    public static ImmersiveWorldTools getPlugin() {
        return plugin;
    }
}
