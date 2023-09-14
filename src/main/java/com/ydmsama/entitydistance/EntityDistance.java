package com.ydmsama.entitydistance;

import com.google.common.collect.Sets;
import com.ydmsama.entitydistance.client.gui.CustomGuiOptions;
//import com.ydmsama.entitydistance.client.gui.CustomGuiVideoSettings;
import com.ydmsama.entitydistance.config.ModConfig;
import com.ydmsama.entitydistance.mixin.gui.GuiOptionsAccessor;
//import com.ydmsama.entitydistance.mixin.gui.GuiVideoSettingsAccessor;
import com.ydmsama.entitydistance.mixin.tracker.EntityTrackerAccessor;
import com.ydmsama.entitydistance.mixin.tracker.EntityTrackerEntryAccessor;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiVideoSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IntHashMap;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import static com.ydmsama.entitydistance.DefaultValues.DefaultMaxRange;
import static com.ydmsama.entitydistance.DefaultValues.DefaultRange;


@Mod(modid = EntityDistance.MOD_ID)
public class EntityDistance {
    public static final String MOD_ID = "entitydistance";

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        System.out.println("Hello world!");
        MinecraftForge.EVENT_BUS.register(new ConfigEventHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerLoginHandler());
        MinecraftForge.EVENT_BUS.register(new GuiOpenHandler());
    }

    @Mod.EventBusSubscriber(modid = EntityDistance.MOD_ID)
    public static class ConfigEventHandler {
        @SubscribeEvent
        public static void onConfigChangedEvent(OnConfigChangedEvent event) {
            if (event.getModID().equals(EntityDistance.MOD_ID)) {
                ConfigManager.sync(EntityDistance.MOD_ID, Config.Type.INSTANCE);

                MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
                if (server == null) {
                    return;
                }
                WorldServer worldServer = server.getWorld(DimensionType.OVERWORLD.getId());
                if (worldServer == null) {
                    return;
                }
                EntityTracker tracker = worldServer.getEntityTracker();
                if (tracker == null) {
                    return;
                }
                EntityTrackerAccessor accessor = (EntityTrackerAccessor) tracker;
                Collection<EntityTrackerEntry> entries = accessor.getEntries();
                IntHashMap<EntityTrackerEntry> TrackedEntityHashTable = accessor.getTrackedEntityHashTable();
                if (entries == null || TrackedEntityHashTable == null) {
                    return;
                }
                Set<EntityTrackerEntry> newEntries = Sets.<EntityTrackerEntry>newHashSet();

                Iterator<EntityTrackerEntry> iterator = entries.iterator();

                while (iterator.hasNext()) {
                    EntityTrackerEntry oldEntry = iterator.next();
                    Entity entity = ((EntityTrackerEntryAccessor) oldEntry).gettrackedEntity();

                    EntityTrackerEntry newEntry = new EntityTrackerEntry(
                            entity,
                            (int) (DefaultRange * ModConfig.trackDistanceMultiplier),
                            (int) (DefaultMaxRange * ModConfig.trackDistanceMultiplier),
                            ((EntityTrackerEntryAccessor) oldEntry).getupdateFrequency(),
                            ((EntityTrackerEntryAccessor) oldEntry).getsendVelocityUpdates()
                    );

                    newEntries.add(newEntry);
                }

                entries.clear();
                TrackedEntityHashTable.clearMap();

                for (EntityTrackerEntry newEntry : newEntries) {
                    Entity entity = ((EntityTrackerEntryAccessor) newEntry).gettrackedEntity();
                    entries.add(newEntry);
                    TrackedEntityHashTable.addKey(entity.getEntityId(), newEntry);
                    newEntry.updatePlayerEntities(accessor.getWorldServer().playerEntities);
                }
//                Set<EntityTrackerEntry> newEntries = Sets.<EntityTrackerEntry>newHashSet();
//                for (EntityTrackerEntry entry : entries) {
//                    Entity entity = ((EntityTrackerEntryAccessor) entry).gettrackedEntity();
//                    EntityTrackerEntry newEntry = new EntityTrackerEntry(
//                            entity,
//                            (int) (DefaultRange * ModConfig.trackDistanceMultiplier),
//                            (int) (DefaultMaxRange * ModConfig.trackDistanceMultiplier),
//                            ((EntityTrackerEntryAccessor) entry).getupdateFrequency(),
//                            ((EntityTrackerEntryAccessor) entry).getsendVelocityUpdates()
//                    );
//                    newEntries.add(newEntry);
//                }
//                entries.clear();
//                entries.addAll(newEntries);
//
//                TrackedEntityHashTable.clearMap();
//                Iterator<EntityTrackerEntry> iterator = entries.iterator();
//                while (iterator.hasNext()) {
//                    EntityTrackerEntry entry = iterator.next();
//                    Entity entity = ((EntityTrackerEntryAccessor) entry).gettrackedEntity();
//                    iterator.remove();
//                    TrackedEntityHashTable.addKey(entity.getEntityId(), entry);
//                    entry.updatePlayerEntities(accessor.getWorldServer().playerEntities);
//                }

            }
        }
    }
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
            if (tracker == null) {
                return;
            }
            EntityTrackerAccessor accessor = (EntityTrackerAccessor) tracker;
            Collection<EntityTrackerEntry> entries = accessor.getEntries();
            IntHashMap<EntityTrackerEntry> TrackedEntityHashTable = accessor.getTrackedEntityHashTable();
            if (entries == null || TrackedEntityHashTable == null) {
                return;
            }

            Set<EntityTrackerEntry> newEntries = Sets.<EntityTrackerEntry>newHashSet();

            Iterator<EntityTrackerEntry> iterator = entries.iterator();

            while (iterator.hasNext()) {
                EntityTrackerEntry oldEntry = iterator.next();
                Entity entity = ((EntityTrackerEntryAccessor) oldEntry).gettrackedEntity();

                EntityTrackerEntry newEntry = new EntityTrackerEntry(
                        entity,
                        (int) (DefaultRange * ModConfig.trackDistanceMultiplier),
                        (int) (DefaultMaxRange * ModConfig.trackDistanceMultiplier),
                        ((EntityTrackerEntryAccessor) oldEntry).getupdateFrequency(),
                        ((EntityTrackerEntryAccessor) oldEntry).getsendVelocityUpdates()
                );

                newEntries.add(newEntry);
            }

            entries.clear();
            TrackedEntityHashTable.clearMap();

            for (EntityTrackerEntry newEntry : newEntries) {
                Entity entity = ((EntityTrackerEntryAccessor) newEntry).gettrackedEntity();
                entries.add(newEntry);
                TrackedEntityHashTable.addKey(entity.getEntityId(), newEntry);
                newEntry.updatePlayerEntities(accessor.getWorldServer().playerEntities);
            }

