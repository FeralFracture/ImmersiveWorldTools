package me.immersiveworldtools.immersiveworldtools.Utils;

import net.md_5.bungee.api.ChatColor;

public class HexColorsUtils {
    public static String hexColor(String hexColor) {
        if (hexColor.startsWith("#")) {
            return ChatColor.of(hexColor).toString();
        }
        return "";
    }
    public static String stripColors(String message) {
        return ChatColor.stripColor(message);
    }
}


