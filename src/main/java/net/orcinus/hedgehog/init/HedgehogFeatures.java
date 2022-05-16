package net.orcinus.hedgehog.init;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.orcinus.hedgehog.Hedgehog;
import net.orcinus.hedgehog.world.gen.FallenBirchLogFeature;
import net.orcinus.hedgehog.world.gen.HedgehogBirchTreeFeature;
import net.orcinus.hedgehog.world.gen.KiwiVinesFeature;

import java.util.LinkedHashMap;

public class HedgehogFeatures {
    public static final LinkedHashMap<String, Feature<DefaultFeatureConfig>> FEATURES = new LinkedHashMap<>();

    public static final Feature<DefaultFeatureConfig> FALLEN_BIRCH = registerFeature("fallen_branch", new FallenBirchLogFeature(DefaultFeatureConfig.CODEC));
    public static final Feature<DefaultFeatureConfig> HEDGEHOG_BIRCH_TREE = registerFeature("hedgehog_birch_tree", new HedgehogBirchTreeFeature(DefaultFeatureConfig.CODEC));
    public static final Feature<DefaultFeatureConfig> KIWI_VINES = registerFeature("kiwi_vines", new KiwiVinesFeature(DefaultFeatureConfig.CODEC));

    public static Feature<DefaultFeatureConfig> registerFeature(String name, Feature<DefaultFeatureConfig> feature) {
        FEATURES.put(name, feature);
        return feature;
    }

    public static void init() {
        for (String id : FEATURES.keySet()) {
            Registry.register(Registry.FEATURE, Hedgehog.ID(id), FEATURES.get(id));
        }
    }

}
