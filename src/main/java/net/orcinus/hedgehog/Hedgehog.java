package net.orcinus.hedgehog;

import com.google.common.reflect.Reflection;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.village.TradeOffer;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.orcinus.hedgehog.entities.HedgehogEntity;
import net.orcinus.hedgehog.init.HedgehogBlocks;
import net.orcinus.hedgehog.init.HedgehogEntities;
import net.orcinus.hedgehog.init.HedgehogFeatures;
import net.orcinus.hedgehog.init.HedgehogItems;
import net.orcinus.hedgehog.init.HedgehogPlacements;
import net.orcinus.hedgehog.init.HedgehogSoundEvents;

public class Hedgehog implements ModInitializer {
    public static final String MODID = "hedgehog";

    @Override
    public void onInitialize() {
        HedgehogBlocks.init();
        HedgehogItems.init();

        Reflection.initialize(HedgehogEntities.class);

        /*
         * Registers Attributes with Fabric API
         */
        FabricDefaultAttributeRegistry.register(HedgehogEntities.HEDGEHOG, HedgehogEntity.createHedgehogAttributes());

        /*
         * Registers Wandering Traders custom offers with Fabric API
         */
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, 1), ItemStack.EMPTY, new ItemStack(HedgehogItems.KIWI), 2, 1, 0.0F));
        });

        HedgehogFeatures.init();
        HedgehogSoundEvents.init();

        /*
         * Registers Entity Spawnings with Fabric API
         */
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.MEADOW), SpawnGroup.CREATURE, HedgehogEntities.HEDGEHOG, 40, 3, 6);

        /*
         * Adds the biome features into the meadow biome
         */
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW), GenerationStep.Feature.VEGETAL_DECORATION, BuiltinRegistries.PLACED_FEATURE.getKey(HedgehogPlacements.FALLEN_BIRCH.value()).get());
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW), GenerationStep.Feature.VEGETAL_DECORATION, BuiltinRegistries.PLACED_FEATURE.getKey(HedgehogPlacements.HEDGEHOG_BIRCH_TREE.value()).get());

    }

    public static Identifier ID(String name) {
        return new Identifier(MODID, name);
    }

}
