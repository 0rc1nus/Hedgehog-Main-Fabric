package net.orcinus.hedgehog.init;

import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.orcinus.hedgehog.Hedgehog;

public class HedgehogConfiguredFeatures {

    public static final ConfiguredFeature<?, ?> FALLEN_BIRCH = HedgehogFeatures.FALLEN_BIRCH.configure(DefaultFeatureConfig.INSTANCE);
    public static final ConfiguredFeature<?, ?> HEDGEHOG_BIRCH_TREE = HedgehogFeatures.HEDGEHOG_BIRCH_TREE.configure(DefaultFeatureConfig.INSTANCE);

    public static void init() {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, Hedgehog.ID("fallen_birch"), FALLEN_BIRCH);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, Hedgehog.ID("hedgehog_birch_tree"), HEDGEHOG_BIRCH_TREE);
    }

}
