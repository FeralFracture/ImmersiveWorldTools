package me.immersiveworldtools.immersiveworldtools.Utils.Classes;

import me.immersiveworldtools.immersiveworldtools.ImmersiveWorldTools;
import me.immersiveworldtools.immersiveworldtools.Tasks.BlockFlickerTask;
import me.immersiveworldtools.immersiveworldtools.Tasks.InteractableSparkleTask;
import org.apache.commons.codec.binary.Hex;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Interaction;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.UUID;

public class InteractionObject {
    public Interaction interaction;
    public String uuid;
    public String message;
    public String hexColor = "#FFFFFF";

    public InteractableSparkleTask sparkleTask = new InteractableSparkleTask(this);

    public InteractionObject(Interaction interaction, String message) {
        this.interaction = interaction;
        this.uuid = interaction.getUniqueId().toString();
        this.message = message;
        sparkleTask.runTaskTimer(ImmersiveWorldTools.getPlugin(), 0L, 7L);
    }

    public InteractionObject(String uuid) {
        this.uuid = uuid;
        this.interaction = (Interaction) Bukkit.getEntity(UUID.fromString(uuid));
        sparkleTask.runTaskTimer(ImmersiveWorldTools.getPlugin(), 0L, 7L);
    }

    public void stopSparkle() {
        System.out.println("canceling sparkles.");
        sparkleTask.cancel();
    }
}
