package me.immersiveworldtools.immersiveworldtools.Commands.Subcommands;

import me.codetoolsapi.codetoolsapi.CommandTools.Subcommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;

import static me.immersiveworldtools.immersiveworldtools.Utils.InteractableManager.addInteraction;

public class SummonInteractionEntitySubcommand extends Subcommand {
    @Override
    public String getName() {
        return "addinteraction";
    }

    @Override
    public String getDescription() {
        return "create an interaction entity. All numerical values can be decimals. Interaction name must be one continuous word (Word_like_this), but the message is any extra words after, the width value.";
    }

    @Override
    public String getSyntax() {
        return "/iwt " + getName() + " <interaction name> <x> <y> <z> <height> <width> <Message>";
    }

    @Override
    public void perform(Player player, String[] strings) {
        if (strings.length < 8) {
            player.sendMessage(ChatColor.RED + "Usage: /iwt " + getName() + " <interaction name> <x> <y> <z> <height> <width> <Message>");
        } else {
            try {
                String name = strings[1];
                float x = Float.parseFloat(strings[2]);
                float y = Float.parseFloat(strings[3]);
                float z = Float.parseFloat(strings[4]);
                float height = Float.parseFloat(strings[5]);
                float width = Float.parseFloat(strings[6]);
                StringBuilder message = new StringBuilder();
                for (int i = 7; i < strings.length; i++) {
                    message.append(strings[i]);
                    if ((i + 1) < strings.length) {
                        message.append(" ");
                    }
                }
                addInteraction(player, x, y, z, width, height, message.toString(), name);
                player.sendMessage(ChatColor.GREEN + "New interaction \"" + ChatColor.ITALIC + name + ChatColor.GREEN + "\" created.");
            } catch (Exception ex) {
                player.sendMessage(ChatColor.RED + "Usage: /iwt " + getName() + " <interaction name> <x> <y> <z> <height> <width> <Message>");
            }
        }
    }
}
