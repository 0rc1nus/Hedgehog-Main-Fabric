package net.orcinus.hedgehog.init;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.orcinus.hedgehog.Hedgehog;
import net.orcinus.hedgehog.client.models.HedgehogModel;
import net.orcinus.hedgehog.client.models.HedgehogScaredModel;
import net.orcinus.hedgehog.client.renderers.HedgehogRenderer;

@Environment(EnvType.CLIENT)
public class HModelLayers {

    public static final EntityModelLayer HEDGEHOG = new EntityModelLayer(new Identifier(Hedgehog.MODID, "hedgehog"), "main");
    public static final EntityModelLayer HEDGEHOG_SCARED = new EntityModelLayer(new Identifier(Hedgehog.MODID, "hedgehogscared"), "main");
    public static final EntityModelLayer HEDGEHOG_DECOR = new EntityModelLayer(new Identifier(Hedgehog.MODID, "hedgehog"), "decor");

    public static void registerRenderers() {
        EntityRendererRegistry.register(HEntities.HEDGEHOG, HedgehogRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(HEDGEHOG, HedgehogModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(HEDGEHOG_DECOR, HedgehogModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(HEDGEHOG_SCARED, HedgehogScaredModel::getTexturedModelData);
    }

}
