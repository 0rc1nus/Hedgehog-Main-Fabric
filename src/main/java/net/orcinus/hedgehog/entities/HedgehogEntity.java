package net.orcinus.hedgehog.entities;

import com.google.common.collect.Maps;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.PounceAtTargetGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.SitGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.UniversalAngerGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.CaveSpiderEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.orcinus.hedgehog.Hedgehog;
import net.orcinus.hedgehog.entities.ai.hedgehog.HedgehogAfraidOfSkullGoal;
import net.orcinus.hedgehog.entities.ai.hedgehog.HedgehogBegGoal;
import net.orcinus.hedgehog.entities.ai.hedgehog.HedgehogBreedGoal;
import net.orcinus.hedgehog.entities.ai.hedgehog.HedgehogEatSpiderEyeGoal;
import net.orcinus.hedgehog.entities.ai.hedgehog.HedgehogFollowOwnerGoal;
import net.orcinus.hedgehog.entities.ai.hedgehog.HedgehogLookAtPlayerGoal;
import net.orcinus.hedgehog.entities.ai.hedgehog.HedgehogMeleeAttackGoal;
import net.orcinus.hedgehog.entities.ai.hedgehog.HedgehogNearestAttackableTargetGoal;
import net.orcinus.hedgehog.entities.ai.hedgehog.HedgehogOwnerHurtByTargetGoal;
import net.orcinus.hedgehog.entities.ai.hedgehog.HedgehogOwnerHurtTargetGoal;
import net.orcinus.hedgehog.entities.ai.hedgehog.HedgehogPanicGoal;
import net.orcinus.hedgehog.entities.ai.hedgehog.HedgehogRandomLookAroundGal;
import net.orcinus.hedgehog.init.HEntities;
import net.orcinus.hedgehog.init.HItems;
import net.orcinus.hedgehog.init.HSoundEvents;
import net.orcinus.hedgehog.mixin.MobEntityInvoker;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class HedgehogEntity extends TameableEntity implements Angerable {
    private static final TrackedData<Integer> ANGER_TIME = DataTracker.registerData(HedgehogEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> BAND_COLOR = DataTracker.registerData(HedgehogEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> SCARED_TICKS = DataTracker.registerData(HedgehogEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> POTION_TICKS = DataTracker.registerData(HedgehogEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> ASSISTANCE_TICKS = DataTracker.registerData(HedgehogEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> ANOINTED = DataTracker.registerData(HedgehogEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> IS_INSTANTANEOUS = DataTracker.registerData(HedgehogEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> IS_INTERESTED = DataTracker.registerData(HedgehogEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> EFFECT_COLOR = DataTracker.registerData(HedgehogEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final UniformIntProvider PERSISTENT_ANGER_TIME = TimeHelper.betweenSeconds(20, 39);
    private int snifingTicks;
    @Nullable
    private UUID targetUuid;
    private Potion potion = Potions.EMPTY;
    private static final Map<DyeColor, Item> ITEM_BY_DYE = Util.make(Maps.newEnumMap(DyeColor.class), (map) -> {
        map.put(DyeColor.WHITE, Items.WHITE_WOOL);
        map.put(DyeColor.ORANGE, Items.ORANGE_WOOL);
        map.put(DyeColor.MAGENTA, Items.MAGENTA_WOOL);
        map.put(DyeColor.LIGHT_BLUE, Items.LIGHT_BLUE_WOOL);
        map.put(DyeColor.YELLOW, Items.YELLOW_WOOL);
        map.put(DyeColor.LIME, Items.LIME_WOOL);
        map.put(DyeColor.PINK, Items.PINK_WOOL);
        map.put(DyeColor.GRAY, Items.GRAY_WOOL);
        map.put(DyeColor.LIGHT_GRAY, Items.LIGHT_GRAY_WOOL);
        map.put(DyeColor.CYAN, Items.CYAN_WOOL);
        map.put(DyeColor.PURPLE, Items.PURPLE_WOOL);
        map.put(DyeColor.BLUE, Items.BLUE_WOOL);
        map.put(DyeColor.BROWN, Items.BROWN_WOOL);
        map.put(DyeColor.GREEN, Items.GREEN_WOOL);
        map.put(DyeColor.RED, Items.RED_WOOL);
        map.put(DyeColor.BLACK, Items.BLACK_WOOL);
    });

    public HedgehogEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
        this.lookControl = new HedgehogLookControl(this);
        this.moveControl = new HedgehogMoveControl(this);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new HedgehogAfraidOfSkullGoal(this));
        this.goalSelector.add(2, new HedgehogPanicGoal(this));
        this.goalSelector.add(3, new SitGoal(this));
        this.goalSelector.add(4, new HedgehogEatSpiderEyeGoal(this));
        this.goalSelector.add(5, new PounceAtTargetGoal(this, 0.4F));
        this.goalSelector.add(6, new HedgehogMeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.add(7, new HedgehogFollowOwnerGoal(this, 1.3D, 10.0F, 2.0F, false));
        this.goalSelector.add(8, new HedgehogBreedGoal(this, 1.0D));
        this.goalSelector.add(9, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(10, new HedgehogBegGoal(this, 8.0F));
        this.goalSelector.add(11, new HedgehogLookAtPlayerGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(11, new HedgehogRandomLookAroundGal(this));
        this.targetSelector.add(1, new HedgehogOwnerHurtByTargetGoal(this));
        this.targetSelector.add(2, new HedgehogOwnerHurtTargetGoal(this));
        this.targetSelector.add(3, (new RevengeGoal(this, FoxEntity.class)).setGroupRevenge());
        this.targetSelector.add(4, new HedgehogNearestAttackableTargetGoal<>(this, SpiderEntity.class, false));
        this.targetSelector.add(5, new HedgehogNearestAttackableTargetGoal<>(this, CaveSpiderEntity.class, false));
        this.targetSelector.add(6, new UniversalAngerGoal<>(this, true));
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(BAND_COLOR, -1);
        this.dataTracker.startTracking(POTION_TICKS, 0);
        this.dataTracker.startTracking(ANGER_TIME, 0);
        this.dataTracker.startTracking(SCARED_TICKS, 0);
        this.dataTracker.startTracking(ASSISTANCE_TICKS, 0);
        this.dataTracker.startTracking(EFFECT_COLOR, 0);
        this.dataTracker.startTracking(IS_INTERESTED, false);
        this.dataTracker.startTracking(IS_INSTANTANEOUS, false);
        this.dataTracker.startTracking(ANOINTED, false);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (!source.isMagic() && source.getSource() instanceof LivingEntity livingEntity) {
            if (!source.isExplosive()) {
                livingEntity.damage(DamageSource.mob(this), 1.0F);
            }
        }
        return super.damage(source, amount);
    }

    @Override
    public SoundEvent getEatSound(ItemStack stack) {
        return HSoundEvents.ENTITY_HEDGEHOG_EATING;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (this.getScaredTicks() > 0) {
            return HSoundEvents.ENTITY_HEDGEHOG_SCARED;
        }
        return HSoundEvents.ENTITY_HEDGEHOG_AMBIENT;

    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return HSoundEvents.ENTITY_HEDGEHOG_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return HSoundEvents.ENTITY_HEDGEHOG_DEATH;
    }

    public DyeColor getBandColor() {
        int i = this.dataTracker.get(BAND_COLOR);
        return i == -1 ? null : DyeColor.byId(i);
    }

    public void setBandColor(DyeColor color) {
        this.dataTracker.set(BAND_COLOR, color == null ? -1 : color.getId());
    }

    public static DefaultAttributeContainer.Builder createHedgehogAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2F).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putString("Potion", Registry.POTION.getKey(this.potion).toString());
        nbt.putInt("EffectColor", this.getEffectColor());
        nbt.putInt("ScaredTicks", this.getScaredTicks());
        nbt.putInt("PotionTicks", this.getPotionTicks());
        nbt.putInt("AssistanceTicks", this.getAssistanceTicks());
        nbt.putBoolean("instantaneous", this.isInstantaneous());
        nbt.putBoolean("Anointed", this.isAnointed());
        if (this.getBandColor() != null) {
            nbt.putByte("BandColor", (byte) this.getBandColor().getId());
        }
        this.writeAngerToNbt(nbt);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setEffectColor(nbt.getInt("EffectColor"));
        this.setScaredTicks(nbt.getInt("ScaredTicks"));
        this.setPotionTicks(nbt.getInt("PotionTicks"));
        this.setAssistanceTicks(nbt.getInt("AssistanceTicks"));
        this.setAnointed(nbt.getBoolean("Anointed"));
        this.setIsInstantaneous(nbt.getBoolean("Instantaneous"));
        this.readAngerFromNbt(this.world, nbt);
        this.setPotion(PotionUtil.getPotion(nbt));
        if (nbt.contains("BandColor", 99)) {
            this.setBandColor(DyeColor.byId(nbt.getInt("BandColor")));
        }
    }

    public int getEffectColor() {
        return this.dataTracker.get(EFFECT_COLOR);
    }

    public void setEffectColor(int effectColor) {
        this.dataTracker.set(EFFECT_COLOR, effectColor);
    }

    public int getAssistanceTicks() {
        return this.dataTracker.get(ASSISTANCE_TICKS);
    }

    public void setAssistanceTicks(int assistanceTicks) {
        this.dataTracker.set(ASSISTANCE_TICKS, assistanceTicks);
    }

    public boolean isInstantaneous() {
        return this.dataTracker.get(IS_INSTANTANEOUS);
    }

    public void setIsInstantaneous(boolean instantaneous) {
        this.dataTracker.set(IS_INSTANTANEOUS, instantaneous);
    }

    public boolean hasPotion() {
        return this.potion != Potions.EMPTY;
    }

    public void setPotion(Potion potion) {
        this.potion = potion;
    }

    public boolean isAnointed() {
        return this.dataTracker.get(ANOINTED);
    }

    public void setAnointed(boolean anointed) {
        this.dataTracker.set(ANOINTED, anointed);
    }

    public int getSnifingTicks() {
        return this.snifingTicks;
    }

    public void setSnifingTicks(int snifingTicks) {
        this.snifingTicks = snifingTicks;
    }

    public int getPotionTicks() {
        return this.dataTracker.get(POTION_TICKS);
    }

    public void setPotionTicks(int potionTick) {
        this.dataTracker.set(POTION_TICKS, potionTick);
    }

    public int getScaredTicks() {
        return this.dataTracker.get(SCARED_TICKS);
    }

    public void setScaredTicks(int scaredTicks) {
        this.dataTracker.set(SCARED_TICKS, scaredTicks);
    }

    public void setIsInterested(boolean isInterested) {
        this.dataTracker.set(IS_INTERESTED, isInterested);
    }

    public boolean isInterested() {
        return this.dataTracker.get(IS_INTERESTED);
    }

    @Override
    public boolean canHaveStatusEffect(StatusEffectInstance effectInstance) {
        StatusEffect effect = effectInstance.getEffectType();
        if (effect == StatusEffects.POISON) {
            return false;
        }
        return super.canHaveStatusEffect(effectInstance);
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (this.hasPotion() && (this.getPotionTicks() > 0 || this.isInstantaneous())) {
            this.world.sendEntityStatus(this, (byte)8);
        }
        if (this.isAnointed()) {
            this.world.sendEntityStatus(this, (byte)9);
        }
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        List<FoxEntity> foxes = this.world.getNonSpectatingEntities(FoxEntity.class, this.getBoundingBox().expand(3.0D));
        List<LivingEntity> closestLivings = this.world.getEntitiesByClass(LivingEntity.class, this.getBoundingBox().expand(0.6D), (entity -> {
            if (entity instanceof PlayerEntity) {
                return !((PlayerEntity) entity).getAbilities().creativeMode;
            }
            return !entity.isSpectator();
        }));
        for (FoxEntity nearbyFoxes : foxes) {
            if (nearbyFoxes.isAlive()) {
                this.setScaredTicks(300);
            }
        }
        for (LivingEntity nearbyMobs : closestLivings) {
            if (nearbyMobs.isAlive() && this.getScaredTicks() > 0) {
                if (!(nearbyMobs instanceof HedgehogEntity)) {
                    if (nearbyMobs instanceof TameableEntity) {
                        if (((TameableEntity) nearbyMobs).isTamed()) continue;
                    }
                    if (this.hasPotion() && (this.getPotionTicks() > 0 || this.isInstantaneous())) {
                        if (!this.world.isClient()) {
                            for (StatusEffectInstance mobEffectInstance : this.potion.getEffects()) {
                                StatusEffect effect = mobEffectInstance.getEffectType();
                                if (!nearbyMobs.canHaveStatusEffect(mobEffectInstance)) continue;
                                if (nearbyMobs.hasStatusEffect(effect)) continue;
                                if (effect.isInstant() && this.isInstantaneous()) {
                                    this.setIsInstantaneous(false);
                                    effect.applyInstantEffect(null, null, nearbyMobs, mobEffectInstance.getAmplifier(), 1.0D);
                                } else {
                                    nearbyMobs.addStatusEffect(mobEffectInstance);
                                }
                            }
                        }
                    }
                    nearbyMobs.damage(DamageSource.mob(this), 2);
                    this.jumping = false;
                    this.navigation.stop();
                    this.setTarget(null);
                }
            }
        }
        if (this.getAssistanceTicks() > 0) {
            this.setAssistanceTicks(this.getAssistanceTicks() - 1);
            this.setScaredTicks(0);
        }
        if (this.getPotionTicks() > 0) {
            this.setPotionTicks(this.getPotionTicks() - 1);
        }
        if (!this.isInstantaneous() && this.getPotionTicks() == 0 && this.potion != Potions.EMPTY) {
            this.setPotion(Potions.EMPTY);
        }
        if (random.nextInt(15) == 0) {
            if (random.nextBoolean()) {
                this.setSnifingTicks(20);
            }
        }
        if (this.getSnifingTicks() > 0) {
            this.setSnifingTicks(this.getSnifingTicks() - 1);
        }
        if (this.getScaredTicks() > 0) {
            this.jumping = false;
            this.navigation.stop();
            this.setTarget(null);
            this.setScaredTicks(this.getScaredTicks() - 1);
        }
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        Item item = stack.getItem();
        if (this.world.isClient()) {
            boolean flag = this.isOwner(player) || this.isTamed() || stack.isOf(Items.BONE) && !this.isTamed() && !this.hasAngerTime();
            return flag ? ActionResult.CONSUME : ActionResult.PASS;
        } else {
            if (stack.isOf(Items.MILK_BUCKET)) {
                this.kill();
                ItemStack stack1 = ItemUsage.exchangeStack(stack, player, Items.BUCKET.getDefaultStack());
                player.setStackInHand(hand, stack1);
                return ActionResult.SUCCESS;
            }
            if (stack.isOf(Items.SPIDER_EYE) && this.getScaredTicks() == 0 && !this.isBaby() && !this.hasPotion() && !this.isAnointed()) {
                for (int i = 0; i < UniformIntProvider.create(40, 80).get(random); i++){
                    Vec3d vec3 = new Vec3d(((double) random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, ((double) random.nextFloat() - 0.5D) * 0.1D);
                    vec3 = vec3.rotateX(-this.getPitch() * ((float) Math.PI / 180F));
                    vec3 = vec3.rotateY(-this.getYaw() * ((float) Math.PI / 180F));
                    double d0 = (double) (-random.nextFloat()) * 0.6D - 0.3D;
                    Vec3d vec31 = new Vec3d(((double) random.nextFloat() - 0.5D) * 0.8D, d0, 1.0D + ((double) random.nextFloat() - 0.5D) * 0.4D);
                    vec31 = vec31.add(this.getX(), this.getEyeY(), this.getZ());
                    ((ServerWorld) this.world).spawnParticles(new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(Items.SPIDER_EYE)), vec31.x, vec31.y, vec31.z, 1, vec3.x, vec3.y + 0.05D, vec3.z, 0.5F);
                }
                this.playSound(SoundEvents.ENTITY_GENERIC_EAT, 1.0F, 1.0F);
                this.setAnointed(true);
                if (!player.getAbilities().creativeMode) {
                    stack.decrement(1);
                }
                return ActionResult.SUCCESS;
            }
            if (!this.isBaby() && stack.getItem() instanceof PotionItem && this.isAnointed() && !this.hasPotion()) {
                Potion potion = PotionUtil.getPotion(stack);
                List<StatusEffectInstance> instance = potion.getEffects();
                for (StatusEffectInstance effectInstance : instance) {
                    if (!effectInstance.getEffectType().isInstant()) {
                        this.setPotionTicks(effectInstance.getDuration());
                    } else {
                        this.setIsInstantaneous(true);
                    }
                    //TODO: CHANGE THIS TO BE MORE EFFICIENT
                    this.setEffectColor(effectInstance.getEffectType().getColor());
                }
                this.setPotion(potion);
                this.setAnointed(false);
                stack.decrement(1);
                if (!player.getAbilities().creativeMode) {
                    if (stack.isEmpty()) {
                        player.setStackInHand(hand, new ItemStack(Items.GLASS_BOTTLE));
                    } else if (!player.getInventory().insertStack(new ItemStack(Items.GLASS_BOTTLE))) {
                        player.dropItem(new ItemStack(Items.GLASS_BOTTLE), false);
                    }
                }
                return ActionResult.SUCCESS;
            }
            if (this.isTamed()) {
                if (this.isBreedingItem(stack) && this.getHealth() < this.getMaxHealth()) {
                    if (stack.isOf(HItems.KIWI)) {
                        this.setAssistanceTicks(1200);
                    }
                    if (!player.getAbilities().creativeMode) {
                        stack.decrement(1);
                    }

                    this.heal((float)item.getFoodComponent().getHunger());
                    this.emitGameEvent(GameEvent.MOB_INTERACT, this.getCameraBlockPos());
                    return ActionResult.SUCCESS;
                }
                for (DyeColor dyeColor : ITEM_BY_DYE.keySet()) {
                    Item dyeItem = ITEM_BY_DYE.get(dyeColor);
                    if (stack.getItem() == dyeItem) {
                        this.setBandColor(dyeColor);
                        if (!player.getAbilities().creativeMode) {
                            stack.decrement(1);
                        }
                        return ActionResult.SUCCESS;
                    }
                }
                if (!(item instanceof DyeItem)) {
                    ActionResult interactionresult = super.interactMob(player, hand);
                    if ((!interactionresult.isAccepted() || this.isBaby()) && this.isOwner(player)) {
                        this.setSitting(!this.isSitting());
                        this.jumping = false;
                        this.navigation.stop();
                        this.setTarget(null);
                        return ActionResult.SUCCESS;
                    }

                    return interactionresult;
                }
                DyeColor dyecolor = ((DyeItem)item).getColor();
                if (dyecolor != this.getBandColor()) {
                    if (this.getBandColor().getId() != -1) {
                        this.setBandColor(dyecolor);
                        if (!player.getAbilities().creativeMode) {
                            stack.decrement(1);
                        }
                    }

                    return ActionResult.SUCCESS;
                }
            } else if ((stack.isOf(HItems.KIWI) || stack.isOf(Items.APPLE)) && !this.hasAngerTime() && this.getScaredTicks() == 0) {
                if (!player.getAbilities().creativeMode) {
                    stack.decrement(1);
                }

                if (this.random.nextInt(3) == 0) {
                    this.setOwner(player);
                    this.navigation.stop();
                    this.setTarget(null);
                    this.setSitting(true);
                    this.world.sendEntityStatus(this, (byte)7);
                } else {
                    this.world.sendEntityStatus(this, (byte)6);
                }

                return ActionResult.SUCCESS;
            }

            return super.interactMob(player, hand);
        }
    }

    @Override
    public void handleStatus(byte status) {
        super.handleStatus(status);
        if (status == 8) {
            int i = this.getEffectColor();
            if (i > 0) {
                boolean flag;
                if (this.isInvisible()) {
                    flag = this.random.nextInt(15) == 0;
                } else {
                    flag = this.random.nextBoolean();
                }
                if (flag) {
                    double d0 = (double) (i >> 16 & 255) / 255.0D;
                    double d1 = (double) (i >> 8 & 255) / 255.0D;
                    double d2 = (double) (i & 255) / 255.0D;
                    this.world.addParticle(ParticleTypes.ENTITY_EFFECT, this.getParticleX(0.5D), this.getRandomBodyY(), this.getParticleZ(0.5D), d0, d1, d2);
                }
            }
        }
        if (status == 9) {
            if (random.nextInt(15) == 0) {
                for (int k = 0; k < UniformIntProvider.create(1, 2).get(this.getRandom()); k++) {
                    this.world.addParticle(ParticleTypes.SNOWFLAKE, this.getCameraBlockPos().getX() + 0.5D, (this.getCameraBlockPos().getY()) + 0.8D, this.getCameraBlockPos().getZ() + 0.5D, (MathHelper.nextBetween(random, -1.0F, 1.0F) * 0.083333336F), 0.05F, (MathHelper.nextBetween(random, -1.0F, 1.0F) * 0.083333336F));
                }
            }
        }
    }


    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == Items.APPLE || stack.getItem() == HItems.KIWI;
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        HedgehogEntity hedgehog = HEntities.HEDGEHOG.create(world);
        UUID uuid = this.getOwnerUuid();
        if (uuid != null) {
            hedgehog.setOwnerUuid(uuid);
            hedgehog.setTamed(true);
        }
        return hedgehog;
    }

    @Override
    public int getAngerTime() {
        return this.dataTracker.get(ANGER_TIME);
    }

    @Override
    public void setAngerTime(int ticks) {
        this.dataTracker.set(ANGER_TIME, ticks);
    }

    @Nullable
    @Override
    public UUID getAngryAt() {
        return this.targetUuid;
    }

    @Override
    public void setAngryAt(@Nullable UUID uuid) {
        this.targetUuid = uuid;
    }

    @Override
    public void chooseRandomAngerTime() {
        this.setAngerTime(PERSISTENT_ANGER_TIME.get(this.random));
    }

    @Override
    public boolean canBreedWith(AnimalEntity animal) {
        if (animal == this) {
            return false;
        } else if (!this.isTamed()) {
            return false;
        } else if (!(animal instanceof HedgehogEntity)) {
            return false;
        } else {
            HedgehogEntity hedgehog = (HedgehogEntity)animal;
            if (!hedgehog.isTamed()) {
                return false;
            } else if (hedgehog.isInSittingPose()) {
                return false;
            } else {
                return this.isInLove() && hedgehog.isInLove();
            }
        }
    }

    @Override
    public boolean canAttackWithOwner(LivingEntity target, LivingEntity owner) {
        if (!(target instanceof CreeperEntity) && !(target instanceof GhastEntity)) {
            if (target instanceof HedgehogEntity) {
                HedgehogEntity hedgehog = (HedgehogEntity)target;
                return !hedgehog.isTamed() || hedgehog.getOwner() != owner;
            } else if (target instanceof PlayerEntity && owner instanceof PlayerEntity && !((PlayerEntity)owner).shouldDamagePlayer((PlayerEntity)target)) {
                return false;
            } else if (target instanceof HorseBaseEntity && ((HorseBaseEntity)target).isTame()) {
                return false;
            } else {
                return !(target instanceof TameableEntity) || !((TameableEntity)target).isTamed();
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean tryAttack(Entity target) {
        float damage = (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        float g = (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_KNOCKBACK);
        if (target instanceof LivingEntity) {
            damage += EnchantmentHelper.getAttackDamage(this.getMainHandStack(), ((LivingEntity)target).getGroup());
            g += (float)EnchantmentHelper.getKnockback(this);
        }

        int i = EnchantmentHelper.getFireAspect(this);
        if (i > 0) {
            target.setOnFireFor(i * 4);
        }

        boolean bl = target.damage(new EntityDamageSource(String.format("mob.%s", new Identifier(Hedgehog.MODID, "hedgehog")), this), damage); // custom damage source
        if (bl) {
            if (g > 0.0F && target instanceof LivingEntity) {
                ((LivingEntity)target).takeKnockback(g * 0.5F, MathHelper.sin(this.getYaw() * 0.017453292F), -MathHelper.cos(this.getYaw() * 0.017453292F));
                this.setVelocity(this.getVelocity().multiply(0.6D, 1.0D, 0.6D));
            }

            if (target instanceof PlayerEntity playerEntity) {
                ((MobEntityInvoker) this).invoke_disablePlayerShield(playerEntity, this.getMainHandStack(), playerEntity.isUsingItem() ? playerEntity.getActiveItem() : ItemStack.EMPTY);
            }

            this.applyDamageEffects(this, target);
            this.onAttacking(target);
        }

        return bl;
    }

    @Override
    public void setTamed(boolean tamed) {
        super.setTamed(tamed);
        if (tamed) {
            this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(20.0D);
            this.setHealth(20.0F);
        } else {
            this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(8.0D);
        }
        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    public static class HedgehogMoveControl extends MoveControl {
        private final HedgehogEntity entity;

        public HedgehogMoveControl(HedgehogEntity mob) {
            super(mob);
            this.entity = mob;
        }

        @Override
        public void tick() {
            if (this.entity.getScaredTicks() == 0) {
                super.tick();
            }
        }
    }

    public static class HedgehogLookControl extends LookControl {
        private final HedgehogEntity entity;

        public HedgehogLookControl(HedgehogEntity entity) {
            super(entity);
            this.entity = entity;
        }

        @Override
        public void tick() {
            if (this.entity.getScaredTicks() == 0) {
                super.tick();
            }
        }
    }
}
