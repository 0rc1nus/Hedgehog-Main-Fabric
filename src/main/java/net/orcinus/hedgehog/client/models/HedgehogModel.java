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
public class HedgehogModel<T extends HedgehogEntity> extends AnimalModel<T> {
	private final ModelPart root;

	private final ModelPart body;
	private final ModelPart snout;
	private final ModelPart spines_top1;
	private final ModelPart spines_top2;
	private final ModelPart spines_top3;
	private final ModelPart spines_right1;
	private final ModelPart spines_right2;
	private final ModelPart spines_right3;
	private final ModelPart spines_left1;
	private final ModelPart spines_left2;
	private final ModelPart spines_left3;
	private final ModelPart left_ear;
	private final ModelPart right_ear;
	private final ModelPart right_foot;
	private final ModelPart left_foot;
	private final ModelPart left_hand;
	private final ModelPart right_hand;

	@SuppressWarnings("FieldCanBeLocal, unused")
	@Environment(EnvType.CLIENT)
	public HedgehogModel(ModelPart root) {
		this.root = root;

		this.body = root.getChild("body");
		this.right_foot = root.getChild("right_foot");
		this.left_foot = root.getChild("left_foot");
		this.left_hand = root.getChild("left_hand");
		this.right_hand = root.getChild("right_hand");

		this.snout = body.getChild("snout");
		this.spines_top1 = body.getChild("spines_top1");
		this.spines_top2 = body.getChild("spines_top2");
		this.spines_top3 = body.getChild("spines_top3");
		this.spines_right1 = body.getChild("spines_right1");
		this.spines_right2 = body.getChild("spines_right2");
		this.spines_right3 = body.getChild("spines_right3");
		this.spines_left1 = body.getChild("spines_left1");
		this.spines_left2 = body.getChild("spines_left2");
		this.spines_left3 = body.getChild("spines_left3");
		this.left_ear = body.getChild("left_ear");
		this.right_ear = body.getChild("right_ear");
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
			ModelTransform.of(0.0F, 20.0F, -1.0F, 0.0F, 0.0F, 0.0F)
		);

		ModelPartData snout = body.addChild(
			"snout",
			ModelPartBuilder.create()
							.uv(0, 3)
							.mirrored(false)
							.cuboid(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)),
			ModelTransform.of(0.0F, 2.0F, -4.5F, 0.0F, 0.0F, 0.0F)
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

