package net.orcinus.hedgehog.client.models;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.util.math.MathHelper;
import net.orcinus.hedgehog.entities.HedgehogEntity;

@SuppressWarnings("FieldCanBeLocal, unused")
@Environment(EnvType.CLIENT)
public class HedgehogScaredModel<T extends HedgehogEntity> extends AnimalModel<T> {
	private final ModelPart root;

	private final ModelPart body;
	private final ModelPart spines_top0;
	private final ModelPart spines_top1;
	private final ModelPart spines_top2;
	private final ModelPart spines_top3;
	private final ModelPart spines_front0;
	private final ModelPart spines_front1;
	private final ModelPart spines_left0;
	private final ModelPart spines_left1;
	private final ModelPart spines_left2;
	private final ModelPart spines_left3;
	private final ModelPart spines_right0;
	private final ModelPart spines_right1;
	private final ModelPart spines_right2;
	private final ModelPart spines_right3;

	public HedgehogScaredModel(ModelPart root) {
		this.root = root;

		this.body = root.getChild("body");

		this.spines_top0 = body.getChild("spines_top0");
		this.spines_top1 = body.getChild("spines_top1");
		this.spines_top2 = body.getChild("spines_top2");
		this.spines_top3 = body.getChild("spines_top3");
		this.spines_right0 = body.getChild("spines_right0");
		this.spines_right1 = body.getChild("spines_right1");
		this.spines_right2 = body.getChild("spines_right2");
		this.spines_right3 = body.getChild("spines_right3");
		this.spines_left0 = body.getChild("spines_left0");
		this.spines_left1 = body.getChild("spines_left1");
		this.spines_left2 = body.getChild("spines_left2");
		this.spines_left3 = body.getChild("spines_left3");
		this.spines_front0 = body.getChild("spines_front0");
		this.spines_front1 = body.getChild("spines_front1");
	}

