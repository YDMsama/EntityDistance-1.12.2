//package com.yourname.modid.mixin;
//
//import net.minecraft.client.renderer.EntityRenderer;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
//
//@Mixin(EntityRenderer.class)
//public class FarClippingPlaneMixin {
//
//    @Inject(
//            method = "setupCameraTransform",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V",
//                    ordinal = 0
//            ),
//            locals = LocalCapture.CAPTURE_FAILHARD
//    )
//    private void modifyFarClippingPlane(float partialTicks, int pass, CallbackInfo ci, float f, float f1, float f2) {
//        // 在这里修改 f2，例如，将其乘以2
//        f2 = f2 * 2;
//
//        // 重新调用 gluPerspective 使用新的 f2 值
//        // 在这个例子里，你可能需要访问其他局部变量以传给 gluPerspective
//        // 或者直接修改目标代码，使其使用新的 f2 值
//    }
//}
