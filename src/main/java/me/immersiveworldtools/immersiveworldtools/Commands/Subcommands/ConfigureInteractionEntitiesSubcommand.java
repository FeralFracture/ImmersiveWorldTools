package me.immersiveworldtools.immersiveworldtools.Commands.Subcommands;

import me.codetoolsapi.codetoolsapi.CommandTools.Subcommand;
import me.immersiveworldtools.immersiveworldtools.ImmersiveWorldTools;
import me.immersiveworldtools.immersiveworldtools.MenuSystem.ImmersivePlayerMenuUtility;
import me.immersiveworldtools.immersiveworldtools.MenuSystem.Menus.InteractableMenus;
import org.bukkit.entity.Player;

public class ConfigureInteractionEntitiesSubcommand extends Subcommand {
    @Override
    public String getName() {
        return "configureinteractions";
    }

    @Override
    public String getDescription() {
        return "open a menu with which to configure the interaction entities";
    }

    @Override
    public String getSyntax() {
        return "/iwt " + getName();
    }

    @Override
    public void perform(Player player, String[] strings) {
        new InteractableMenus(ImmersiveWorldTools.getPlayerMenuUtil(player)).open();

    }
}
