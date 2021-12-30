package net.orcinus.hedgehog.client.models;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.orcinus.hedgehog.entities.HedgehogEntity;

@Environment(EnvType.CLIENT)
public class HedgehogScaredModel<T extends HedgehogEntity> extends EntityModel<T> {
	private final ModelPart body;

	public HedgehogScaredModel(ModelPart root) {
		this.body = root.getChild("body");
	}

	public static TexturedModelData createBodyLayer() {
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 27.0F, -3.0F));

		ModelPartData spike1 = body.addChild("spike1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -12.8368F, -1.5058F));

		ModelPartData spike9_r1 = spike1.addChild("spike9_r1", ModelPartBuilder.create().uv(35, 18).cuboid(-3.5F, -1.0F, 1.0F, 7.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, 5.5F, -1.0F, 0.3491F, 0.0F, 1.5708F));

		ModelPartData spike8_r1 = spike1.addChild("spike8_r1", ModelPartBuilder.create().uv(35, 20).cuboid(-3.5F, -1.0F, 0.0F, 7.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, 5.5F, 3.0F, 0.3491F, 0.0F, 1.5708F));

		ModelPartData spike7_r1 = spike1.addChild("spike7_r1", ModelPartBuilder.create().uv(35, 22).cuboid(-3.5F, -1.0F, 0.0F, 7.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, 5.5F, 7.0F, 0.3491F, 0.0F, 1.5708F));

		ModelPartData spike6_r1 = spike1.addChild("spike6_r1", ModelPartBuilder.create().uv(35, 24).cuboid(-3.5F, -1.5F, -1.0F, 7.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, 5.5F, 11.0F, 0.3491F, 0.0F, 1.5708F));

		ModelPartData spike5_r1 = spike1.addChild("spike5_r1", ModelPartBuilder.create().uv(35, 26).cuboid(-3.5F, -0.5F, -1.0F, 7.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 5.5F, 11.0F, -0.3491F, 0.0F, 1.5708F));

		ModelPartData spike4_r1 = spike1.addChild("spike4_r1", ModelPartBuilder.create().uv(0, 36).cuboid(-3.5F, -1.0F, 0.0F, 7.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 5.5F, 7.0F, -0.3491F, 0.0F, 1.5708F));

		ModelPartData spike3_r1 = spike1.addChild("spike3_r1", ModelPartBuilder.create().uv(36, 0).cuboid(-3.5F, -1.0F, 0.0F, 7.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 5.5F, 3.0F, -0.3491F, 0.0F, 1.5708F));

		ModelPartData spike2_r1 = spike1.addChild("spike2_r1", ModelPartBuilder.create().uv(36, 2).cuboid(-3.5F, -1.0F, 1.0F, 7.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 5.5F, -1.0F, -0.3491F, 0.0F, 1.5708F));

		ModelPartData spike1_r1 = spike1.addChild("spike1_r1", ModelPartBuilder.create().uv(24, 34).cuboid(-6.0F, -0.5F, -1.0F, 12.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.0F, -0.5236F, 0.0F, 0.0F));

		ModelPartData spike2 = body.addChild("spike2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -12.8368F, -1.5058F));

		ModelPartData spike2_r2 = spike2.addChild("spike2_r2", ModelPartBuilder.create().uv(0, 34).cuboid(-6.0F, -1.75F, 1.0F, 12.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.0F, -0.5236F, 0.0F, 0.0F));

		ModelPartData spike3 = body.addChild("spike3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -12.8368F, -1.5058F));

		ModelPartData spike3_r2 = spike3.addChild("spike3_r2", ModelPartBuilder.create().uv(24, 32).cuboid(-6.0F, -0.75F, -1.0F, 12.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.2679F, 6.4641F, -0.5236F, 0.0F, 0.0F));

		ModelPartData spike4 = body.addChild("spike4", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -12.8368F, -1.5058F));

		ModelPartData spike4_r2 = spike4.addChild("spike4_r2", ModelPartBuilder.create().uv(0, 32).cuboid(-6.0F, -0.5F, -2.0F, 12.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5359F, 10.9282F, -0.5236F, 0.0F, 0.0F));

		ModelPartData inner = body.addChild("inner", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -9.0F, -7.0F, 12.0F, 6.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 4.0F));

		ModelPartData outer = body.addChild("outer", ModelPartBuilder.create().uv(0, 18).cuboid(-6.0F, -12.0F, -6.5F, 12.0F, 3.0F, 11.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 4.0F));

		return TexturedModelData.of(meshdefinition, 64, 64);
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}

	@Override
	public void render(MatrixStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		if (this.child) {
			poseStack.push();
			poseStack.translate(0.0D, (10 / 16.0F), (4 / 16.0F));
			poseStack.pop();
			poseStack.push();
			float f1 = 1.0F / 2.0F;
			poseStack.scale(f1, f1, f1);
			poseStack.translate(0.0D, (24.0F / 16.0F), 0.0D);
			this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
			poseStack.pop();
		} else {
			this.body.render(poseStack, buffer, packedLight, packedOverlay);
		}
	}
}