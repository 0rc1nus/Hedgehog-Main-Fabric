package net.orcinus.hedgehog.entities.ai.hedgehog;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.orcinus.hedgehog.entities.HedgehogEntity;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class HedgehogEatSpiderEyeGoal extends Goal {
    private final HedgehogEntity hedgehog;
    private int eatingTicks;

    public HedgehogEatSpiderEyeGoal(HedgehogEntity entity) {
        this.hedgehog = entity;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    @Override
    public boolean canStart() {
        List<ItemEntity> list = this.hedgehog.world.getEntitiesByClass(ItemEntity.class, this.hedgehog.getBoundingBox().expand(8.0D, 8.0D, 8.0D), itemEntity -> !itemEntity.cannotPickup() && itemEntity.isAlive() && itemEntity.getStack().isOf(Items.SPIDER_EYE));
        return !list.isEmpty() && !this.hedgehog.isAnointed() && !this.hedgehog.hasPotion() && this.hedgehog.getScaredTicks() == 0;
    }

    @Override
    public boolean shouldContinue() {
        List<ItemEntity> list = this.hedgehog.world.getEntitiesByClass(ItemEntity.class, this.hedgehog.getBoundingBox().expand(8.0D, 8.0D, 8.0D), itemEntity -> !itemEntity.cannotPickup() && itemEntity.isAlive() && itemEntity.getStack().isOf(Items.SPIDER_EYE));
        return !list.isEmpty() && !this.hedgehog.isAnointed() && !this.hedgehog.hasPotion() && this.hedgehog.getScaredTicks() == 0;
    }

    @Override
    public void start() {
        List<ItemEntity> list = this.hedgehog.world.getEntitiesByClass(ItemEntity.class, this.hedgehog.getBoundingBox().expand(8.0D, 8.0D, 8.0D), itemEntity -> !itemEntity.cannotPickup() && itemEntity.isAlive() && itemEntity.getStack().isOf(Items.SPIDER_EYE));
        if (!list.isEmpty()) {
            this.eatingTicks = 60;
            this.hedgehog.getNavigation().startMovingTo(list.get(0), 1.2F);
        }
    }

    @Override
    public void tick() {
        World world = this.hedgehog.world;
        List<ItemEntity> list = world.getEntitiesByClass(ItemEntity.class, this.hedgehog.getBoundingBox().expand(8.0D, 8.0D, 8.0D), itemEntity -> !itemEntity.cannotPickup() && itemEntity.isAlive() && itemEntity.getStack().isOf(Items.SPIDER_EYE));
        if (!list.isEmpty()) {
            ItemEntity item = list.get(0);
            this.hedgehog.getLookControl().lookAt(item);
            this.hedgehog.getNavigation().startMovingTo(item, 1.2F);
            double distance = this.hedgehog.distanceTo(item);
            if (distance < 2.0D) {
                if (this.eatingTicks > 0) {
                    this.eatingTicks--;
                    if (!world.isClient()) {
                        world.emitGameEvent(GameEvent.EAT, this.hedgehog.getCameraBlockPos());
                    }
                    if (this.eatingTicks % 5 == 0) {
                        this.hedgehog.getLookControl().lookAt(item);
                        Random random = this.hedgehog.getRandom();
                        this.hedgehog.playSound(SoundEvents.ENTITY_GENERIC_EAT, 0.5F + 0.5F * (float) random.nextInt(2), (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                        for(int i = 0; i < UniformIntProvider.create(12, 20).get(random); i++) {
                            Vec3d vec3 = new Vec3d(((double) random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, ((double) random.nextFloat() - 0.5D) * 0.1D);
                            vec3 = vec3.rotateX(-this.hedgehog.getPitch() * ((float)Math.PI / 180F));
                            vec3 = vec3.rotateY(-this.hedgehog.getYaw() * ((float)Math.PI / 180F));
                            double d0 = (double)(-random.nextFloat()) * 0.6D - 0.3D;
                            Vec3d vec31 = new Vec3d(((double) random.nextFloat() - 0.5D) * 0.8D, d0, 1.0D + ((double) random.nextFloat() - 0.5D) * 0.4D);
                            vec31 = vec31.add(this.hedgehog.getX(), this.hedgehog.getEyeY(), this.hedgehog.getZ());
                            ((ServerWorld)this.hedgehog.world).spawnParticles(new ItemStackParticleEffect(ParticleTypes.ITEM, item.getStack()), vec31.x, vec31.y, vec31.z, 1, vec3.x, vec3.y + 0.05D, vec3.z, 0.5F);
                        }
                    }
                }
                if (this.eatingTicks == 0) {
                    this.hedgehog.setAnointed(true);
                    item.getStack().decrement(1);
                }
            }
        }
    }
}
