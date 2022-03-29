package net.orcinus.hedgehog.init;

import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.orcinus.hedgehog.Hedgehog;

public class HedgehogConfiguredFeatures {

    public static final RegistryEntry<ConfiguredFeature<DefaultFeatureConfig, ?>> FALLEN_BIRCH = registerConfiguredFeature("fallen_birch", HedgehogFeatures.FALLEN_BIRCH);
    public static final RegistryEntry<ConfiguredFeature<DefaultFeatureConfig, ?>> HEDGEHOG_BIRCH_TREE = registerConfiguredFeature("hedgehog_birch_tree", HedgehogFeatures.HEDGEHOG_BIRCH_TREE);

    public static RegistryEntry<ConfiguredFeature<DefaultFeatureConfig, ?>> registerConfiguredFeature(String id, Feature<DefaultFeatureConfig> feature) {
        return registerConfiguredFeature(id, feature, FeatureConfig.DEFAULT);
    }

    public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, ?>> registerConfiguredFeature(String id, F feature, FC config) {
        return BuiltinRegistries.method_40360(BuiltinRegistries.CONFIGURED_FEATURE, Hedgehog.MODID + ":" + id, new ConfiguredFeature(feature, config));
    }

}
