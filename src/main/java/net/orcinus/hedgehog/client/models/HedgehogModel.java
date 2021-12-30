package net.orcinus.hedgehog.client.models;

import com.google.common.collect.ImmutableList;
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
import net.minecraft.util.math.MathHelper;
import net.orcinus.hedgehog.entities.HedgehogEntity;

@Environment(EnvType.CLIENT)
public class HedgehogModel<T extends HedgehogEntity> extends EntityModel<T> {
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart right_front_leg;
	private final ModelPart left_front_leg;
	private final ModelPart right_back_leg;
	private final ModelPart left_back_leg;
	private final ModelPart left_side_spike;
	private final ModelPart left_side_spike2;
	private final ModelPart left_side_spike3;
	private final ModelPart left_side_spike4;
	private final ModelPart right_side_spike;
	private final ModelPart right_side_spike2;
	private final ModelPart right_side_spike3;
	private final ModelPart right_side_spike4;

	public HedgehogModel(ModelPart root) {
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.right_front_leg = root.getChild("right_front_leg");
		this.left_front_leg = root.getChild("left_front_leg");
		this.right_back_leg = root.getChild("right_back_leg");
		this.left_back_leg = root.getChild("left_back_leg");
		this.left_side_spike = root.getChild("left_side_spike");
		this.left_side_spike2 = root.getChild("left_side_spike2");
		this.left_side_spike3 = root.getChild("left_side_spike3");
		this.left_side_spike4 = root.getChild("left_side_spike4");
		this.right_side_spike = root.getChild("right_side_spike");
		this.right_side_spike2 = root.getChild("right_side_spike2");
		this.right_side_spike3 = root.getChild("right_side_spike3");
		this.right_side_spike4 = root.getChild("right_side_spike4");

	}