//            Set<EntityTrackerEntry> newEntries = Sets.<EntityTrackerEntry>newHashSet();
//            for (EntityTrackerEntry entry : entries) {
//                Entity entity = ((EntityTrackerEntryAccessor) entry).gettrackedEntity();
//                EntityTrackerEntry newEntry = new EntityTrackerEntry(
//                        entity,
//                        (int) (DefaultRange * ModConfig.trackDistanceMultiplier),
//                        (int) (DefaultMaxRange * ModConfig.trackDistanceMultiplier),
//                        ((EntityTrackerEntryAccessor) entry).getupdateFrequency(),
//                        ((EntityTrackerEntryAccessor) entry).getsendVelocityUpdates()
//                );
//                newEntries.add(newEntry);
//            }
//            entries.clear();
//            entries.addAll(newEntries);
//
//            TrackedEntityHashTable.clearMap();
//            Iterator<EntityTrackerEntry> iterator = entries.iterator();
//            while (iterator.hasNext()) {
//                EntityTrackerEntry entry = iterator.next();
//                Entity entity = ((EntityTrackerEntryAccessor) entry).gettrackedEntity();
//                iterator.remove();
//                TrackedEntityHashTable.addKey(entity.getEntityId(), entry);
//                entry.updatePlayerEntities(accessor.getWorldServer().playerEntities);
//            }

        }

    }

//    @Mod.EventBusSubscriber(modid = EntityDistance.MOD_ID)
//    public class GuiOpenHandler {
//        @SubscribeEvent
//        public void onGuiOpen(GuiOpenEvent event) {
//            if (event.getGui() instanceof GuiVideoSettings) {
//                GuiVideoSettings original = (GuiVideoSettings) event.getGui();
//                GuiVideoSettingsAccessor accessor = (GuiVideoSettingsAccessor) original;
//                event.setGui(new CustomGuiVideoSettings(accessor.getParentGuiScreen(), accessor.getGuiGameSettings()));
//            }
//        }
//    }
    @Mod.EventBusSubscriber(modid = EntityDistance.MOD_ID)
    public class GuiOpenHandler {
        @SubscribeEvent
        public void onGuiOpen(GuiOpenEvent event) {
            if (event.getGui() instanceof GuiOptions) {
                GuiOptions original = (GuiOptions) event.getGui();
                GuiOptionsAccessor accessor = (GuiOptionsAccessor) original;
                event.setGui(new CustomGuiOptions(accessor.getLastScreen(), accessor.getSettings()));
            }
        }
    }
}
