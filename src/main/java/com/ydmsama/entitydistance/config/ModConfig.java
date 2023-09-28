package com.ydmsama.entitydistance.config;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Config;

import java.util.*;

import static com.ydmsama.entitydistance.EntityDistance.MOD_ID;

@Config(modid = MOD_ID)
public class ModConfig {

    @Config.Name("Render Distance Multiplier")
    @Config.Comment("Multiplier for entity render distance. Default is 1.0D (100%)")
    @Config.RangeDouble(min = 0.0D, max = 5.0D)
    public static double renderDistanceMultiplier = 1.0D;

    @Config.Name("Track Distance Multiplier")
    @Config.Comment("Multiplier for entity track distance. Default is 1.0D (100%)")
    @Config.RangeDouble(min = 0.0D, max = 5.0D)
    public static double trackDistanceMultiplier = 1.0D;

    @Config.Name("Entity Blacklist")
    @Config.Comment({
            "List of entity IDs that are exempted from any changes.",
            "Format: 'entityID'. e.g. minecraft:zombie"
    })
    public static String[] entityList = new String[]{};

    @Config.Comment({
            "Custom Entity Track Multipliers.",
            "Format: 'entityID,multiplier'. e.g. minecraft:zombie,2.0"
    })
    public static String[] CustomEntityTrackMultipliers = {};

    @Config.Comment("Set to true to treat the 'blackList' as a whitelist (only entities in the list are affected). Set to false to treat it as a blacklist.")
    public static boolean Whitelist = false;
    public static double getEntityTrackMultiplier(Entity entity) {
        ResourceLocation entityId = EntityList.getKey(entity);
        String entityIdString = entityId != null ? entityId.toString() : null;

        if (entityIdString == null) {
            return trackDistanceMultiplier;
        }

        List<String> entityListNames = Arrays.asList(ModConfig.entityList);

        if (ModConfig.Whitelist) {
            if (entityListNames.isEmpty()) {
                return 1.0;
            }
            else if (!entityListNames.contains(entityIdString)) {
                return 1.0;
            }
        }
        else {
            if (entityListNames.contains(entityIdString)) {
                return 1.0;
            }
        }

        Map<String, Double> multipliers = parseEntityTrackMultipliers();
        return multipliers.getOrDefault(entityIdString, trackDistanceMultiplier);
    }


    public static Map<String, Double> parseEntityTrackMultipliers() {
        Map<String, Double> result = new HashMap<>();
        for (String entry : CustomEntityTrackMultipliers) {
            String[] parts = entry.split(",");
            if (parts.length == 2) {
                try {
                    String entityID = parts[0].trim();
                    double multiplier = Double.parseDouble(parts[1].trim());
                    result.put(entityID, multiplier);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
