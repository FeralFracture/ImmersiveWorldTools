package me.immersiveworldtools.immersiveworldtools;

import me.immersiveworldtools.immersiveworldtools.Commands.CommandManager;
import me.immersiveworldtools.immersiveworldtools.Files.StoredFlickerBlocks;
import me.immersiveworldtools.immersiveworldtools.Files.StoredInteractions;
import me.immersiveworldtools.immersiveworldtools.Listeners.InteractionListener;
import me.immersiveworldtools.immersiveworldtools.Listeners.MenuListener;
import me.immersiveworldtools.immersiveworldtools.MenuSystem.ImmersivePlayerMenuUtility;
import me.immersiveworldtools.immersiveworldtools.Utils.BlockFlickerManager;
import me.immersiveworldtools.immersiveworldtools.Utils.InteractableManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

import static me.immersiveworldtools.immersiveworldtools.Utils.InteractableManager.clearInteractions;


public final class ImmersiveWorldTools extends JavaPlugin {

    private static ImmersiveWorldTools plugin;
    public  static StoredFlickerBlocks storedBlocks;
    public static StoredInteractions storedInteractions;
    private static HashMap<UUID, ImmersivePlayerMenuUtility> menuUtilMap = new HashMap<>();

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
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
    }
    @Override
    public void onDisable() {
        clearInteractions();
    }

    public static ImmersiveWorldTools getPlugin() {
        return plugin;
    }
    public static ImmersivePlayerMenuUtility getPlayerMenuUtil(Player p) {
        if(menuUtilMap.containsKey(p.getUniqueId())) {
            return menuUtilMap.get(p.getUniqueId());
        }
        else {
            ImmersivePlayerMenuUtility util = new ImmersivePlayerMenuUtility(p);
            menuUtilMap.put(p.getUniqueId(), util);
            return util;
        }
    }
}
