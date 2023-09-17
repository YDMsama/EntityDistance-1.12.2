package com.ydmsama.entitydistance.mixin.render;

import com.ydmsama.entitydistance.config.ModConfig;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class RenderDistanceMixin {
    @Shadow
    private static double renderDistanceWeight = 1.0D;
    @Shadow
    public AxisAlignedBB getEntityBoundingBox() {
        return null;
    }

    @Inject(method = "isInRangeToRenderDist", at = @At("HEAD"), cancellable = true)
    public void changeRenderDistance(double distance, CallbackInfoReturnable<Boolean> cir) {
        double d0 = getEntityBoundingBox().getAverageEdgeLength();

        if (Double.isNaN(d0)) {
            d0 = 1.0D;
        }

        d0 = d0 * 64.0D * ModConfig.renderDistanceMultiplier * renderDistanceWeight;

        boolean result = distance < d0 * d0;

        cir.setReturnValue(result);
        cir.cancel();
    }
}
