package net.orcinus.hedgehog.entities.ai.hedgehog;

import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.orcinus.hedgehog.entities.HedgehogEntity;

public class HedgehogRandomLookAroundGal extends LookAroundGoal {
    private final HedgehogEntity hedgehog;

    public HedgehogRandomLookAroundGal(HedgehogEntity entity) {
        super(entity);
        this.hedgehog = entity;
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
