package net.orcinus.hedgehog.init;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.orcinus.hedgehog.Hedgehog;
import net.orcinus.hedgehog.world.gen.FallenBirchLogFeature;
import net.orcinus.hedgehog.world.gen.HedgehogBirchTreeFeature;
import net.orcinus.hedgehog.world.gen.KiwiVinesFeature;

public class HedgehogFeatures {

    public static final Feature<DefaultFeatureConfig> FALLEN_BIRCH = new FallenBirchLogFeature(DefaultFeatureConfig.CODEC);
    public static final Feature<DefaultFeatureConfig> HEDGEHOG_BIRCH_TREE = new HedgehogBirchTreeFeature(DefaultFeatureConfig.CODEC);
    public static final Feature<DefaultFeatureConfig> KIWI_VINES = new KiwiVinesFeature(DefaultFeatureConfig.CODEC);

    public static void init() {
        Registry.register(Registry.FEATURE, Hedgehog.ID("fallen_branch"), FALLEN_BIRCH);
        Registry.register(Registry.FEATURE, Hedgehog.ID("hedgehog_birch_tree"), HEDGEHOG_BIRCH_TREE);
        Registry.register(Registry.FEATURE, Hedgehog.ID("kiwi_vines"), KIWI_VINES);
    }

}
