package com.ydmsama.entitydistance.utils;

import com.google.common.collect.Sets;
import com.ydmsama.entitydistance.mixin.tracker.EntityTrackerAccessor;
import com.ydmsama.entitydistance.mixin.tracker.EntityTrackerEntryAccessor;
import com.ydmsama.entitydistance.mixin.tracker.WorldAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.util.IntHashMap;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.ydmsama.entitydistance.EntityDistance.storage;

public class EntityTrackerUtils {
    public static void updateGlobalEntityDistance(WorldServer worldServer, EntityTracker tracker) {
        EntityTrackerAccessor accessor = (EntityTrackerAccessor) tracker;
        Collection<EntityTrackerEntry> entries = accessor.getEntries();
        IntHashMap<EntityTrackerEntry> trackedEntityHashTable = accessor.getTrackedEntityHashTable();
        if (entries == null || trackedEntityHashTable == null) {
            return;
        }

        Set<EntityTrackerEntry> newEntries = Sets.newHashSet();

        Iterator<EntityTrackerEntry> iterator = entries.iterator();

        List<Entity> unloadedEntityList = ((WorldAccessor) worldServer).getUnloadedEntityList();
        List<Entity> loadedEntityList = ((WorldAccessor) worldServer).getloadedEntityList();

        while (iterator.hasNext()) {
            EntityTrackerEntry oldEntry = iterator.next();
            Entity entity = ((EntityTrackerEntryAccessor) oldEntry).gettrackedEntity();
            EntityRegistry.EntityRegistration er = net.minecraftforge.fml.common.registry.EntityRegistry.instance().lookupModSpawn(entity.getClass(), true);
            assert er != null;
            EntityTrackerEntry newEntry = new EntityTrackerEntry(
                    entity,
                    storage.getInitRange(entity),
                    storage.getInitMaxRange(entity),
                    ((EntityTrackerEntryAccessor) oldEntry).getupdateFrequency(),
                    ((EntityTrackerEntryAccessor) oldEntry).getsendVelocityUpdates()
            );

            newEntries.add(newEntry);
        }

        for (Entity entity : loadedEntityList) {
            tracker.untrack(entity);
        }

        entries.clear();
        trackedEntityHashTable.clearMap();

        for (EntityTrackerEntry newEntry : newEntries) {
            Entity entity = ((EntityTrackerEntryAccessor) newEntry).gettrackedEntity();
            entries.add(newEntry);
            trackedEntityHashTable.addKey(entity.getEntityId(), newEntry);
            newEntry.updatePlayerEntities(accessor.getWorldServer().playerEntities);
        }
    }
}