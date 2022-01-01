package net.orcinus.hedgehog.init;

import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.orcinus.hedgehog.Hedgehog;

import java.util.LinkedHashMap;
import java.util.Map;

public class HItems {
    private static final Map<Identifier, Item> ITEMS = new LinkedHashMap<>();

    public static final Item KIWI = registerItem("kiwi", (Item)new AliasedBlockItem(HBlocks.KIWI_VINES, new Item.Settings().food(HFoodComponents.KIWI).group(ItemGroup.FOOD)));
    public static final Item HEDGEHOG_SPAWN_EGG = registerItem("hedgehog_spawn_egg", new SpawnEggItem(HEntities.HEDGEHOG, 5654847, 13352614, new Item.Settings().group(ItemGroup.MISC)));

    public static <I extends Item> I registerItem(String name, I item) {
        ITEMS.put(Hedgehog.ID(name), item);
        return item;
    }

    public static void init() {
        for (Identifier identifier : ITEMS.keySet()) {
            Registry.register(Registry.ITEM, identifier, ITEMS.get(identifier));
        }
    }

}
