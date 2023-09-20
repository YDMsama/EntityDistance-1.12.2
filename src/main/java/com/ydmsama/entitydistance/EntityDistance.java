package com.ydmsama.entitydistance;

import com.ydmsama.entitydistance.handlers.ConfigEventHandler;
import com.ydmsama.entitydistance.handlers.GuiOpenHandler;
import com.ydmsama.entitydistance.handlers.PlayerLoginHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = EntityDistance.MOD_ID)
public class EntityDistance {
    public static final String MOD_ID = "entitydistance";
    public static EntityTrackerStorage storage;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        storage = new EntityTrackerStorage();
        MinecraftForge.EVENT_BUS.register(new ConfigEventHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerLoginHandler());
        MinecraftForge.EVENT_BUS.register(new GuiOpenHandler());
    }
}
