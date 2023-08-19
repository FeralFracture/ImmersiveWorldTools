package me.immersiveworldtools.immersiveworldtools.Utils.Classes;

import me.immersiveworldtools.immersiveworldtools.ImmersiveWorldTools;
import me.immersiveworldtools.immersiveworldtools.Tasks.BlockFlickerTask;
import me.immersiveworldtools.immersiveworldtools.Tasks.InteractableSparkleTask;
import org.apache.commons.codec.binary.Hex;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Interaction;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.UUID;

public class InteractionObject {
    public Interaction interaction = null;
    public String uuid;
    public String message;
    public String hexColor = "#FFFFFF";
    public Material blockType;
    public String interactionName = "";
    public float x;
    public float y;
    public float z;
    public float height;
    public float width;
    public String world;

    public InteractableSparkleTask sparkleTask = new InteractableSparkleTask(this);

    public InteractionObject(Interaction interaction, String message, String interactionName) {
        this.interaction = interaction;
        this.interactionName = interactionName;
        this.uuid = interaction.getUniqueId().toString();
        this.message = message;
        sparkleTask.runTaskTimer(ImmersiveWorldTools.getPlugin(), 0L, 10L);
    }

    public InteractionObject() {
        sparkleTask.runTaskTimer(ImmersiveWorldTools.getPlugin(), 0L, 10L);
    }

    public void stopSparkle() {
        sparkleTask.cancel();
    }
}
