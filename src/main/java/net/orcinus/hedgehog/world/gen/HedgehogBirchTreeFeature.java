package net.orcinus.hedgehog.world.gen;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.DripstoneHelper;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.orcinus.hedgehog.blocks.KiwiVinesBlock;
import net.orcinus.hedgehog.init.HedgehogBlocks;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.Random;

public class HedgehogBirchTreeFeature extends Feature<DefaultFeatureConfig> {

    public HedgehogBirchTreeFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        int height = ConstantIntProvider.create(9).get(random);
        if (!world.getBlockState(blockPos.down()).isIn(BlockTags.DIRT)) {
            return false;
        } else {
            for (int i = 0; i <= height; i++) {
                BlockPos placePos = blockPos.up(i);
                if (world.testBlockState(placePos, state -> state.isOf(HedgehogBlocks.KIWI_VINES) || state.getMaterial().isReplaceable() || state.isAir() || state.isOf(Blocks.WATER) || state.getMaterial() == Material.PLANT)) {
                    world.setBlockState(placePos, Blocks.BIRCH_LOG.getDefaultState(), 19);
                    this.generateVines(world, random, placePos);
                }
            }
            int radius = 1;
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    for (int y = -4; y <= 4; y++) {
                        BlockPos leavePos = new BlockPos(blockPos.getX() + x, blockPos.getY() + y + height, blockPos.getZ() + z);
                        if (0.4 * (x * x) + ((y * y) / 16.0) + 0.4 * (z * z) <= radius * radius) {
                            if (world.testBlockState(leavePos, DripstoneHelper::canGenerate)) {
                                BlockState state = Blocks.BIRCH_LEAVES.getDefaultState();
                                world.setBlockState(leavePos, state, 19);
                            }
                        }
                    }
                }
            }
            return true;
        }
    }

    private void generateVines(WorldAccess world, Random random, BlockPos pos) {
        BlockPos.Mutable mut = pos.mutableCopy();
        if (random.nextInt(3) == 0) {
            KiwiVinesFeature.generateVine(world, pos, random, 2);
        }
        for (Direction direction : Direction.values()) {
            BlockPos offset = mut.offset(direction);
            BlockState state = random.nextBoolean() ? HedgehogBlocks.KIWI_VINES.getDefaultState().with(KiwiVinesBlock.KIWI, true) : HedgehogBlocks.KIWI_VINES.getDefaultState();
            if (random.nextBoolean() && world.isAir(offset)) {
                world.setBlockState(offset, state.with(KiwiVinesBlock.getProperty(direction.getOpposite()), true), 2);
            }
        }
    }
}
