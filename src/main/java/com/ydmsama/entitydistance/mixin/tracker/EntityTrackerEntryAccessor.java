package com.ydmsama.entitydistance.mixin.tracker;

import net.minecraft.entity.EntityTrackerEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityTrackerEntry.class)
public interface EntityTrackerEntryAccessor {

    @Accessor("range")
    void setRange(int newRange);
}
