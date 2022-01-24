package net.orcinus.hedgehog.client.renderers.layer;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.orcinus.hedgehog.Hedgehog;
import net.orcinus.hedgehog.client.models.HedgehogModel;
import net.orcinus.hedgehog.client.renderers.HedgehogRenderer;
import net.orcinus.hedgehog.entities.HedgehogEntity;
import net.orcinus.hedgehog.init.HedgehogModelLayers;

public class HedgehogClothLayer extends FeatureRenderer<HedgehogEntity, EntityModel<HedgehogEntity>> {
    //TODO: If you're reading this, I'm sorry. I'll probably find a more efficient way in the future.
    private static final Identifier[] TEXTURE_LOCATION = new Identifier[]{
            new Identifier(Hedgehog.MODID, "textures/entity/clothes/white_hedgehog_cloth.png"),
            new Identifier(Hedgehog.MODID, "textures/entity/clothes/orange_hedgehog_cloth.png"),
            new Identifier(Hedgehog.MODID, "textures/entity/clothes/magenta_hedgehog_cloth.png"),
            new Identifier(Hedgehog.MODID, "textures/entity/clothes/light_blue_hedgehog_cloth.png"),
            new Identifier(Hedgehog.MODID, "textures/entity/clothes/yellow_hedgehog_cloth.png"),
            new Identifier(Hedgehog.MODID, "textures/entity/clothes/lime_hedgehog_cloth.png"),
            new Identifier(Hedgehog.MODID, "textures/entity/clothes/pink_hedgehog_cloth.png"),
            new Identifier(Hedgehog.MODID, "textures/entity/clothes/gray_hedgehog_cloth.png"),
            new Identifier(Hedgehog.MODID, "textures/entity/clothes/light_gray_hedgehog_cloth.png"),
            new Identifier(Hedgehog.MODID, "textures/entity/clothes/cyan_hedgehog_cloth.png"),
            new Identifier(Hedgehog.MODID, "textures/entity/clothes/purple_hedgehog_cloth.png"),
            new Identifier(Hedgehog.MODID, "textures/entity/clothes/blue_hedgehog_cloth.png"),
            new Identifier(Hedgehog.MODID, "textures/entity/clothes/brown_hedgehog_cloth.png"),
            new Identifier(Hedgehog.MODID, "textures/entity/clothes/green_hedgehog_cloth.png"),
            new Identifier(Hedgehog.MODID, "textures/entity/clothes/red_hedgehog_cloth.png"),
            new Identifier(Hedgehog.MODID, "textures/entity/clothes/black_hedgehog_cloth.png")
    };
    private final HedgehogModel<HedgehogEntity> model;

    public HedgehogClothLayer(HedgehogRenderer hedgehogRenderer, EntityModelLoader modelSet) {
        super(hedgehogRenderer);
        this.model = new HedgehogModel<>(modelSet.getModelPart(HedgehogModelLayers.HEDGEHOG_DECOR));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, HedgehogEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity.getBandColor() == null) return;
        if (entity.isTamed() && !entity.isInvisible() && entity.getScaredTicks() == 0) {
            DyeColor color = entity.getBandColor();
            int id = color.getId();
            Identifier texture = TEXTURE_LOCATION[id];
            render(this.getContextModel(), this.model, texture, matrices, vertexConsumers, light, entity, limbAngle, limbDistance, animationProgress, tickDelta, headYaw, headPitch, 1.0F, 1.0F, 1.0F);
        }
    }
}
