package me.immersiveworldtools.immersiveworldtools.Commands.Subcommands;

import me.codetoolsapi.codetoolsapi.CommandTools.Subcommand;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import static me.immersiveworldtools.immersiveworldtools.Utils.BlockFlickerManager.addFlickerBlock;
import static me.immersiveworldtools.immersiveworldtools.Utils.BlockFlickerManager.removeFlickerBlock;

public class RemoveFlickeringBlockSubcommand extends Subcommand {
    @Override
    public String getName() {
        return "removeflicker";
    }

    @Override
    public String getDescription() {
        return "removes the flickering effect for a target block if it's a flickering block.";
    }

    @Override
    public String getSyntax() {
        return "/iwt " + getName();
    }

    @Override
    public void perform(Player player, String[] strings) {
        Block target = player.getTargetBlockExact(5);

        if (target != null) {
            removeFlickerBlock(player, target);
        } else {
            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "No Block Selected.");
        }
    }
}
