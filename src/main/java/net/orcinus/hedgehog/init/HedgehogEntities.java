package net.orcinus.hedgehog.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;
import net.orcinus.hedgehog.Hedgehog;
import net.orcinus.hedgehog.entities.HedgehogEntity;

public class HedgehogEntities {

    public static final EntityType<HedgehogEntity> HEDGEHOG = register("hedgehog", EntityType.Builder.create(HedgehogEntity::new, SpawnGroup.CREATURE).setDimensions(0.5F, 0.5F).maxTrackingRange(10));

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return Registry.register(Registry.ENTITY_TYPE, Hedgehog.ID(id), type.build(id));
    }

}
