package com.ydmsama.entitydistance.handlers;

import com.ydmsama.entitydistance.EntityDistance;
import com.ydmsama.entitydistance.utils.EntityTrackerUtils;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber(modid = EntityDistance.MOD_ID)
public class PlayerLoginHandler {
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        EntityPlayer player = event.player;
        WorldServer worldServer = (WorldServer) player.world;
        if (worldServer == null) {
            return;
        }

        EntityTracker tracker = worldServer.getEntityTracker();
        EntityTrackerUtils.updateGlobalEntityDistance(worldServer, tracker);
    }
}