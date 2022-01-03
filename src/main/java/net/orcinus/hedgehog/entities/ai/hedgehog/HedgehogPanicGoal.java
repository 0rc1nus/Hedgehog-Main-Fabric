package net.orcinus.hedgehog.entities.ai.hedgehog;

import com.google.common.collect.Lists;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.orcinus.hedgehog.entities.HedgehogEntity;

import java.util.List;

public class HedgehogPanicGoal extends Goal {
    private final HedgehogEntity hedgehog;

    public HedgehogPanicGoal(HedgehogEntity hedgehog) {
        this.hedgehog = hedgehog;
    }

    @Override
    public boolean canStart() {
        return this.hedgehog.isAlive() && this.isUserScary() != null;
    }

    @Override
    public void tick() {
        if (this.isUserScary() != null) {
            this.hedgehog.setTarget(null);
            this.hedgehog.getNavigation().stop();
            this.hedgehog.setJumping(false);
            this.hedgehog.setScaredTicks(100);
        }
    }

    private LivingEntity isUserScary() {
        List<LivingEntity> possibles = Lists.newArrayList();
        List<LivingEntity> list = this.hedgehog.world.getNonSpectatingEntities(LivingEntity.class, this.hedgehog.getBoundingBox().expand(3.0D));
        for (LivingEntity livingEntity : list) {
            if (livingEntity.isAlive()) {
                if (this.isScared(livingEntity)) {
                    possibles.add(livingEntity);
                }
            }
        }
        if (possibles.isEmpty()) return null;

        return possibles.get(hedgehog.getRandom().nextInt(possibles.size()));
    }

    private boolean isScared(LivingEntity livingEntity) {
        return livingEntity.getEquippedStack(EquipmentSlot.HEAD).isOf(Items.SKELETON_SKULL) || livingEntity.getEquippedStack(EquipmentSlot.HEAD).isOf(Items.WITHER_SKELETON_SKULL) || livingEntity.getStackInHand(Hand.MAIN_HAND).isOf(Items.MILK_BUCKET) || livingEntity.getStackInHand(Hand.OFF_HAND).isOf(Items.MILK_BUCKET);
    }
}