	public static TexturedModelData createBodyLayer() {
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 18.5F, -5.0F));

		ModelPartData head_r1 = head.addChild("head_r1", ModelPartBuilder.create().uv(0, 36).cuboid(-5.0F, -9.0F, -11.0F, 10.0F, 7.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 5.5F, 9.0F, 0.0F, 0.0F, 0.0F));

		ModelPartData ear = head.addChild("ear", ModelPartBuilder.create().uv(7, 6).cuboid(5.0F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F))
				.uv(7, 0).cuboid(-8.0F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 0.0F));

		ModelPartData nose = head.addChild("nose", ModelPartBuilder.create().uv(36, 24).cuboid(-2.0F, -1.0F, -4.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.5F, -2.0F));

		ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create().uv(10, 0).cuboid(-6.0F, -12.0F, -6.0F, 12.0F, 3.0F, 15.0F, new Dilation(0.0F))
				.uv(16, 46).cuboid(-6.0F, -9.0F, -3.0F, 12.0F, 6.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData spike1 = body.addChild("spike1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -12.6047F, -5.1038F));

		ModelPartData spike1_r1 = spike1.addChild("spike1_r1", ModelPartBuilder.create().uv(36, 18).cuboid(-6.0F, 1.0F, -3.0F, 12.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.2321F, 3.5981F, -0.5236F, 0.0F, 0.0F));

		ModelPartData spike2 = body.addChild("spike2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -12.8368F, -1.5058F));

		ModelPartData spike2_r1 = spike2.addChild("spike2_r1", ModelPartBuilder.create().uv(36, 18).cuboid(-6.0F, -1.0F, 0.0F, 12.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		ModelPartData spike3 = body.addChild("spike3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -12.5688F, 2.9583F));

		ModelPartData spike3_r1 = spike3.addChild("spike3_r1", ModelPartBuilder.create().uv(36, 22).cuboid(-6.0F, -1.0F, 0.0F, 12.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		ModelPartData spike4 = body.addChild("spike4", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -12.3009F, 7.4224F));

		ModelPartData spike5_r1 = spike4.addChild("spike5_r1", ModelPartBuilder.create().uv(36, 20).cuboid(-6.0F, -1.0F, 0.0F, 12.0F, 2.0F, 0.0F, new Dilation(0.0F))
				.uv(36, 20).cuboid(-6.0F, -1.0F, 0.0F, 12.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		ModelPartData right_front_leg = partdefinition.addChild("right_front_leg", ModelPartBuilder.create().uv(0, 18).cuboid(-1.0F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 22.5F, -4.5F));

		ModelPartData left_front_leg = partdefinition.addChild("left_front_leg", ModelPartBuilder.create().uv(0, 24).cuboid(-1.0F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, 22.5F, -4.5F));

		ModelPartData right_back_leg = partdefinition.addChild("right_back_leg", ModelPartBuilder.create().uv(0, 6).cuboid(-1.0F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 22.5F, 5.5F));

		ModelPartData left_back_leg = partdefinition.addChild("left_back_leg", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, 22.5F, 5.5F));

		ModelPartData left_side_spike = partdefinition.addChild("left_side_spike", ModelPartBuilder.create(), ModelTransform.pivot(6.5716F, 16.6632F, -1.405F));

		ModelPartData left_side_spike_r1 = left_side_spike.addChild("left_side_spike_r1", ModelPartBuilder.create().uv(36, 18).cuboid(-3.5F, -1.0F, 0.0F, 7.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, 1.5708F));

		ModelPartData left_side_spike2 = partdefinition.addChild("left_side_spike2", ModelPartBuilder.create(), ModelTransform.pivot(6.4852F, 16.6632F, 1.7561F));

		ModelPartData left_side_spike_2_r1 = left_side_spike2.addChild("left_side_spike_2_r1", ModelPartBuilder.create().uv(36, 18).cuboid(-3.5F, -1.0F, 0.0F, 7.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, 1.5708F));

		ModelPartData left_side_spike3 = partdefinition.addChild("left_side_spike3", ModelPartBuilder.create(), ModelTransform.pivot(6.3989F, 16.6632F, 4.9172F));

		ModelPartData left_side_spike_3_r1 = left_side_spike3.addChild("left_side_spike_3_r1", ModelPartBuilder.create().uv(36, 18).cuboid(-3.5F, -1.0F, 0.0F, 7.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, 1.5708F));

		ModelPartData left_side_spike4 = partdefinition.addChild("left_side_spike4", ModelPartBuilder.create(), ModelTransform.pivot(6.3125F, 16.6632F, 8.0783F));

		ModelPartData left_side_spike_4_r1 = left_side_spike4.addChild("left_side_spike_4_r1", ModelPartBuilder.create().uv(36, 18).cuboid(-3.5F, -1.0F, 0.0F, 7.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, 1.5708F));

		ModelPartData right_side_spike = partdefinition.addChild("right_side_spike", ModelPartBuilder.create(), ModelTransform.pivot(-6.4977F, 16.6632F, -1.6572F));

		ModelPartData right_side_spike_r1 = right_side_spike.addChild("right_side_spike_r1", ModelPartBuilder.create().uv(36, 18).cuboid(-3.5F, -1.0F, 0.0F, 7.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 1.5708F));

		ModelPartData right_side_spike2 = partdefinition.addChild("right_side_spike2", ModelPartBuilder.create(), ModelTransform.pivot(-6.4113F, 16.6632F, 1.5039F));

		ModelPartData right_side_spike2_r1 = right_side_spike2.addChild("right_side_spike2_r1", ModelPartBuilder.create().uv(36, 18).cuboid(-3.5F, -1.0F, 0.0F, 7.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 1.5708F));

		ModelPartData right_side_spike3 = partdefinition.addChild("right_side_spike3", ModelPartBuilder.create(), ModelTransform.pivot(-6.325F, 16.6632F, 4.665F));

		ModelPartData right_side_spike3_r1 = right_side_spike3.addChild("right_side_spike3_r1", ModelPartBuilder.create().uv(36, 18).cuboid(-3.5F, -1.0F, 0.0F, 7.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 1.5708F));

		ModelPartData right_side_spike4 = partdefinition.addChild("right_side_spike4", ModelPartBuilder.create(), ModelTransform.pivot(-6.2386F, 16.6632F, 7.8261F));

		ModelPartData right_side_spike4_r1 = right_side_spike4.addChild("right_side_spike4_r1", ModelPartBuilder.create().uv(36, 18).cuboid(-3.5F, -1.0F, 0.0F, 7.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 1.5708F));

		return TexturedModelData.of(meshdefinition, 64, 64);
	}

	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		int i = entity.getSnifingTicks();
		this.head.getChild("nose").pitch = i > 0 ? -MathHelper.abs(MathHelper.sin(ageInTicks) * 0.3F) : 0.0F;
		this.head.getChild("ear").pitch = !entity.isInSittingPose() ? MathHelper.sin(ageInTicks * 1.0F * 0.2F) * 0.3F : 0.0F;
		this.head.pitch = MathHelper.sin(limbSwing) * limbSwingAmount;
		this.body.pitch = MathHelper.sin(limbSwing) * limbSwingAmount * 0.25F;
		this.right_back_leg.yaw = MathHelper.sin(limbSwing) * limbSwingAmount * 0.5F;
		this.right_back_leg.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
		this.left_back_leg.pitch = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;
		this.left_back_leg.yaw = MathHelper.sin(limbSwing) * limbSwingAmount * 0.5F;
		this.right_front_leg.pitch = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;
		this.right_front_leg.yaw = MathHelper.sin(limbSwing) * limbSwingAmount * 0.5F;
		this.left_front_leg.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
		this.left_front_leg.yaw = MathHelper.sin(limbSwing) * limbSwingAmount * 0.5F;
	}

	@Override
	public void animateModel(T entity, float limbSwing, float limbSwingAmount, float ageInTicks) {
		this.left_back_leg.visible = !entity.isInSittingPose();
		this.right_back_leg.visible = !entity.isInSittingPose();
		this.left_front_leg.visible = !entity.isInSittingPose();
		this.right_front_leg.visible = !entity.isInSittingPose();
		if (entity.isInSittingPose()) {
			this.head.getChild("ear").setPivot(0.0F, 1.0F, -1.5F);
			this.body.setPivot(0.0F, 26.5F, -3.0F);
			this.left_side_spike.setPivot(7.0716F, 19.6632F, -3.405F);
			this.left_side_spike2.setPivot(6.9852F, 19.6632F, -0.905F);
			this.left_side_spike3.setPivot(6.8989F, 19.6632F, 1.595F);
			this.left_side_spike4.setPivot(6.8125F, 19.6632F, 4.095F);
			this.right_side_spike.setPivot(-6.9977F, 19.6632F, -4.1572F);
			this.right_side_spike2.setPivot(-6.9113F, 19.6632F, -0.9961F);
			this.right_side_spike3.setPivot(-6.825F, 19.6632F, 2.165F);
			this.right_side_spike4.setPivot(-6.7386F, 19.6632F, 5.3261F);
		} else {
			this.head.getChild("ear").setPivot(0.0F, -2.0F, 0.0F);
			this.left_back_leg.setPivot(3.0F, 22.5F, 5.5F);
			this.right_front_leg.setPivot(-3.0F, 22.5F, -4.5F);
			this.right_back_leg.setPivot(-3.0F, 22.5F, 5.5F);
			this.left_front_leg.setPivot(3.0F, 22.5F, -4.5F);
			this.body.setPivot(0.0F, 24.0F, 0.0F);
			this.left_side_spike.setPivot(6.5716F, 16.6632F, -1.405F);
			this.left_side_spike2.setPivot(6.4852F, 16.6632F, 1.7561F);
			this.left_side_spike3.setPivot(6.3989F, 16.6632F, 4.9172F);
			this.left_side_spike4.setPivot(6.3125F, 16.6632F, 8.0783F);
			this.right_side_spike.setPivot(-6.4977F, 16.6632F, -1.6572F);
			this.right_side_spike2.setPivot(-6.4113F, 16.6632F, 1.5039F);
			this.right_side_spike3.setPivot(-6.325F, 16.6632F, 4.665F);
			this.right_side_spike4.setPivot(-6.2386F, 16.6632F, 7.8261F);
		}
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
			ImmutableList.of(this.left_side_spike, this.left_side_spike2, this.left_side_spike3, this.left_side_spike4, this.right_side_spike, this.right_side_spike2, this.right_side_spike3, this.right_side_spike4, this.head, this.body, this.left_back_leg, this.right_back_leg, this.right_front_leg, this.left_front_leg).forEach((model) -> {
				model.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
			});
			poseStack.pop();
		} else {
			this.head.render(poseStack, buffer, packedLight, packedOverlay);
			this.body.render(poseStack, buffer, packedLight, packedOverlay);
			this.right_front_leg.render(poseStack, buffer, packedLight, packedOverlay);
			this.left_front_leg.render(poseStack, buffer, packedLight, packedOverlay);
			this.right_back_leg.render(poseStack, buffer, packedLight, packedOverlay);
			this.left_back_leg.render(poseStack, buffer, packedLight, packedOverlay);
			left_side_spike.render(poseStack, buffer, packedLight, packedOverlay);
			left_side_spike2.render(poseStack, buffer, packedLight, packedOverlay);
			left_side_spike3.render(poseStack, buffer, packedLight, packedOverlay);
			left_side_spike4.render(poseStack, buffer, packedLight, packedOverlay);
			right_side_spike.render(poseStack, buffer, packedLight, packedOverlay);
			right_side_spike2.render(poseStack, buffer, packedLight, packedOverlay);
			right_side_spike3.render(poseStack, buffer, packedLight, packedOverlay);
			right_side_spike4.render(poseStack, buffer, packedLight, packedOverlay);
		}
	}
}