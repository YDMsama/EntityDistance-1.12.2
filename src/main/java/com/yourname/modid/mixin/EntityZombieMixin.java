package com.yourname.modid.mixin;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityZombie.class)
public class EntityZombieMixin {
    @Inject(method = "<init>", at = @At("RETURN"))
    private void onEntityZombieInit(World worldIn, CallbackInfo ci) {
        // 获取当前EntityZombie实例
        EntityZombie zombie = (EntityZombie) (Object) this;

        // 设置ignoreFrustumCheck为true
        zombie.ignoreFrustumCheck = true;
        System.out.println("EntityZombie constructor mixin applied!");
    }
}
