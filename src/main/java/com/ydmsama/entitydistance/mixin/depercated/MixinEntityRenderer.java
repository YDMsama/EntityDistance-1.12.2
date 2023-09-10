//package com.ydmsama.entitydistance.mixin.depercated;
//
//import org.objectweb.asm.Opcodes;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.EntityRenderer;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Redirect;
//
//
//@Mixin(EntityRenderer.class)  // 将EntityRenderer替换为你实际要修改的类名
//public class MixinEntityRenderer {
//
//    @Shadow
//    private Minecraft mc;
//    @Shadow
//    private float farPlaneDistance;
//
//
//    @Redirect(
//            method = "setupCameraTransform",
//            at = @At(
//                    value = "FIELD",
//                    target = "Lnet/minecraft/client/renderer/EntityRenderer;farPlaneDistance:F",
//                    opcode = Opcodes.PUTFIELD
//            )
//    )
//    private void redirectFarPlaneDistanceAssignment(EntityRenderer instance, float value) {
//        // 自定义farPlaneDistance的值
//        value = (float) (this.mc.gameSettings.renderDistanceChunks * 16 * 2);
//        // 此处实际进行赋值
//        this.farPlaneDistance = value;
//        System.out.println("FarPlane constructor mixin applied!");
//    }
//}
