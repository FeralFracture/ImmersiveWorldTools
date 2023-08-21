package me.immersiveworldtools.immersiveworldtools;

import me.immersiveworldtools.immersiveworldtools.Commands.CommandManager;
import me.immersiveworldtools.immersiveworldtools.Files.StoredBlocks;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

import static me.immersiveworldtools.immersiveworldtools.Utils.BlockFlickerManager.readConfig;

public final class ImmersiveWorldTools extends JavaPlugin {
    private static ImmersiveWorldTools plugin;
    public static StoredBlocks storedBlocks;

    public String getOPEN_AI_KEY() {
        return OPEN_AI_KEY;
    }

    private final String OPEN_AI_KEY = getOpenAiKey();

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
}
