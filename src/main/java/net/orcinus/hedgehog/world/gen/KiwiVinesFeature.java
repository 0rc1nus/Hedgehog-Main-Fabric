package net.orcinus.hedgehog.world.gen;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.orcinus.hedgehog.blocks.KiwiVinesBlock;
import net.orcinus.hedgehog.init.HedgehogBlocks;

public class KiwiVinesFeature extends Feature<DefaultFeatureConfig> {

    public KiwiVinesFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = context.getOrigin();
        Random random = context.getRandom();
        generateVine(world, pos, random, random.nextInt(8) - random.nextInt(5) + 2);
        return true;
    }

    public static void generateVine(WorldAccess world, BlockPos pos, Random random, int tries) {
        BlockPos.Mutable mut = pos.mutableCopy();
        for (Direction direction : Direction.Type.HORIZONTAL) {
            for (int i = 0; i < tries; i++) {
                if (!world.getBlockState(mut.down()).isSideSolidFullSquare(world, mut.down(), Direction.DOWN) || world.isAir(mut.down())) mut.move(Direction.DOWN);
                BlockState kiwi = HedgehogBlocks.KIWI_VINES.getDefaultState().with(KiwiVinesBlock.getProperty(Direction.DOWN), true);
                if (random.nextInt(2) == 0 && random.nextBoolean()) kiwi = kiwi.with(KiwiVinesBlock.KIWI, true);
                for (Direction sideDirections : Direction.values()) {
                    if (world.getBlockState(mut.offset(sideDirections)).isSideSolidFullSquare(world, mut.offset(sideDirections), sideDirections)) {
                        kiwi = kiwi.with(KiwiVinesBlock.getProperty(sideDirections), true);
                    }
                }
                if (!world.getBlockState(mut.down()).isOf(HedgehogBlocks.KIWI_VINES) && !world.isAir(mut.down(2)) && world.getBlockState(mut.down()).isSideSolidFullSquare(world, mut.down(), Direction.UP) && !world.isAir(mut.down()) && !world.getBlockState(mut).isOf(HedgehogBlocks.KIWI_VINES) && world.getBlockState(mut).getMaterial().isReplaceable() && world.getFluidState(mut).isEmpty()) {
                    world.setBlockState(mut, kiwi, 2);
                }
                mut.move(direction);
            }
        }
    }
}
