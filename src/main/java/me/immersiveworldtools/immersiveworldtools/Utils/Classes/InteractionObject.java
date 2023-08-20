package me.immersiveworldtools.immersiveworldtools.Utils.Classes;

import me.immersiveworldtools.immersiveworldtools.ImmersiveWorldTools;
import me.immersiveworldtools.immersiveworldtools.Tasks.BlockFlickerTask;
import me.immersiveworldtools.immersiveworldtools.Tasks.InteractableSparkleTask;
import org.apache.commons.codec.binary.Hex;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Interaction;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.UUID;

import static me.immersiveworldtools.immersiveworldtools.Utils.GeneralUtils.getDefaultConfig;

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

    public InteractionObject cloneObject() {
        InteractionObject iobject = new InteractionObject();
        iobject.height = this.height;
        iobject.width = this.width;
        iobject.world = this.world;
        iobject.x = this.x;
        iobject.y = this.y;
        iobject.z = this.z;
        iobject.interactionName = this.interactionName;
        iobject.message = this.message;
        iobject.hexColor = this.hexColor;
        iobject.blockType = this.blockType;
        return iobject;
    }

    public InteractableSparkleTask sparkleTask = new InteractableSparkleTask(this);

    public InteractionObject(Interaction interaction, String message, String interactionName) {
        this.interaction = interaction;
        this.interactionName = interactionName;
        this.uuid = interaction.getUniqueId().toString();
        this.message = message;
        sparkleTask.runTaskTimer(ImmersiveWorldTools.getPlugin(), 0L,
                Math.max(1, 16 - getDefaultConfig().getInt("defaultSparkleRate")));
    }

    public InteractionObject() {
        sparkleTask.runTaskTimer(ImmersiveWorldTools.getPlugin(), 0L, Math.max(1, 16 - getDefaultConfig().getInt("defaultSparkleRate")));
    }

    public void stopSparkle() {
        sparkleTask.cancel();
    }
}
