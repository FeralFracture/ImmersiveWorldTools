package me.immersiveworldtools.immersiveworldtools;

import me.codetoolsapi.codetoolsapi.MenuSystem.Menu;
import me.immersiveworldtools.immersiveworldtools.Commands.CommandManager;
import me.immersiveworldtools.immersiveworldtools.Files.StoredFlickerBlocks;
import me.immersiveworldtools.immersiveworldtools.Files.StoredInteractions;
import me.immersiveworldtools.immersiveworldtools.Listeners.ChatListener;
import me.immersiveworldtools.immersiveworldtools.Listeners.InteractionListener;
import me.immersiveworldtools.immersiveworldtools.Listeners.MenuListener;
import me.immersiveworldtools.immersiveworldtools.MenuSystem.ImmersivePlayerMenuUtility;
import me.immersiveworldtools.immersiveworldtools.Utils.BlockFlickerManager;
import me.immersiveworldtools.immersiveworldtools.Utils.InteractableManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

import static me.immersiveworldtools.immersiveworldtools.Utils.BlockFlickerManager.readConfig;

import java.awt.*;
import java.util.HashMap;
import java.util.UUID;

import static me.immersiveworldtools.immersiveworldtools.Utils.InteractableManager.clearInteractions;


public final class ImmersiveWorldTools extends JavaPlugin {
    private static ImmersiveWorldTools plugin;

    private final String OPEN_AI_KEY = getOpenAiKey();
    public static StoredFlickerBlocks storedBlocks;
    public static StoredInteractions storedInteractions;
    private static final HashMap<UUID, ImmersivePlayerMenuUtility> menuUtilMap = new HashMap<>();


    @Override
    public void onEnable() {
        plugin = this;
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
        this.saveConfig();
        storedBlocks = new StoredFlickerBlocks();
        storedInteractions = new StoredInteractions();
        BlockFlickerManager.readConfig();
        InteractableManager.readConfig();
        getCommand(new CommandManager().command_prefix()).setExecutor(new CommandManager());
        getServer().getPluginManager().registerEvents(new InteractionListener(), this);
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
    }

    @Override
    public void onDisable() {
        clearInteractions();
    }

    public static ImmersiveWorldTools getPlugin() {
        return plugin;
    }

    public String getOPEN_AI_KEY() {
        return OPEN_AI_KEY;
    }

    private String getOpenAiKey() {
        File configFile = new File(getDataFolder(), "application.properties");
        if (!configFile.exists()) {
            // If it doesn't exist, copy the default properties from the resources
            InputStream defaultConfig = getResource("application.properties");

            if (defaultConfig != null) {
                try {
                    Files.copy(defaultConfig, configFile.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                getLogger().severe("Failed to copy default application.properties from resources.");
            }
        }
        // Now you can load and use the properties from the application.properties file as before
        try (InputStream input = new FileInputStream(configFile)) {
            Properties properties = new Properties();
            properties.load(input);
            String apiKey = properties.getProperty("openAiKey");
            return apiKey;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ImmersivePlayerMenuUtility getPlayerMenuUtil(Player p) {
        if (menuUtilMap.containsKey(p.getUniqueId())) {
            return menuUtilMap.get(p.getUniqueId());
        } else {
            ImmersivePlayerMenuUtility util = new ImmersivePlayerMenuUtility(p);
            menuUtilMap.put(p.getUniqueId(), util);
            return util;

        }
    }
}
