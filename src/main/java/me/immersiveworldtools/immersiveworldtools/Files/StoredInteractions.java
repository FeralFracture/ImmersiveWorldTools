package me.immersiveworldtools.immersiveworldtools.Files;

import me.immersiveworldtools.immersiveworldtools.Utils.Classes.InteractionObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StoredInteractions extends YmlFile {

    public StoredInteractions() {
        initializeResponsesConfig("storedInteractables");
    }

    public void addInteractableToConfig(InteractionObject o) {
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("message",o.message);
        jsonData.put("hexColor", o.hexColor);
        config.addDefault(o.uuid, jsonData);

        save();
    }
    public void removeInteractableEntry(String key) {
        if(config.contains(key)){
            this.set(key, null);
            this.save();
        }
        else {
            System.out.println("not found?");
        }
    }



    @Override
    protected void addDefaults() {

    }
}
