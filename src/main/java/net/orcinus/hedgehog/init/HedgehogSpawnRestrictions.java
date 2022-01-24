package net.orcinus.hedgehog.init;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.tag.BlockTags;
import net.minecraft.world.Heightmap;

import java.util.Map;

public class HedgehogSpawnRestrictions {

    public static void init(Map<EntityType<?>, SpawnRestriction.Entry> map) {
        map.put(HedgehogEntities.HEDGEHOG, new SpawnRestriction.Entry(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnRestriction.Location.ON_GROUND, (type, world, spawnReason, pos, random) -> world.getBlockState(pos.down()).isIn(BlockTags.ANIMALS_SPAWNABLE_ON) && world.getBaseLightLevel(pos, 0) > 8));
    }

}
