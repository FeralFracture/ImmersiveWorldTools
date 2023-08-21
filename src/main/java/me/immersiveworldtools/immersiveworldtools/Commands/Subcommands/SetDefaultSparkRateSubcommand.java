package me.immersiveworldtools.immersiveworldtools.Commands.Subcommands;

import me.codetoolsapi.codetoolsapi.CommandTools.Subcommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static me.immersiveworldtools.immersiveworldtools.Utils.GeneralUtils.setDefaultConfig;
import static me.immersiveworldtools.immersiveworldtools.Utils.InteractableManager.reload;

public class SetDefaultSparkRateSubcommand extends Subcommand {
    @Override
    public String getName() {
        return "setdefaultsparklerate";
    }

    @Override
    public String getDescription() {
        return "adjust the rate at which interactables sparkle. <0-15> slowest to fastest. (they still spark at 0 that is just the min value)";
    }

    @Override
    public String getSyntax() {
        return "/iwt " + getName() + " <0 - 15 int value>";
    }

    @Override
    public void perform(Player player, String[] strings) {
        try {
            setDefaultConfig("defaultSparkleRate", Integer.parseInt(strings[1]));
            reload();
        } catch (Exception ex) {
            player.sendMessage(ChatColor.RED + "Invalid inputs");
        }


    }
}
