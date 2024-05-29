package com.ydmsama.entitydistance.config;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Config;

import java.util.*;

import static com.ydmsama.entitydistance.EntityDistance.MOD_ID;

@Config(modid = MOD_ID)
public class ModConfig {

    @Config.Name("Use Mod GUI")
    @Config.Comment("Whether to use the Mod GUI. Default is true.")
    public static boolean useModGui = true;

    public static EntitySettings entitySettings = new EntitySettings();

    public static class EntitySettings {
        @Config.Name("Enable Animal Track Multiplier")
        @Config.Comment("Set to true to enable the Animal Track Multiplier.")
        public boolean enableAnimalMultiplier = false;

        @Config.Name("Enable Mob Track Multiplier")
        @Config.Comment("Set to true to enable the Mob Track Multiplier.")
        public boolean enableMobMultiplier = false;

        @Config.Name("Enable NonLiving Entity Track Multiplier")
        @Config.Comment("Set to true to enable the NonLiving Entity Track Multiplier.")
        public boolean enableNonLivingMultiplier = false;

        @Config.Name("Enable Player Entity Track Multiplier")
        @Config.Comment("Set to true to enable the Player Entity Track Multiplier.")
        public boolean enablePlayerMultiplier = false;

        @Config.Name("Animal Track Multiplier")
        @Config.Comment("Multiplier for animal track distance.")
        @Config.RangeDouble(min = 0.0D, max = 5.0D)
        public double animalMultiplier = 0.25D;

        @Config.Name("Mob Track Multiplier")
        @Config.Comment("Multiplier for mob track distance.")
        @Config.RangeDouble(min = 0.0D, max = 5.0D)
        public double mobMultiplier = 0.6D;

        @Config.Name("NonLiving Entity Track Multiplier")
        @Config.Comment("Multiplier for non-living entity track distance.")
        @Config.RangeDouble(min = 0.0D, max = 5.0D)
        public double nonLivingMultiplier = 0.5D;

        @Config.Name("Player Entity Track Multiplier")
        @Config.Comment("Multiplier for player entity track distance.")
        @Config.RangeDouble(min = 0.0D, max = 5.0D)
        public double playerMultiplier = 1.5D;

//        @Config.Name("Neutral Entity Track Multiplier")
//        @Config.Comment("Multiplier for neutral entity track distance. Set 0 to disable.")
//        @Config.RangeDouble(min = 0.0D, max = 5.0D)
//        public static double neutralMultiplier = 0.0D;

//        @Config.Name("NonNeutral Entity Track Multiplier")
//        @Config.Comment("Multiplier for non-neutral entity track distance. Set 0 to disable.")
//        @Config.RangeDouble(min = 0.0D, max = 5.0D)
//        public static double nonNeutralMultiplier = 0.0D;
    }

    @Config.Name("Render Distance Multiplier")
    @Config.Comment("Multiplier for global entity render distance. Default is 1.0D (100%)")
    @Config.RangeDouble(min = 0.0D, max = 5.0D)
    public static double renderDistanceMultiplier = 1.0D;

    @Config.Name("Track Distance Multiplier")
    @Config.Comment("Multiplier for entity track distance. Default is 1.0D (100%)")
    @Config.RangeDouble(min = 0.0D, max = 5.0D)
    public static double trackDistanceMultiplier = 1.0D;

    @Config.Name("Entity Blacklist")
    @Config.Comment({
            "List of entity IDs that are exempted from any changes.(tracking distance only)",
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
        if(entitySettings.enablePlayerMultiplier && entity instanceof EntityPlayerMP){
            return entitySettings.playerMultiplier;
        }
        if(entitySettings.enableNonLivingMultiplier && !(entity instanceof EntityLiving)){
            return entitySettings.nonLivingMultiplier;
        }
        if (entitySettings.enableMobMultiplier && entity instanceof IMob) {
            return entitySettings.mobMultiplier;
        }
        if (entitySettings.enableAnimalMultiplier && (entity instanceof EntityAnimal || entity instanceof EntityAmbientCreature || entity instanceof EntityWaterMob)) {
            return entitySettings.animalMultiplier;
        }

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
                String entityID = parts[0].trim();
                double multiplier = Double.parseDouble(parts[1].trim());
                result.put(entityID, multiplier);
            }
        }
        return result;
    }
}
