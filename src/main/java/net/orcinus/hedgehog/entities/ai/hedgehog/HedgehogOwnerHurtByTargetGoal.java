package net.orcinus.hedgehog.entities.ai.hedgehog;

import net.minecraft.entity.ai.goal.TrackOwnerAttackerGoal;
import net.orcinus.hedgehog.entities.HedgehogEntity;

public class HedgehogOwnerHurtByTargetGoal extends TrackOwnerAttackerGoal {
    private final HedgehogEntity hedgehog;

    public HedgehogOwnerHurtByTargetGoal(HedgehogEntity animal) {
        super(animal);
        this.hedgehog = animal;
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
