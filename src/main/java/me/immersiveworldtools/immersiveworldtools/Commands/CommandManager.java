package me.immersiveworldtools.immersiveworldtools.Commands;

import me.immersiveworldtools.immersiveworldtools.Commands.Subcommands.RemoveFlickeringBlockSubcommand;
import me.immersiveworldtools.immersiveworldtools.Commands.Subcommands.SetFlickeringBlockSubcommand;
import org.bukkit.ChatColor;

import java.util.ArrayList;

public class CommandManager extends me.codetoolsapi.codetoolsapi.CommandTools.CommandManager {
    public CommandManager() {
        subcommands.add(new SetFlickeringBlockSubcommand());
        subcommands.add(new RemoveFlickeringBlockSubcommand());
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
}
