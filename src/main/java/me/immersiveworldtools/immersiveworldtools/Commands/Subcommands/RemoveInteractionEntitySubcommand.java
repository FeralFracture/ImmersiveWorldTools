package me.immersiveworldtools.immersiveworldtools.Commands.Subcommands;

import me.codetoolsapi.codetoolsapi.CommandTools.Subcommand;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import static me.immersiveworldtools.immersiveworldtools.Utils.InteractableManager.removeInteraction;

public class RemoveInteractionEntitySubcommand extends Subcommand {
    @Override
    public String getName() {
        return "removeinteraction";
    }

    @Override
    public String getDescription() {
        return "deletes an existing interaction entity";
    }

    @Override
    public String getSyntax() {
        return "/iwt " + getName() + " <interaction name>";
    }

    @Override
    public void perform(Player player, String[] strings) {
        if (strings.length >= 2) {
            removeInteraction(strings[1]);
        } else {
            Interaction target = getClosestLookingAtInteraction(player);
            if (target != null)  {
                removeInteraction(target.getUniqueId().toString());
            } else {
                player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "No Interactable found.");
            }
        }
    }

    private Interaction getClosestLookingAtInteraction(Player player) {
        Location eyeLocation = player.getEyeLocation();
        Vector direction = eyeLocation.getDirection();
        Interaction closestEntity = null;
        double range = 30;
        double closestDistance = range; // Start with the maximum range

        for (Entity entity : player.getNearbyEntities(range, range, range)) {
            if (entity instanceof Interaction i) {
                Location entityLocation = entity.getLocation().add(0, ((double)i.getInteractionHeight())/2, 0);
                Vector toEntity = entityLocation.subtract(eyeLocation).toVector();
                double distance = toEntity.length();

                if (distance <= closestDistance && direction.normalize().dot(toEntity.normalize()) >= 0.985) {
                    closestEntity = (Interaction) entity;
                    closestDistance = distance;
                }
            }
        }
        return closestEntity;
    }
}
