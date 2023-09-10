package com.ydmsama.entitydistance.config;

import net.minecraftforge.common.config.Config;

import static com.ydmsama.entitydistance.EntityDistance.MOD_ID;

@Config(modid = MOD_ID)
public class ModConfig {

    @Config.Name("Render Distance Multiplier")
    @Config.Comment("Multiplier for entity render distance. Default is 2.0D (200%)")
    @Config.RangeDouble(min = 0.0D, max = 10.0D)
    public static double renderDistanceMultiplier = 2.0D;

    @Config.Name("track Distance Multiplier")
    @Config.Comment("Multiplier for entity track distance. Default is 2.0D (200%)")
    @Config.RangeDouble(min = 0.0D, max = 10.0D)
    public static double trackDistanceMultiplier = 2.0D;
}