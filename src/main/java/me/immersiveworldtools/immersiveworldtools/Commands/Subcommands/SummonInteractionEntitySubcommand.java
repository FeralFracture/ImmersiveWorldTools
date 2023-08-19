package me.immersiveworldtools.immersiveworldtools.Commands.Subcommands;

import me.codetoolsapi.codetoolsapi.CommandTools.Subcommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;

import static me.immersiveworldtools.immersiveworldtools.Utils.InteractableManager.addInteraction;

public class SummonInteractionEntitySubcommand extends Subcommand {
    @Override
    public String getName() {
        return "addinteractionEntity";
    }

    @Override
    public String getDescription() {
        return "test";
    }

    @Override
    public String getSyntax() {
        return "/iwt" + getName();
    }

    @Override
    public void perform(Player player, String[] strings) {
        if (strings.length < 7) {
            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Invalid Inputs");
        } else {
            float x = Float.parseFloat(strings[1]);
            float y = Float.parseFloat(strings[2]);
            float z = Float.parseFloat(strings[3]);
            float width = Float.parseFloat(strings[4]);
            float height = Float.parseFloat(strings[5]);
            StringBuilder message = new StringBuilder();
            for (int i = 6; i < strings.length; i++) {
                message.append(strings[i]);
                if ((i + 1) < strings.length) {
                    message.append(" ");
                }
            }
            addInteraction(player, x, y, z, width, height, message.toString());
        }
    }
}
