package com.ydmsama.entitydistance.mixin.tracker;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(World.class)
public interface WorldAccessor {

//    @Accessor("unloadedEntityList")
//    List<Entity> getUnloadedEntityList();
    @Accessor("loadedEntityList")
    List<Entity> getloadedEntityList();
}