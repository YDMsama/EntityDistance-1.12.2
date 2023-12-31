package com.ydmsama.entitydistance.mixin.tracker;

import com.ydmsama.entitydistance.EntityDistance;
import com.ydmsama.entitydistance.config.ModConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTrackerEntry;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityTrackerEntry.class)
    public class TrackDistanceMixin {
    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(Entity entityIn, int rangeIn, int maxRangeIn, int updateFrequencyIn, boolean sendVelocityUpdatesIn, CallbackInfo ci) {
        double multiplier = ModConfig.getEntityTrackMultiplier(entityIn);
        ((EntityTrackerEntryAccessor) this).setRange((int) (rangeIn * multiplier));
        ((EntityTrackerEntryAccessor) this).setmaxRange((int) (maxRangeIn * multiplier));
        EntityDistance.storage.storeInitValues(entityIn, rangeIn, maxRangeIn);
    }
}
