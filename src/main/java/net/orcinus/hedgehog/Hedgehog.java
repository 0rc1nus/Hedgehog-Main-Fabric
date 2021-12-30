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
import net.minecraft.village.TradeOffer;
import net.minecraft.world.biome.BiomeKeys;
import net.orcinus.hedgehog.entities.HedgehogEntity;
import net.orcinus.hedgehog.init.HBlocks;
import net.orcinus.hedgehog.init.HEntities;
import net.orcinus.hedgehog.init.HItems;

public class Hedgehog implements ModInitializer {
    public static final String MODID = "hedgehog";

    @Override
    public void onInitialize() {
        HBlocks.init();
        HItems.init();
        Reflection.initialize(HEntities.class);

        /*
         * Registers Attributes with Fabric API
         */
        FabricDefaultAttributeRegistry.register(HEntities.HEDGEHOG, HedgehogEntity.createHedgehogAttributes());

        /*
         * Registers Wandering Traders custom offers with Fabric API
         */
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, 1), ItemStack.EMPTY, new ItemStack(HItems.KIWI), 2, 1, 0.0F));
        });

        /*
         * Registers Entity Spawnings with Fabric API
         */
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.MEADOW), SpawnGroup.CREATURE, HEntities.HEDGEHOG, 12, 1, 3);

    }

    public static Identifier ID(String name) {
        return new Identifier(MODID, name);
    }

}
