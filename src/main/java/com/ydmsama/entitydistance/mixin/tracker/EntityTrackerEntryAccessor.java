package com.ydmsama.entitydistance.mixin.tracker;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTrackerEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityTrackerEntry.class)
public interface EntityTrackerEntryAccessor {

    @Accessor("range")
    void setRange(int newRange);
    @Accessor("range")
    int getRange();
    @Accessor("trackedEntity")
    Entity gettrackedEntity();
    @Accessor("maxRange")
    int getmaxRange();
    @Accessor("updateFrequency")
    int getupdateFrequency();
    @Accessor("sendVelocityUpdates")
    boolean getsendVelocityUpdates();

}
