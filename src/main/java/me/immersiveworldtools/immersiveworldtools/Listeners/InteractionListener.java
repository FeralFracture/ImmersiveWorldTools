package me.immersiveworldtools.immersiveworldtools.Listeners;

import me.immersiveworldtools.immersiveworldtools.Utils.Classes.InteractionObject;
import org.bukkit.entity.Interaction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import static me.immersiveworldtools.immersiveworldtools.Utils.InteractableManager.interactablesList;
import static me.immersiveworldtools.immersiveworldtools.Utils.InteractableManager.readMessage;

public class InteractionListener implements Listener {
    @EventHandler
    public void playerClickEvent(PlayerInteractAtEntityEvent e) {
        if(e.getRightClicked() instanceof Interaction interaction
                && interactablesList.stream().anyMatch(s -> s.uuid.equalsIgnoreCase(interaction.getUniqueId().toString()))) {
            InteractionObject o = interactablesList.stream().filter(s -> s.uuid.equalsIgnoreCase(interaction.getUniqueId().toString())).findFirst().get();
            readMessage(e.getPlayer(), o);

        }
    }
}
