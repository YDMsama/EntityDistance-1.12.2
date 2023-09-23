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
    @Config.Comment("Multiplier for entity render distance. Default is 2.5D (250%)")
    @Config.RangeDouble(min = 0.0D, max = 5.0D)
    public static double renderDistanceMultiplier = 2.5D;

    @Config.Name("track Distance Multiplier")
    @Config.Comment("Multiplier for entity track distance. Default is 2.0D (200%)")
    @Config.RangeDouble(min = 0.0D, max = 5.0D)
    public static double trackDistanceMultiplier = 2.0D;

    @Config.Name("Entity Blacklist")
    @Config.Comment("List of entity IDs that are exempted from any changes.")
    public static String[] entityList = new String[]{};

    @Config.Name("Custom Entity Track Multipliers")
    @Config.Comment("A map of entities and their specific track distance multipliers. e.g. \"minecraft:zombie\" = 2.0")
    public static Map<String, Double> customEntityTrackMultipliers = new HashMap<>();

    @Config.Comment("Set to true to treat the 'entityList' as a whitelist (only entities in the list are affected). Set to false to treat it as a blacklist.")
    public static boolean useWhitelist = false;
    public static double getEntityTrackMultiplier(Entity entity) {
        ResourceLocation entityId = EntityList.getKey(entity);
        String entityIdString = entityId != null ? entityId.toString() : null;

        if (entityIdString == null) {
            return trackDistanceMultiplier;
        }

        List<String> entityList = Arrays.asList(ModConfig.entityList);

        if (!entityList.isEmpty()) {
            if (ModConfig.useWhitelist) {
                if (!entityList.contains(entityIdString)) {
                    return 1.0;
                }
            } else {
                if (entityList.contains(entityIdString)) {
                    return 1.0;
                }
            }
        }

        return ModConfig.customEntityTrackMultipliers.getOrDefault(entityIdString, trackDistanceMultiplier);
    }
}
