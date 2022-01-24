package net.orcinus.hedgehog.world.gen;

import com.mojang.serialization.Codec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.DripstoneHelper;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.orcinus.hedgehog.blocks.KiwiVinesBlock;
import net.orcinus.hedgehog.init.HedgehogBlocks;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.Random;

public class FallenBirchLogFeature extends Feature<DefaultFeatureConfig> {

    public FallenBirchLogFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        Direction direction = Direction.Type.HORIZONTAL.random(random);
        int logLength = MathHelper.nextInt(random, 3, 6);
        if (!world.getBlockState(blockPos.down()).isIn(BlockTags.DIRT)) {
            return false;
        } else {
            List<BlockPos> vinePlaceables = Lists.newArrayList();
            List<BlockPos> extraVines = Lists.newArrayList();
            BlockPos.Mutable mut = blockPos.mutableCopy();
            for (int i = 0; i <= logLength; i++) {
                if (world.getBlockState(mut.down()).getMaterial().isReplaceable() || world.testBlockState(mut.down(), DripstoneHelper::canGenerate)) {
                    mut.move(Direction.DOWN);
                    if (world.getBlockState(mut).getMaterial().isReplaceable() || world.testBlockState(mut, DripstoneHelper::canGenerate)) {
                        world.setBlockState(mut, Blocks.BIRCH_LOG.getDefaultState().with(PillarBlock.AXIS, direction.getAxis()), 2);
                        vinePlaceables.add(mut.toImmutable());
                    }
                }
                if (world.getBlockState(mut).getMaterial().isReplaceable() || world.testBlockState(mut, DripstoneHelper::canGenerate)) {
                    world.setBlockState(mut, Blocks.BIRCH_LOG.getDefaultState().with(PillarBlock.AXIS, direction.getAxis()), 2);
                    vinePlaceables.add(mut.toImmutable());
                }
                mut.move(direction);
            }
            BlockState kiwi = random.nextBoolean() ? HedgehogBlocks.KIWI_VINES.getDefaultState() : HedgehogBlocks.KIWI_VINES.getDefaultState().with(KiwiVinesBlock.KIWI, true);
            for (BlockPos pos : vinePlaceables) {
                for (Direction facingDirection : Direction.values()) {
                    BlockPos relative = pos.offset(facingDirection);
                    if (world.testBlockState(relative, AbstractBlock.AbstractBlockState::isAir)) {
                        if (random.nextInt(3) == 0) {
                            world.setBlockState(relative, kiwi.with(KiwiVinesBlock.getProperty(facingDirection.getOpposite()), true), 2);
                            if (random.nextInt(5) == 0){
                                KiwiVinesFeature.generateVine(world, relative, random, 2);
                            }
                        }
                        extraVines.add(relative);
                    }
                }
            }
            return true;
        }
    }
}
