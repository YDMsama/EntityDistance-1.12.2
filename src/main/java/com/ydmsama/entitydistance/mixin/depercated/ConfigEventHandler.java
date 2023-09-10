//package com.ydmsama.entitydistance;
//
//import net.minecraftforge.common.config.Config;
//import net.minecraftforge.common.config.ConfigManager;
//import net.minecraftforge.fml.client.event.ConfigChangedEvent;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//
//@Mod.EventBusSubscriber(modid = EntityDistance.MOD_ID)
//public class ConfigEventHandler {
//
//    @SubscribeEvent
//    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
//        if (event.getModID().equals(EntityDistance.MOD_ID)) {
//            ConfigManager.sync(EntityDistance.MOD_ID, Config.Type.INSTANCE);
//        }
//    }
//}
