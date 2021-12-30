package net.orcinus.hedgehog.entities.ai.hedgehog;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.orcinus.hedgehog.entities.HedgehogEntity;

import java.util.EnumSet;
import java.util.List;

public class HedgehogAfraidOfSkullGoal extends Goal {
    private final HedgehogEntity hedgehog;

    public HedgehogAfraidOfSkullGoal(HedgehogEntity entity) {
        this.hedgehog = entity;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public boolean canStart() {
        return this.hedgehog.getScaredTicks() == 0 && this.hedgehog.isAlive() && this.canFindSkull() != null;
    }

    @Override
    public boolean shouldContinue() {
        return this.hedgehog.isAlive() && this.canFindSkull() != null;
    }

    @Override
    public void tick() {
        this.hedgehog.setScaredTicks(150);
        this.hedgehog.getLookControl().lookAt(this.hedgehog);
        if (this.canFindSkull() != null) {
            this.hedgehog.getNavigation().stop();
        }
    }

    private BlockPos canFindSkull() {
        List<BlockPos> list = Lists.newArrayList();
        int radius = 2;
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                for (int y = -1; y <= 1; y++) {
                    BlockPos blockPos = new BlockPos(this.hedgehog.getX() + x ,this.hedgehog.getY() + y, this.hedgehog.getZ() + z);
                    if (this.isSkull(blockPos)) {
                        list.add(blockPos);
                    }
                }
            }
        }
        if (list.isEmpty()) return null;

        return list.get(this.hedgehog.getRandom().nextInt(list.size()));
    }

    private boolean isSkull(BlockPos blockPos) {
        Block block = this.hedgehog.world.getBlockState(blockPos).getBlock();
        return block == Blocks.SKELETON_SKULL || block == Blocks.WITHER_SKELETON_SKULL || block == Blocks.SKELETON_WALL_SKULL || block == Blocks.WITHER_SKELETON_WALL_SKULL;
    }
}
