package net.orcinus.hedgehog.entities.ai.hedgehog;

import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.orcinus.hedgehog.entities.HedgehogEntity;

public class HedgehogBreedGoal extends AnimalMateGoal {
    private final HedgehogEntity hedgehog;

    public HedgehogBreedGoal(HedgehogEntity hedgehog, double speed) {
        super(hedgehog, speed);
        this.hedgehog = hedgehog;
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
