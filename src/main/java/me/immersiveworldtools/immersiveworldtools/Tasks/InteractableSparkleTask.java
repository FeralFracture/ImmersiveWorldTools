package me.immersiveworldtools.immersiveworldtools.Tasks;

import me.immersiveworldtools.immersiveworldtools.ImmersiveWorldTools;
import me.immersiveworldtools.immersiveworldtools.Utils.Classes.InteractionObject;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class InteractableSparkleTask extends BukkitRunnable {
    private InteractionObject parentObject;
    private Random r = new Random();
    private int maxTicks = 40;

    public InteractableSparkleTask(InteractionObject parent) {
        parentObject = parent;
    }

    @Override
    public void run() {

        int delay = r.nextInt(maxTicks - 5 + 1) + 5;

        new BukkitRunnable() {
            @Override
            public void run() {
                int volume = (int) Math.ceil((double)parentObject.interaction.getInteractionHeight() + (double)parentObject.interaction.getInteractionWidth());
                double multi = 0.17;
                parentObject.interaction.getWorld().spawnParticle(
                        Particle.VILLAGER_HAPPY, parentObject.interaction.getLocation().add(0, ((double)parentObject.interaction.getInteractionHeight())/2, 0),
                        volume,
                        ((double)parentObject.interaction.getInteractionWidth() * multi),
                        ((double)parentObject.interaction.getInteractionHeight() * multi),
                        ((double)parentObject.interaction.getInteractionWidth() * multi)
                        );
            }
        }.runTaskLater(ImmersiveWorldTools.getPlugin(), delay);
    }
}
