package net.orcinus.hedgehog.entities.ai.hedgehog;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.orcinus.hedgehog.entities.HedgehogEntity;

public class HedgehogNearestAttackableTargetGoal<T extends LivingEntity> extends ActiveTargetGoal<T> {
    private final HedgehogEntity hedgehog;

    public HedgehogNearestAttackableTargetGoal(HedgehogEntity tameable, Class<T> targetClass, boolean checkVisibility) {
        super(tameable, targetClass, checkVisibility);
        this.hedgehog = tameable;
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
