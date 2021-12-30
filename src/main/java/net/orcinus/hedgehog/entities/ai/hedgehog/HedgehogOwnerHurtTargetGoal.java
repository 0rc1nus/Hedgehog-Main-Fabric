package net.orcinus.hedgehog.entities.ai.hedgehog;

import net.minecraft.entity.ai.goal.AttackWithOwnerGoal;
import net.orcinus.hedgehog.entities.HedgehogEntity;

public class HedgehogOwnerHurtTargetGoal extends AttackWithOwnerGoal {
    private final HedgehogEntity hedgehog;

    public HedgehogOwnerHurtTargetGoal(HedgehogEntity entity) {
        super(entity);
        this.hedgehog = entity;
    }

    @Override
    public boolean canStart() {
        return super.canStart() && this.hedgehog.isAlive() && this.hedgehog.getAssistanceTicks() > 0;
    }

    @Override
    public boolean shouldContinue() {
        return super.shouldContinue() && this.hedgehog.isAlive() && this.hedgehog.getAssistanceTicks() > 0;
    }

}
