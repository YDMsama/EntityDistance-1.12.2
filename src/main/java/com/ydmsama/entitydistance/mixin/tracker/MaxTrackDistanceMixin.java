package com.ydmsama.entitydistance.mixin.tracker;

import com.ydmsama.entitydistance.DefaultValues;
import com.ydmsama.entitydistance.config.ModConfig;
import net.minecraft.world.WorldServer;
import net.minecraft.entity.EntityTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(EntityTracker.class)
public class MaxTrackDistanceMixin {

    @Shadow
    private int maxTrackingDistanceThreshold;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(WorldServer theWorldIn, CallbackInfo ci) {
        DefaultValues.DefaultMaxRange = this.maxTrackingDistanceThreshold;
        this.maxTrackingDistanceThreshold = (int)(DefaultValues.DefaultMaxRange * ModConfig.trackDistanceMultiplier);
//        this.maxTrackingDistanceThreshold *= 2;
    }
}
