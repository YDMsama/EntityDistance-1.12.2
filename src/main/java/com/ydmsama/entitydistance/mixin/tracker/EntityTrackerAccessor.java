package com.ydmsama.entitydistance.mixin.tracker;

import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.EntityTrackerEntry;
import java.util.Set;

import net.minecraft.util.IntHashMap;
import net.minecraft.world.WorldServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityTracker.class)
public interface EntityTrackerAccessor {

    @Accessor("entries")
    Set<EntityTrackerEntry> getEntries();
    @Accessor("trackedEntityHashTable")
    IntHashMap<EntityTrackerEntry> getTrackedEntityHashTable();
    @Accessor("world")
    WorldServer getWorldServer();
}