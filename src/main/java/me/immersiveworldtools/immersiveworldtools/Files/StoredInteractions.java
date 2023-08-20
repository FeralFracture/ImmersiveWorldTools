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
        jsonData.put("block", o.blockType.name());
        jsonData.put("message", o.message);
        jsonData.put("hexColor", o.hexColor);
        jsonData.put("X", o.x);
        jsonData.put("Y", o.y);
        jsonData.put("Z", o.z);
        jsonData.put("height", o.height);
        jsonData.put("width", o.width);
        jsonData.put("world", o.world);
        config.addDefault(o.interactionName, jsonData);
        save();
    }

    public void updateInteractionToConfig(String name, String key, String value) {
        if (config.contains(name)) {
            // Retrieve the current data for the specified entry
            Map<String, Object> jsonData = config.getConfigurationSection(name).getValues(false);
            // Check if the subkey exists in the current data
            if (jsonData.containsKey(key)) {
                // Update the subvalue
                jsonData.put(key, value);
                // Set the updated data back to the configuration
                config.set(name, jsonData);
                // Save the configuration
                save();
            } else {
                System.out.println("Subkey '" + key + "' not found in entry '" + name + "'.");
            }
        } else {
            System.out.println("Entry '" + name + "' not found in the configuration.");
        }
    }

    public void updateInteractionToConfig(InteractionObject o, String type) {
        if (config.contains(o.interactionName)) {
            // Retrieve the current data for the specified entry
            Map<String, Object> jsonData = config.getConfigurationSection(o.interactionName).getValues(false);
            // Check if the subkey exists in the current data
            switch (type) {
                case "position":
                    if (jsonData.containsKey("X") && jsonData.containsKey("Y") && jsonData.containsKey("Z")) {
                        // Update the subvalue
                        jsonData.put("X", o.x);
                        jsonData.put("Y", o.y);
                        jsonData.put("Z", o.z);
                        config.set(o.interactionName, jsonData);
                        save();
                    } else {
                        System.out.println("Couldn't find position values for " + o.interactionName);
                    }
                    break;
                case "size":
                    if (jsonData.containsKey("width") && jsonData.containsKey("height")) {
                        jsonData.put("width", o.width);
                        jsonData.put("height", o.height);
                        config.set(o.interactionName, jsonData);
                        save();
                    } else {
                        System.out.println("Couldn't find height/width values for " + o.interactionName);
                    }
                    break;
                case "blockType": {
                    if (jsonData.containsKey("block")) {
                        jsonData.put("block", o.blockType.name());
                        config.set(o.interactionName, jsonData);
                        save();
                    } else {
                        System.out.println("Couldn't find block type value for " + o.interactionName);
                    }
                    break;
                }
            }
        } else {
            System.out.println("Entry '" + o.interactionName + "' not found in the configuration.");
        }
    }

    public void removeInteractableEntry(String key) {
        if (config.contains(key)) {
            this.set(key, null);
            this.save();
        } else {
            System.out.println("not found?");
        }
    }


    @Override
    protected void addDefaults() {

    }
}
