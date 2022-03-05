package net.orcinus.hedgehog.blocks;

import net.minecraft.block.AbstractLichenBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.orcinus.hedgehog.init.HedgehogItems;

import java.util.Random;

public class KiwiVinesBlock extends AbstractLichenBlock implements Fertilizable {
    public static final BooleanProperty KIWI = BooleanProperty.of("kiwi");

    public KiwiVinesBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(KIWI, false));
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.grow(world, random, pos, state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(KIWI);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(KIWI)) {
            FACING_PROPERTIES.forEach((direction, bl) -> {
                BooleanProperty booleanproperty = getProperty(direction);
                if (state.contains(booleanproperty) && state.get(booleanproperty)) {
                    Block.dropStack(world, pos, new ItemStack(HedgehogItems.KIWI, 1));
                }
            });
            world.setBlockState(pos, state.with(KIWI, false), 2);
            world.playSound(null, pos, SoundEvents.BLOCK_CAVE_VINES_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return ActionResult.SUCCESS;
        } else {
            return super.onUse(state, world, pos, player, hand, hit);
        }
    }

    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state.with(KIWI, true), 2);
    }
}
