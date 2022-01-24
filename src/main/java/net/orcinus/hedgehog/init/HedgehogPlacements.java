package net.orcinus.hedgehog.init;

import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.RarityFilterPlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.orcinus.hedgehog.Hedgehog;

public class HedgehogPlacements {

    public static final PlacedFeature FALLEN_BIRCH = HedgehogConfiguredFeatures.FALLEN_BIRCH.withPlacement(RarityFilterPlacementModifier.of(40), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final PlacedFeature HEDGEHOG_BIRCH_TREE = HedgehogConfiguredFeatures.HEDGEHOG_BIRCH_TREE.withPlacement(RarityFilterPlacementModifier.of(40), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static void init() {
        Registry.register(BuiltinRegistries.PLACED_FEATURE, Hedgehog.ID("fallen_birch"), FALLEN_BIRCH);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, Hedgehog.ID("hedgehog_birch_tree"), HEDGEHOG_BIRCH_TREE);
    }

}
