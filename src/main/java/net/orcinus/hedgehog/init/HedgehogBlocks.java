package net.orcinus.hedgehog.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.orcinus.hedgehog.Hedgehog;
import net.orcinus.hedgehog.blocks.KiwiVinesBlock;

import java.util.LinkedHashMap;
import java.util.Map;

public class HedgehogBlocks {
    private static final Map<Identifier, Block> BLOCKS = new LinkedHashMap<>();

    public static final Block KIWI_VINES = registerBlock("kiwi_vines", new KiwiVinesBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.GREEN).strength(0.2F).sounds(BlockSoundGroup.CAVE_VINES).noCollision()));

    private static <B extends Block> B registerBlock(String name, B block) {
        BLOCKS.put(Hedgehog.ID(name), block);
        return block;
    }

    public static void init() {
        for (Identifier identifier : BLOCKS.keySet()) {
            Registry.register(Registry.BLOCK, identifier, BLOCKS.get(identifier));
        }
    }

}
