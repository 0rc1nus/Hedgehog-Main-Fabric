package net.orcinus.hedgehog.entities.ai.hedgehog;

import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.orcinus.hedgehog.entities.HedgehogEntity;
import net.orcinus.hedgehog.init.HedgehogItems;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class HedgehogBegGoal extends Goal {
    private final HedgehogEntity hedgehog;
    @Nullable
    private PlayerEntity player;
    private final World world;
    private final float lookDistance;
    private int lookTime;
    private final TargetPredicate begTargeting;

    public HedgehogBegGoal(HedgehogEntity hedgehog, float distance) {
        this.hedgehog = hedgehog;
        this.world = hedgehog.world;
        this.lookDistance = distance;
        this.begTargeting = TargetPredicate.createNonAttackable().setBaseMaxDistance(distance);
        this.setControls(EnumSet.of(Control.LOOK));
    }

    @Override
    public boolean canStart() {
        this.player = this.world.getClosestPlayer(this.begTargeting, this.hedgehog);
        return this.player != null && this.playerHoldingInteresting(this.player) && this.hedgehog.getScaredTicks() == 0;
    }

    @Override
    public boolean shouldContinue() {
        if (!this.player.isAlive()) {
            return false;
        } else if (this.hedgehog.squaredAttackRange(this.player) > (double)(this.lookDistance * this.lookDistance)) {
            return false;
        } else {
            return this.lookTime > 0 && this.playerHoldingInteresting(this.player) && this.hedgehog.getScaredTicks() == 0;
        }
    }

    @Override
    public void start() {
        this.hedgehog.setIsInterested(true);
        this.lookTime = this.getTickCount(40 + this.hedgehog.getRandom().nextInt(40));
    }

    @Override
    public void stop() {
        this.hedgehog.setIsInterested(false);
        this.player = null;
    }

    @Override
    public void tick() {
        this.hedgehog.getLookControl().lookAt(this.player.getX(), this.player.getEyeY(), this.player.getZ(), 10.0F, (float)this.hedgehog.getMaxLookPitchChange());
        this.lookTime--;
    }

    private boolean playerHoldingInteresting(PlayerEntity player) {
        for(Hand interactionhand : Hand.values()) {
            ItemStack itemstack = player.getStackInHand(interactionhand);
            if (this.hedgehog.isTamed() && itemstack.isOf(HedgehogItems.KIWI)) {
                return true;
            }

            if (this.hedgehog.isBreedingItem(itemstack)) {
                return true;
            }
        }

        return false;
    }
}
