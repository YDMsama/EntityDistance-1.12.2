package com.ydmsama.entitydistance.mixin.tracker;

import com.ydmsama.entitydistance.config.ModConfig;
import com.ydmsama.entitydistance.mixin.tracker.EntityTrackerEntryAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTrackerEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityTrackerEntry.class)
public class TrackDistanceMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(Entity entityIn, int rangeIn, int maxRangeIn, int updateFrequencyIn, boolean sendVelocityUpdatesIn, CallbackInfo ci) {
        ((EntityTrackerEntryAccessor)this).setRange((int) (rangeIn * ModConfig.trackDistanceMultiplier));
//        ((EntityTrackerEntryAccessor)this).setRange(rangeIn * 2);
    }
}
