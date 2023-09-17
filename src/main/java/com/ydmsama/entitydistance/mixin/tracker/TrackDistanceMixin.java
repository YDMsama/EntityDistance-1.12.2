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
//public class TrackDistanceMixin implements ITrackDistanceMixin {
    public class TrackDistanceMixin {
    @Shadow private int maxRange;
    @Unique
    private int entityDistance_1_12_2$initRange;
    @Unique
    private int entityDistance_1_12_2$initMaxRange;
    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(Entity entityIn, int rangeIn, int maxRangeIn, int updateFrequencyIn, boolean sendVelocityUpdatesIn, CallbackInfo ci) {
        ((EntityTrackerEntryAccessor) this).setRange((int) (rangeIn * ModConfig.trackDistanceMultiplier));
        ((EntityTrackerEntryAccessor) this).setmaxRange((int) (maxRangeIn * ModConfig.trackDistanceMultiplier));
        EntityDistance.storage.storeInitValues(entityIn, rangeIn, maxRangeIn);
    }

//    @Override
//    public int entityDistance_1_12_2$getInitRange() {
//        return this.entityDistance_1_12_2$initRange;
//    }
//
//    @Override
//    public int entityDistance_1_12_2$getInitMaxRange() {
//        return this.entityDistance_1_12_2$initMaxRange;
//    }
}
