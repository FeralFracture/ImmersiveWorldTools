package me.immersiveworldtools.immersiveworldtools.Commands.Subcommands;

import me.codetoolsapi.codetoolsapi.CommandTools.Subcommand;
import me.immersiveworldtools.immersiveworldtools.Utils.BlockFlickerManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ReloadPluginSubcommand extends Subcommand {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reloads just this plugin. Useful for low resource use means of double checking addition/removal if custom tool changes were saved successfully.";
    }

    @Override
    public String getSyntax() {
        return "/iwt " + getName();
    }

    @Override
    public void perform(Player player, String[] strings) {
        BlockFlickerManager.reload();
        player.sendMessage(ChatColor.GREEN + "Immersive World Tools configs Reloaded.");
    }
}
