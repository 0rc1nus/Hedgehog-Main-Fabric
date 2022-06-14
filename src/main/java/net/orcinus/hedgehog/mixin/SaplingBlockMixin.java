package net.orcinus.hedgehog.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.biome.BiomeKeys;
import net.orcinus.hedgehog.world.gen.HedgehogBirchTreeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SaplingBlock.class)
public class SaplingBlockMixin {

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/block/sapling/SaplingGenerator;generate(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/world/gen/chunk/ChunkGenerator;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/random/Random;)Z"), method = "generate", cancellable = true)
    private void Hedgehog$generate(ServerWorld world, BlockPos pos, BlockState state, Random random, CallbackInfo ci) {
        if (world.getBiome(pos).matchesKey(BiomeKeys.MEADOW)) {
            if (state.getBlock() == Blocks.BIRCH_SAPLING) {
                ci.cancel();
                HedgehogBirchTreeFeature.generateTree(world, pos, random, ConstantIntProvider.create(9).get(random), false);
            }
        }
    }

}
