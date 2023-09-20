package com.ydmsama.entitydistance.handlers;

import com.ydmsama.entitydistance.EntityDistance;
import com.ydmsama.entitydistance.config.ModConfig;
import com.ydmsama.entitydistance.utils.EntityTrackerUtils;
import net.minecraft.entity.EntityTracker;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = EntityDistance.MOD_ID)
public class ConfigEventHandler {
    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(EntityDistance.MOD_ID)) {
            int valueAsInt = (int)(Math.round(ModConfig.renderDistanceMultiplier * 100));
            ModConfig.renderDistanceMultiplier = valueAsInt / 100.0;
            ConfigManager.sync(EntityDistance.MOD_ID, Config.Type.INSTANCE);

            MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
            if (server == null) {
                return;
            }

            WorldServer worldServer = server.getWorld(DimensionType.OVERWORLD.getId());
            EntityTracker tracker = worldServer.getEntityTracker();
            EntityTrackerUtils.updateGlobalEntityDistance(worldServer, tracker);
        }
    }
}