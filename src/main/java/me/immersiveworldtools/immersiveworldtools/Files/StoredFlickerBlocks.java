package me.immersiveworldtools.immersiveworldtools.Files;


import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StoredFlickerBlocks extends YmlFile {

    public StoredFlickerBlocks() {
        initializeResponsesConfig("storedFlickerBlocks");
        //flickerBlockCount = ((ArrayList<Map<String, Object>>) config.get("flickerBlocks")).size();
    }

    public void addFlickerLightLocation(Location location, int flickerMaxRate) {
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("blockType", "lamp");
        jsonData.put("worldName", location.getWorld().getName());
        jsonData.put("x", location.getBlockX());
        jsonData.put("y", location.getBlockY());
        jsonData.put("z", location.getBlockZ());
        jsonData.put("maxTick", flickerMaxRate);
        config.addDefault(String.valueOf(UUID.randomUUID()), jsonData);
        save();
    }

    public void removeFlickerEntry(String key) {
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
