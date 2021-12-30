package net.orcinus.hedgehog.entities.ai.hedgehog;

import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.orcinus.hedgehog.entities.HedgehogEntity;

public class HedgehogMeleeAttackGoal extends MeleeAttackGoal {
    private final HedgehogEntity hedgehog;

    public HedgehogMeleeAttackGoal(HedgehogEntity mob, double speed, boolean pauseWhenMobIdle) {
        super(mob, speed, pauseWhenMobIdle);
        this.hedgehog = mob;
    }

    @Override
    public boolean canStart() {
        return super.canStart() && this.hedgehog.getScaredTicks() == 0;
    }

    @Override
    public boolean shouldContinue() {
        return super.shouldContinue() && this.hedgehog.getScaredTicks() == 0;
    }

}
