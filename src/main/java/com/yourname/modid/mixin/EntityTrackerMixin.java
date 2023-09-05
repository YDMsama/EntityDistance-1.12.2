package com.yourname.modid.mixin;

import net.minecraft.world.WorldServer;
import net.minecraft.entity.EntityTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityTracker.class)
public class EntityTrackerMixin {

    @Shadow
    private int maxTrackingDistanceThreshold;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(WorldServer theWorldIn, CallbackInfo ci) {
        this.maxTrackingDistanceThreshold *= 2;  // Modify the value to be twice as large
    }
}
