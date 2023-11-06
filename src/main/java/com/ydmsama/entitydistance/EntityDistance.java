package com.ydmsama.entitydistance;

import com.ydmsama.entitydistance.cmd.*;
import com.ydmsama.entitydistance.handlers.ConfigEventHandler;
import com.ydmsama.entitydistance.handlers.GuiOpenHandler;
//import com.ydmsama.entitydistance.handlers.PlayerLoginHandler;
import com.ydmsama.entitydistance.handlers.WorldLoadHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = EntityDistance.MOD_ID, name = "Entity Distance", version = "1.0.1")
public class EntityDistance {
    public static final String MOD_ID = "entitydistance";
    public static EntityTrackerStorage storage;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        storage = new EntityTrackerStorage();
        MinecraftForge.EVENT_BUS.register(new ConfigEventHandler());
        MinecraftForge.EVENT_BUS.register(new WorldLoadHandler());
//        MinecraftForge.EVENT_BUS.register(new PlayerLoginHandler());
        if (event.getSide().isClient()) {
            MinecraftForge.EVENT_BUS.register(new GuiOpenHandler());
        }
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandModifyConfig());
        event.registerServerCommand(new CommandSetRenderDistance());
        event.registerServerCommand(new CommandSetTrackDistance());
        event.registerServerCommand(new CommandEntityListAdd());
        event.registerServerCommand(new CommandEntityListRemove());
        event.registerServerCommand(new CommandCustomListAdd());
        event.registerServerCommand(new CommandCustomListRemove());
    }
}
