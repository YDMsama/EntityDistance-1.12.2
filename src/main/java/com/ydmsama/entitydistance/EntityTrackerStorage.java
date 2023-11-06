package com.ydmsama.entitydistance;

import net.minecraft.entity.Entity;

import java.util.HashMap;

public class EntityTrackerStorage {
    private final HashMap<Entity, Integer> initRangeMap = new HashMap<>();
    private final HashMap<Entity, Integer> initMaxRangeMap = new HashMap<>();

    public void storeInitValues(Entity entity, int initRange, int initMaxRange) {
        initRangeMap.put(entity, initRange);
        initMaxRangeMap.put(entity, initMaxRange);
    }

    public Integer getInitRange(Entity entity) {
        return initRangeMap.getOrDefault(entity, null);
    }

    public Integer getInitMaxRange(Entity entity) {
        return initMaxRangeMap.getOrDefault(entity, null);
    }

//    public void removeEntry(Entity entity) {
//        initRangeMap.remove(entity);
//        initMaxRangeMap.remove(entity);
//    }
}
