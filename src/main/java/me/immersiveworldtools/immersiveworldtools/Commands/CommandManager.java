package me.immersiveworldtools.immersiveworldtools.Commands;

import me.codetoolsapi.codetoolsapi.CodeToolsApi;
import me.immersiveworldtools.immersiveworldtools.Commands.Subcommands.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends me.codetoolsapi.codetoolsapi.CommandTools.CommandManager {
    public CommandManager() {
        subcommands.add(new SetFlickeringBlockSubcommand());
        subcommands.add(new RemoveFlickeringBlockSubcommand());
        subcommands.add(new ReloadPluginSubcommand());
        subcommands.add(new SummonInteractionEntitySubcommand());
        subcommands.add(new RemoveInteractionEntitySubcommand());
        subcommands.add(new ConfigureInteractionEntitiesSubcommand());
        subcommands.add(new SetDefaultSparkRateSubcommand());
    }

    @Override
    public ArrayList<String> subcommand_player_autoTab() {
        return new ArrayList<>();
    }

    @Override
    public int header_footer_bar_length() {
        return 40;
    }

    @Override
    public String command_prefix() {
        return "immersiveworldtools";
    }

    @Override
    public String command_manager_title() {
        return "Immersive World Tools";
    }

    @Override
    public String command_name_color() {
        return ChatColor.GREEN + "";
    }

    @Override
    public String command_description_color() {
        return ChatColor.BLUE + "";
    }

    @Override
    public String command_syntax_color() {
        return ChatColor.GRAY + "";
    }

    @Override
    public String header_footer_color() {
        return ChatColor.GOLD + "";
    }

    @Override
    public boolean add_header_footer_bars() {
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        ArrayList<String> words = new ArrayList<>();
        int i;
        if (args.length == 1) {
            for (i = 0; i < this.subcommands.size(); ++i) {
                words.add((this.subcommands.get(i)).getName());
            }

            words.add("help");
        } else if (args.length == 2 && !args[0].equalsIgnoreCase("addinteractionEntity")) {
            if (this.subcommand_player_autoTab().contains(args[0].toLowerCase())) {

                for (Player p : CodeToolsApi.getPlugin().getServer().getOnlinePlayers()) {
                    words.add(p.getDisplayName());
                }
            } else if (args[0].equalsIgnoreCase("help")) {
                for (i = 0; i < this.subcommands.size(); ++i) {
                    words.add((this.subcommands.get(i)).getName());
                }
            }
        } else if (args.length == 3) {
            try {
                words.add(
                        String.valueOf(((Player) commandSender).getTargetBlockExact(5).getX())
                );
            } catch (Exception ex) {
                //whoops
            }
        } else if (args.length == 4) {
            try {
                words.add(
                        String.valueOf(((Player) commandSender).getTargetBlockExact(5).getY())
                );
            } catch (Exception ex) {
                //whoops
            }
        } else if (args.length == 5) {
            try {
                words.add(
                        String.valueOf(((Player) commandSender).getTargetBlockExact(5).getZ())
                );
            } catch (Exception ex) {
                //whoops
            }
        }


        return words;
    }

}
