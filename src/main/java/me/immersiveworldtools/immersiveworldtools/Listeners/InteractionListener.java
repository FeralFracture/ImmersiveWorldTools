package me.immersiveworldtools.immersiveworldtools.Listeners;

import me.immersiveworldtools.immersiveworldtools.Utils.Classes.InteractionObject;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Interaction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.immersiveworldtools.immersiveworldtools.Utils.InteractableManager.*;

public class InteractionListener implements Listener {
    @EventHandler
    public void playerClickEvent(PlayerInteractAtEntityEvent e) {
        if(e.getRightClicked() instanceof Interaction interaction
                && interactablesList.stream().anyMatch(s -> s.uuid.equalsIgnoreCase(interaction.getUniqueId().toString()))) {
            InteractionObject o = interactablesList.stream().filter(s -> s.uuid.equalsIgnoreCase(interaction.getUniqueId().toString())).findFirst().get();
            readMessage(e.getPlayer(), o);

        }
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.getPlayer().getWorld().getEntities();
        for(Entity entity : e.getPlayer().getWorld().getEntities()) {
            if(entity instanceof Interaction) {
                entity.remove();
            }
        }
        reload();
    }
}
