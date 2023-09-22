package com.ydmsama.entitydistance.handlers;

import com.ydmsama.entitydistance.EntityDistance;
import com.ydmsama.entitydistance.utils.EntityTrackerUtils;
import net.minecraft.entity.EntityTracker;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = EntityDistance.MOD_ID)
public class WorldLoadHandler {

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        if (!(event.getWorld() instanceof WorldServer)) {
            return;
        }

        WorldServer worldServer = (WorldServer) event.getWorld();
        EntityTracker tracker = worldServer.getEntityTracker();
        EntityTrackerUtils.updateGlobalEntityDistance(worldServer, tracker);
    }
}
