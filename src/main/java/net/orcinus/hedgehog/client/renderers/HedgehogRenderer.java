package net.orcinus.hedgehog.client.renderers;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.orcinus.hedgehog.Hedgehog;
import net.orcinus.hedgehog.client.models.HedgehogModel;
import net.orcinus.hedgehog.client.models.HedgehogScaredModel;
import net.orcinus.hedgehog.client.renderers.layer.HedgehogClothLayer;
import net.orcinus.hedgehog.entities.HedgehogEntity;
import net.orcinus.hedgehog.init.HModelLayers;

@Environment(EnvType.CLIENT)
public class HedgehogRenderer extends MobEntityRenderer<HedgehogEntity, EntityModel<HedgehogEntity>> {
    public static final Identifier TEXTURE = new Identifier(Hedgehog.MODID, "textures/entity/hedgehog.png");
    public static final Identifier SCARED_TEXTURE = new Identifier(Hedgehog.MODID, "textures/entity/scared_hedgehog.png");
    public static final Identifier SPEED_CONSUMER = new Identifier(Hedgehog.MODID, "textures/entity/speed_consumer_hedgehog.png");
    public static final Identifier SCARED_SPEED_CONSUMER = new Identifier(Hedgehog.MODID, "textures/entity/scared_speed_consumer_hedgehog.png");
    private final EntityModel<HedgehogEntity> scared;
    private final EntityModel<HedgehogEntity> normal = this.getModel();

    public HedgehogRenderer(EntityRendererFactory.Context context) {
        super(context, new HedgehogModel<>(context.getPart(HModelLayers.HEDGEHOG)), 0.3F);
        this.scared = new HedgehogScaredModel<>(context.getPart(HModelLayers.HEDGEHOG_SCARED));
        this.addFeature(new HedgehogClothLayer(this, context.getModelLoader()));
    }

    @Override
    public void render(HedgehogEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (mobEntity.getScaredTicks() > 0) {
            this.model = this.scared;
        } else {
            this.model = this.normal;
        }
        if (mobEntity.isInSittingPose()) {
            matrixStack.translate(0.0d, -0.04d, 0.0d);
        }
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(HedgehogEntity entity) {
        String s = Formatting.strip(entity.getName().getString());
        if ("SpeedBoy".equals(s)) {
            return entity.getScaredTicks() > 0 ? SCARED_SPEED_CONSUMER : SPEED_CONSUMER;
        } else {
            return entity.getScaredTicks() > 0 ? SCARED_TEXTURE : TEXTURE;
        }
    }
}
