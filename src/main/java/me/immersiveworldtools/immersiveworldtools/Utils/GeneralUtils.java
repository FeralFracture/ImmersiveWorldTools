package me.immersiveworldtools.immersiveworldtools.Utils;

import org.bukkit.configuration.file.FileConfiguration;

import javax.annotation.Nullable;

import static me.immersiveworldtools.immersiveworldtools.ImmersiveWorldTools.getPlugin;

public class GeneralUtils {
    public static FileConfiguration getDefaultConfig() {
        return getPlugin().getConfig();
    }

    public static void setDefaultConfig(String path, @Nullable Object value) {
        getPlugin().getConfig().set(path, value);
        getPlugin().saveConfig();
    }
}