		ModelPartData left_ear = body.addChild(
			"left_ear",
			ModelPartBuilder.create()
							.uv(0, 0)
							.mirrored(true)
							.cuboid(0.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)),
			ModelTransform.of(3.0F, 0.5F, -2.5F, 0.0F, -0.7854F, 0.0F)
		);

		ModelPartData right_ear = body.addChild(
			"right_ear",
			ModelPartBuilder.create()
							.uv(0, 0)
							.mirrored(false)
							.cuboid(-2.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)),
			ModelTransform.of(-3.0F, 0.5F, -2.5F, 0.0F, 0.7854F, 0.0F)
		);

		ModelPartData right_foot = root.addChild(
			"right_foot",
			ModelPartBuilder.create()
							.uv(0, 7)
							.mirrored(false)
							.cuboid(-0.5F, 0.0F, -1.5F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)),
			ModelTransform.of(-2.0F, 23.0F, 1.5F, 0.0F, 0.0F, 0.0F)
		);

		ModelPartData left_foot = root.addChild(
			"left_foot",
			ModelPartBuilder.create()
							.uv(0, 7)
							.mirrored(false)
							.cuboid(-0.5F, 0.0F, -1.5F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)),
			ModelTransform.of(2.0F, 23.0F, 1.5F, 0.0F, 0.0F, 0.0F)
		);

		ModelPartData left_hand = root.addChild(
			"left_hand",
			ModelPartBuilder.create()
							.uv(0, 7)
							.mirrored(false)
							.cuboid(-0.5F, 0.0F, -1.5F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)),
			ModelTransform.of(1.5F, 23.0F, -2.0F, 0.0F, 0.0F, 0.0F)
		);

		ModelPartData right_hand = root.addChild(
			"right_hand",
			ModelPartBuilder.create()
							.uv(0, 7)
							.mirrored(false)
							.cuboid(-0.5F, 0.0F, -1.5F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)),
			ModelTransform.of(-1.5F, 23.0F, -2.0F, 0.0F, 0.0F, 0.0F)
		);

		return TexturedModelData.of(data, 32, 16);
	}


	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		float speed = 2.0f;
		float degree = 1.5f;
		this.body.roll = MathHelper.cos(limbAngle * speed * 0.2F) * degree * 0.3F * limbDistance;
		this.body.yaw = MathHelper.cos(-1.0F + limbAngle * speed * 0.2F) * degree * 0.3F * limbDistance;
		this.body.pivotY = MathHelper.cos(limbAngle * speed * 0.4F) * degree * 0.75F * limbDistance + 20.025F;
		this.left_ear.yaw = MathHelper.cos(limbAngle * speed * 0.2F) * degree * 0.4F * limbDistance - 0.8F;
		this.right_ear.yaw = MathHelper.cos(limbAngle * speed * 0.2F) * degree * -0.4F * limbDistance + 0.8F;
		this.snout.pitch = MathHelper.cos(animationProgress * speed * 0.6F) * degree * 0.2F * 0.25F;
		this.left_hand.yaw = MathHelper.cos(limbAngle * speed * 0.4F) * degree * 0.8F * limbDistance;
		this.right_hand.yaw = MathHelper.cos(limbAngle * speed * 0.4F) * degree * -0.8F * limbDistance;
		this.left_hand.pivotZ = MathHelper.cos(-1.0F + limbAngle * speed * 0.4F) * degree * 0.5F * limbDistance - 2.0F;
		this.right_hand.pivotZ = MathHelper.cos(-1.0F + limbAngle * speed * 0.4F) * degree * -0.5F * limbDistance - 2.0F;
		this.left_foot.yaw = MathHelper.cos(limbAngle * speed * 0.4F) * degree * -0.8F * limbDistance;
		this.right_foot.yaw = MathHelper.cos(limbAngle * speed * 0.4F) * degree * 0.8F * limbDistance;
		this.left_foot.pivotZ = MathHelper.cos(-1.0F + limbAngle * speed * 0.4F) * degree * -0.5F * limbDistance + 1.5F;
		this.right_foot.pivotZ = MathHelper.cos(-1.0F + limbAngle * speed * 0.4F) * degree * 0.5F * limbDistance + 1.5F;
		this.spines_top1.pitch = MathHelper.cos(-1.0F + limbAngle * speed * 0.2F) * degree * 0.4F * limbDistance - 1.0F;
		this.spines_top2.pitch = MathHelper.cos(limbAngle * speed * 0.2F) * degree * -0.4F * limbDistance - 1.0F;
		this.spines_top3.pitch = MathHelper.cos(-4.0F + limbAngle * speed * 0.2F) * degree * 0.4F * limbDistance - 1.0F;
		this.spines_right1.yaw = MathHelper.cos(-1.0F + limbAngle * speed * 0.2F) * degree * 0.4F * limbDistance + 1.0F;
		this.spines_right2.yaw = MathHelper.cos(limbAngle * speed * 0.2F) * degree * -0.4F * limbDistance + 1.0F;
		this.spines_right3.yaw = MathHelper.cos(-4.0F + limbAngle * speed * 0.2F) * degree * 0.4F * limbDistance + 1.0F;
		this.spines_left1.yaw = MathHelper.cos(-1.0F + limbAngle * speed * 0.2F) * degree * 0.4F * limbDistance - 1.0F;
		this.spines_left2.yaw = MathHelper.cos(limbAngle * speed * 0.2F) * degree * -0.4F * limbDistance - 1.0F;
		this.spines_left3.yaw = MathHelper.cos(-4.0F + limbAngle * speed * 0.2F) * degree * 0.4F * limbDistance - 1.0F;
		this.left_hand.pivotY = MathHelper.cos(limbAngle * speed * 0.4F) * degree * 0.5F * limbDistance + 23.0F;
		this.right_hand.pivotY = MathHelper.cos(limbAngle * speed * 0.4F) * degree * -0.5F * limbDistance + 23.0F;
		this.right_foot.pivotY = MathHelper.cos(limbAngle * speed * 0.4F) * degree * 0.5F * limbDistance + 23.005F;
		this.left_foot.pivotY = MathHelper.cos(-3.0F + limbAngle * speed * 0.4F) * degree * 0.5F * limbDistance + 23.005F;
	}

	@Override
	protected Iterable<ModelPart> getBodyParts() {
		return ImmutableList.of(this.body, this.left_hand, this.right_hand, this.left_foot, this.right_foot);
	}

	@Override
	protected Iterable<ModelPart> getHeadParts() {
		return ImmutableList.of();
	}
}