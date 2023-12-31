package me.immersiveworldtools.immersiveworldtools.Files;


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static me.immersiveworldtools.immersiveworldtools.ImmersiveWorldTools.getPlugin;

public abstract class YmlFile {
    protected File file;
    protected FileConfiguration config;

    protected abstract void addDefaults();

    private void setup(String filename) {
        this.file = new File(getPlugin().getDataFolder(), filename + ".yml");
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                System.out.println("[Error] Error in generating missing '" + filename + ".yml' config file.");
            }
        }
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    protected void save() {
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            System.out.println("[Error] Failed to save to '" + this.file.getName() + ".yml'.");
        }
    }

    protected void set(String key, Object value) {
        this.config.set(key, value);
        save();
    }

    protected void initializeResponsesConfig(String fileName) {
        setup(fileName);
        this.config.options().copyDefaults(true);
        addDefaults();
        save();
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public void reload() {
        initializeResponsesConfig(this.file.getName().split(".yml")[0]);
    }
}
