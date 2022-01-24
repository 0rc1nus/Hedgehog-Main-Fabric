package net.orcinus.hedgehog.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.orcinus.hedgehog.init.HedgehogBlocks;
import net.orcinus.hedgehog.init.HedgehogModelLayers;

@Environment(EnvType.CLIENT)
public class HedgehogClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HedgehogModelLayers.registerRenderers();
        BlockRenderLayerMap.INSTANCE.putBlock(HedgehogBlocks.KIWI_VINES, RenderLayer.getCutout());
    }

}
