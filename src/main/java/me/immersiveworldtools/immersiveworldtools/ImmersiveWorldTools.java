package me.immersiveworldtools.immersiveworldtools;

import me.immersiveworldtools.immersiveworldtools.Commands.CommandManager;
import me.immersiveworldtools.immersiveworldtools.Files.StoredFlickerBlocks;
import me.immersiveworldtools.immersiveworldtools.Files.StoredInteractions;
import me.immersiveworldtools.immersiveworldtools.Listeners.InteractionListener;
import me.immersiveworldtools.immersiveworldtools.Utils.BlockFlickerManager;
import me.immersiveworldtools.immersiveworldtools.Utils.InteractableManager;
import org.bukkit.plugin.java.JavaPlugin;


public final class ImmersiveWorldTools extends JavaPlugin {

    private static ImmersiveWorldTools plugin;
    public  static StoredFlickerBlocks storedBlocks;
    public static StoredInteractions storedInteractions;
    @Override
    public void onEnable() {
        plugin = this;
        storedBlocks = new StoredFlickerBlocks();
        storedInteractions = new StoredInteractions();
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
        this.saveConfig();
        BlockFlickerManager.readConfig();
        InteractableManager.readConfig();
        getCommand(new CommandManager().command_prefix()).setExecutor(new CommandManager());
        getServer().getPluginManager().registerEvents(new InteractionListener(), this);
    }

    public static ImmersiveWorldTools getPlugin() {
        return plugin;
    }
}
