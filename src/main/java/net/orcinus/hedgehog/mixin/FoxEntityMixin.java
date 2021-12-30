package net.orcinus.hedgehog.mixin;

import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.passive.FoxEntity;
import net.orcinus.hedgehog.entities.HedgehogEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoxEntity.class)
public class FoxEntityMixin {

    @Inject(at = @At("HEAD"), method = "initGoals")
    public void initGoals(CallbackInfo ci) {
        FoxEntity $this = FoxEntity.class.cast(this);
        $this.goalSelector.add(2, new ActiveTargetGoal<>($this, HedgehogEntity.class, true, (entity -> !entity.isBaby())) {
            @Override
            public boolean shouldContinue() {
                if (this.target != null) {
                    return this.target instanceof HedgehogEntity && (!this.target.isBaby() && ((HedgehogEntity) this.target).getScaredTicks() == 0);
                } else {
                    return false;
                }
            }
        });
    }

}
