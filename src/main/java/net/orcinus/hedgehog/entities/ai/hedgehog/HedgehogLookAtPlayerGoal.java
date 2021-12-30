package net.orcinus.hedgehog.entities.ai.hedgehog;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.orcinus.hedgehog.entities.HedgehogEntity;

public class HedgehogLookAtPlayerGoal extends LookAtEntityGoal {
    private final HedgehogEntity hedgehog;

    public HedgehogLookAtPlayerGoal(HedgehogEntity mob, Class<? extends LivingEntity> targetType, float range) {
        super(mob, targetType, range);
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
