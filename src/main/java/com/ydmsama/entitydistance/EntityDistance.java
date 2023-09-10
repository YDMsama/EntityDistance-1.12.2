package com.ydmsama.entitydistance;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;



@Mod(modid = EntityDistance.MOD_ID)
public class EntityDistance {
    public static final String MOD_ID = "entitydistance";

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        System.out.println("Hello world!");
    }

    @Mod.EventBusSubscriber(modid = EntityDistance.MOD_ID)
    public static class ConfigEventHandler {

        @SubscribeEvent
        public static void onConfigChangedEvent(OnConfigChangedEvent event) {
            if (event.getModID().equals(EntityDistance.MOD_ID)) {
                ConfigManager.sync(EntityDistance.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }


}
