package me.immersiveworldtools.immersiveworldtools.Commands.Subcommands;

import me.codetoolsapi.codetoolsapi.CommandTools.Subcommand;
import me.immersiveworldtools.immersiveworldtools.ImmersiveWorldTools;
import me.immersiveworldtools.immersiveworldtools.Tasks.BlockFlickerTask;
import me.immersiveworldtools.immersiveworldtools.Utils.BlockFlickerManager;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.UUID;

import static me.immersiveworldtools.immersiveworldtools.ImmersiveWorldTools.storedBlocks;
import static me.immersiveworldtools.immersiveworldtools.Utils.BlockFlickerManager.addFlickerBlock;

public class SetFlickeringBlockSubcommand extends Subcommand {
    @Override
    public String getName() {
        return "setflicker";
    }

    @Override
    public String getDescription() {
        return "set flickering lamp";
    }

    @Override
    public String getSyntax() {
        return "/iwt " + getName();
    }

    @Override
    public void perform(Player player, String[] strings) {
        Block target = player.getTargetBlockExact(5);
        int max = 60;
        try {
            max = Integer.parseInt(strings[1]);
        } catch (Exception e) {
            //whoops
        }
        if (target != null) {
            addFlickerBlock(player, target, max);
        } else {
            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "No Block Selected.");
        }
    }
}