	public static TexturedModelData getTexturedModelData() {

		ModelData data = new ModelData();
		ModelPartData root = data.getRoot();

		ModelPartData body = root.addChild(
			"body",
			ModelPartBuilder.create()
							.uv(0, 1)
							.mirrored(false)
							.cuboid(-3.0F, -3.0F, -4.5F, 6.0F, 6.0F, 9.0F, new Dilation(0.0F)),
			ModelTransform.of(0.0F, 21.0F, -1.0F, 0.0F, 0.0F, 0.0F)
		);

		ModelPartData spines_top0 = body.addChild(
			"spines_top0",
			ModelPartBuilder.create()
							.uv(21, 8)
							.mirrored(false)
							.cuboid(-2.5F, -2.0F, 0.0F, 5.0F, 2.0F, 0.0F, new Dilation(0.0F)),
			ModelTransform.of(0.0F, -3.0F, -4.0F, -1.0472F, 0.0F, 0.0F)
		);

		ModelPartData spines_top1 = body.addChild(
			"spines_top1",
			ModelPartBuilder.create()
							.uv(21, 5)
							.mirrored(false)
							.cuboid(-2.5F, -2.0F, 0.0F, 5.0F, 2.0F, 0.0F, new Dilation(0.0F)),
			ModelTransform.of(0.0F, -3.0F, -2.0F, -1.0472F, 0.0F, 0.0F)
		);

		ModelPartData spines_top2 = body.addChild(
			"spines_top2",
			ModelPartBuilder.create()
							.uv(21, 8)
							.mirrored(false)
							.cuboid(-2.5F, -2.0F, 0.0F, 5.0F, 2.0F, 0.0F, new Dilation(0.0F)),
			ModelTransform.of(0.0F, -3.0F, 0.0F, -1.0472F, 0.0F, 0.0F)
		);

		ModelPartData spines_top3 = body.addChild(
			"spines_top3",
			ModelPartBuilder.create()
							.uv(21, 5)
							.mirrored(false)
							.cuboid(-2.5F, -2.0F, 0.0F, 5.0F, 2.0F, 0.0F, new Dilation(0.0F)),
			ModelTransform.of(0.0F, -3.0F, 2.0F, -1.0472F, 0.0F, 0.0F)
		);

		ModelPartData spines_front0 = body.addChild(
			"spines_front0",
			ModelPartBuilder.create()
							.uv(21, 5)
							.mirrored(false)
							.cuboid(-2.5F, -2.0F, 0.0F, 5.0F, 2.0F, 0.0F, new Dilation(0.0F)),
			ModelTransform.of(0.0F, 2.0F, -4.5F, 0.5236F, 0.0F, 0.0F)
		);

		ModelPartData spines_front1 = body.addChild(
			"spines_front1",
			ModelPartBuilder.create()
							.uv(21, 8)
							.mirrored(false)
							.cuboid(-2.5F, -2.0F, 0.0F, 5.0F, 2.0F, 0.0F, new Dilation(0.0F)),
			ModelTransform.of(0.0F, -1.0F, -4.5F, 0.5236F, 0.0F, 0.0F)
		);

		ModelPartData spines_left0 = body.addChild(
			"spines_left0",
			ModelPartBuilder.create()
							.uv(26, 0)
							.mirrored(false)
							.cuboid(0.0F, -2.5F, 0.0F, 2.0F, 5.0F, 0.0F, new Dilation(0.0F)),
			ModelTransform.of(3.0F, -0.5F, -4.0F, 0.0F, -1.0472F, 0.0F)
		);

		ModelPartData spines_left1 = body.addChild(
			"spines_left1",
			ModelPartBuilder.create()
							.uv(22, 0)
							.mirrored(false)
							.cuboid(0.0F, -2.5F, 0.0F, 2.0F, 5.0F, 0.0F, new Dilation(0.0F)),
			ModelTransform.of(3.0F, -0.5F, -2.0F, 0.0F, -1.0472F, 0.0F)
		);

		ModelPartData spines_left2 = body.addChild(
			"spines_left2",
			ModelPartBuilder.create()
							.uv(26, 0)
							.mirrored(false)
							.cuboid(0.0F, -2.5F, 0.0F, 2.0F, 5.0F, 0.0F, new Dilation(0.0F)),
			ModelTransform.of(3.0F, -0.5F, 0.0F, 0.0F, -1.0472F, 0.0F)
		);

		ModelPartData spines_left3 = body.addChild(
			"spines_left3",
			ModelPartBuilder.create()
							.uv(22, 0)
							.mirrored(false)
							.cuboid(0.0F, -2.5F, 0.0F, 2.0F, 5.0F, 0.0F, new Dilation(0.0F)),
			ModelTransform.of(3.0F, -0.5F, 2.0F, 0.0F, -1.0472F, 0.0F)
		);

		ModelPartData spines_right0 = body.addChild(
			"spines_right0",
			ModelPartBuilder.create()
							.uv(26, 0)
							.mirrored(true)
							.cuboid(-2.0F, -2.5F, 0.0F, 2.0F, 5.0F, 0.0F, new Dilation(0.0F)),
			ModelTransform.of(-3.0F, -0.5F, -4.0F, 0.0F, 1.0472F, 0.0F)
		);

		ModelPartData spines_right1 = body.addChild(
			"spines_right1",
			ModelPartBuilder.create()
							.uv(22, 0)
							.mirrored(true)
							.cuboid(-2.0F, -2.5F, 0.0F, 2.0F, 5.0F, 0.0F, new Dilation(0.0F)),
			ModelTransform.of(-3.0F, -0.5F, -2.0F, 0.0F, 1.0472F, 0.0F)
		);

		ModelPartData spines_right2 = body.addChild(
			"spines_right2",
			ModelPartBuilder.create()
							.uv(26, 0)
							.mirrored(true)
							.cuboid(-2.0F, -2.5F, 0.0F, 2.0F, 5.0F, 0.0F, new Dilation(0.0F)),
			ModelTransform.of(-3.0F, -0.5F, 0.0F, 0.0F, 1.0472F, 0.0F)
		);

		ModelPartData spines_right3 = body.addChild(
			"spines_right3",
			ModelPartBuilder.create()
							.uv(22, 0)
							.mirrored(true)
							.cuboid(-2.0F, -2.5F, 0.0F, 2.0F, 5.0F, 0.0F, new Dilation(0.0F)),
			ModelTransform.of(-3.0F, -0.5F, 2.0F, 0.0F, 1.0472F, 0.0F)
		);

		return TexturedModelData.of(data, 32, 16);
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		float speed = 1.0f;
		float degree = 1.0f;
		this.body.roll = MathHelper.cos(animationProgress * speed * 0.4F) * degree * 0.2F * 0.25F;
		this.spines_top0.pitch = MathHelper.cos(animationProgress * speed * 0.8F) * degree * -0.6F * 0.25F - 1.0F;
		this.spines_top1.pitch = MathHelper.cos(animationProgress * speed * 0.8F) * degree * 0.6F * 0.25F - 1.0F;
		this.spines_top2.pitch = MathHelper.cos(animationProgress * speed * 0.8F) * degree * -0.6F * 0.25F - 1.0F;
		this.spines_top3.pitch = MathHelper.cos(animationProgress * speed * 0.8F) * degree * 0.6F * 0.25F - 1.0F;
		this.spines_right0.yaw = MathHelper.cos(animationProgress * speed * 0.8F) * degree * -0.6F * 0.25F + 1.0F;
		this.spines_right1.yaw = MathHelper.cos(animationProgress * speed * 0.8F) * degree * 0.6F * 0.25F + 1.0F;
		this.spines_right2.yaw = MathHelper.cos(animationProgress * speed * 0.8F) * degree * -0.6F * 0.25F + 1.0F;
		this.spines_right3.yaw = MathHelper.cos(animationProgress * speed * 0.8F) * degree * 0.6F * 0.25F + 1.0F;
		this.spines_left0.yaw = MathHelper.cos(animationProgress * speed * 0.8F) * degree * 0.6F * 0.25F - 1.0F;
		this.spines_left1.yaw = MathHelper.cos(animationProgress * speed * 0.8F) * degree * -0.6F * 0.25F - 1.0F;
		this.spines_left2.yaw = MathHelper.cos(animationProgress * speed * 0.8F) * degree * 0.6F * 0.25F - 1.0F;
		this.spines_left3.yaw = MathHelper.cos(animationProgress * speed * 0.8F) * degree * -0.6F * 0.25F - 1.0F;
		this.spines_front1.pitch = MathHelper.cos(animationProgress * speed * 0.8F) * degree * -0.6F * 0.25F + 0.5F;
		this.spines_front0.pitch = MathHelper.cos(animationProgress * speed * 0.8F) * degree * 0.6F * 0.25F + 0.5F;
	}

	@Override
	protected Iterable<ModelPart> getBodyParts() {
		return ImmutableList.of(this.body);
	}

	@Override
	protected Iterable<ModelPart> getHeadParts() {
		return ImmutableList.of();
	}
}