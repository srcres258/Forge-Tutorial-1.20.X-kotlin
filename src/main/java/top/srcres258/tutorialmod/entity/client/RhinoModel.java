package top.srcres258.tutorialmod.entity.client;// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;

public class RhinoModel<T extends Entity> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "rhinomodel"), "main");
    private final ModelPart rhino;
    private final ModelPart body;
    private final ModelPart torso;
    final ModelPart head;
    private final ModelPart skull;
    private final ModelPart horn;
    private final ModelPart horn2;
    private final ModelPart left_ear;
    private final ModelPart right_ear;
    private final ModelPart left_eye;
    private final ModelPart left_eyelid;
    private final ModelPart right_eyelid;
    private final ModelPart right_eye;
    private final ModelPart tail;
    private final ModelPart left_back_leg;
    private final ModelPart left_back_knee;
    private final ModelPart left_back_heel;
    private final ModelPart right_back_leg;
    private final ModelPart right_back_knee;
    private final ModelPart right_back_heel;
    private final ModelPart right_front_leg;
    private final ModelPart right_front_knee;
    private final ModelPart left_front_leg;
    private final ModelPart left_front_knee;

    public RhinoModel(ModelPart root) {
        this.rhino = root.getChild("rhino");
        this.body = this.rhino.getChild("body");
        this.torso = this.body.getChild("torso");
        this.head = this.torso.getChild("head");
        this.skull = this.head.getChild("skull");
        this.horn = this.skull.getChild("horn");
        this.horn2 = this.horn.getChild("horn2");
        this.left_ear = this.skull.getChild("left_ear");
        this.right_ear = this.skull.getChild("right_ear");
        this.left_eye = this.skull.getChild("left_eye");
        this.left_eyelid = this.skull.getChild("left_eyelid");
        this.right_eyelid = this.skull.getChild("right_eyelid");
        this.right_eye = this.skull.getChild("right_eye");
        this.tail = this.torso.getChild("tail");
        this.left_back_leg = this.body.getChild("left_back_leg");
        this.left_back_knee = this.left_back_leg.getChild("left_back_knee");
        this.left_back_heel = this.left_back_knee.getChild("left_back_heel");
        this.right_back_leg = this.body.getChild("right_back_leg");
        this.right_back_knee = this.right_back_leg.getChild("right_back_knee");
        this.right_back_heel = this.right_back_knee.getChild("right_back_heel");
        this.right_front_leg = this.body.getChild("right_front_leg");
        this.right_front_knee = this.right_front_leg.getChild("right_front_knee");
        this.left_front_leg = this.body.getChild("left_front_leg");
        this.left_front_knee = this.left_front_leg.getChild("left_front_knee");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition rhino = partdefinition.addOrReplaceChild("rhino", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 2.5F));

        PartDefinition body = rhino.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -38.0F, -26.0F, 20.0F, 24.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 41).addBox(-9.0F, -37.0F, -10.0F, 18.0F, 25.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(53, 67).addBox(-7.0F, -37.0F, 6.0F, 14.0F, 21.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -21.0F, -26.0F));

        PartDefinition skull = head.addOrReplaceChild("skull", CubeListBuilder.create().texOffs(69, 29).addBox(-6.0F, -9.0F, -12.0F, 12.0F, 20.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(-1.0F, -1.0F, -15.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition horn = skull.addOrReplaceChild("horn", CubeListBuilder.create().texOffs(0, 102).addBox(-5.0F, -5.0F, -9.0F, 5.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 8.5F, -11.0F, -0.1745F, 0.0F, 0.0F));

        PartDefinition horn2 = horn.addOrReplaceChild("horn2", CubeListBuilder.create().texOffs(103, 15).addBox(-3.0F, -3.0F, -9.0F, 3.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, -9.0F, -0.4363F, 0.0F, 0.0F));

        PartDefinition left_ear = skull.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -5.0F, -0.5F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -8.0F, -9.5F));

        PartDefinition right_ear = skull.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.0F, -5.0F, -0.5F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, -8.0F, -9.5F));

        PartDefinition left_eye = skull.addOrReplaceChild("left_eye", CubeListBuilder.create().texOffs(55, 46).addBox(-0.45F, -0.4F, -0.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(57, 46).addBox(-0.55F, -1.6F, -1.1F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.8F, 5.1F, -9.4F, 0.2618F, 0.0F, 0.0F));

        PartDefinition left_eyelid = skull.addOrReplaceChild("left_eyelid", CubeListBuilder.create().texOffs(42, 85).addBox(-0.55F, -2.1F, -1.6F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.05F, 5.1F, -9.4F));

        PartDefinition right_eyelid = skull.addOrReplaceChild("right_eyelid", CubeListBuilder.create().texOffs(42, 85).mirror().addBox(-0.45F, -2.1F, -1.6F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.05F, 5.1F, -9.4F));

        PartDefinition right_eye = skull.addOrReplaceChild("right_eye", CubeListBuilder.create().texOffs(55, 46).mirror().addBox(-0.55F, -0.4F, -0.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(57, 46).mirror().addBox(-0.45F, -1.6F, -1.1F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.8F, 5.1F, -9.4F, 0.2618F, 0.0F, 0.0F));

        PartDefinition tail = torso.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(16, 88).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 0.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(57, 0).addBox(-2.5F, 0.0F, 13.0F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -35.0F, 22.0F, -1.309F, 0.0F, 0.0F));

        PartDefinition left_back_leg = body.addOrReplaceChild("left_back_leg", CubeListBuilder.create().texOffs(73, 0).addBox(-4.5F, -4.5F, -5.0F, 9.0F, 13.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -28.5F, 15.5F));

        PartDefinition left_back_knee = left_back_leg.addOrReplaceChild("left_back_knee", CubeListBuilder.create().texOffs(98, 62).addBox(-3.5F, 0.0F, -1.0F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.5F, -3.0F));

        PartDefinition left_back_heel = left_back_knee.addOrReplaceChild("left_back_heel", CubeListBuilder.create().texOffs(54, 105).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, -0.5F));

        PartDefinition right_back_leg = body.addOrReplaceChild("right_back_leg", CubeListBuilder.create().texOffs(73, 0).mirror().addBox(-4.5F, -4.5F, -5.0F, 9.0F, 13.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.0F, -28.5F, 15.5F));

        PartDefinition right_back_knee = right_back_leg.addOrReplaceChild("right_back_knee", CubeListBuilder.create().texOffs(98, 62).mirror().addBox(-3.5F, 0.0F, -1.0F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 8.5F, -3.0F));

        PartDefinition right_back_heel = right_back_knee.addOrReplaceChild("right_back_heel", CubeListBuilder.create().texOffs(54, 105).mirror().addBox(-3.0F, 0.0F, 0.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 10.0F, -0.5F));

        PartDefinition right_front_leg = body.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(100, 111).mirror().addBox(-3.5F, -3.0F, -3.0F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.0F, -17.0F, -20.5F));

        PartDefinition right_front_knee = right_front_leg.addOrReplaceChild("right_front_knee", CubeListBuilder.create().texOffs(54, 105).mirror().addBox(-3.0F, 0.0F, 0.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 7.0F, -2.5F));

        PartDefinition left_front_leg = body.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(100, 111).addBox(-3.5F, -3.0F, -3.0F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -17.0F, -20.5F));

        PartDefinition left_front_knee = left_front_leg.addOrReplaceChild("left_front_knee", CubeListBuilder.create().texOffs(54, 105).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, -2.5F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//        root().getAllParts().forEach(ModelPart::resetPose);
//        applyHeadRotation(netHeadYaw, headPitch);
//
//        animateWalk(ModAnimationDefinitions.RHINO_WALK, limbSwing, limbSwingAmount, 2F, 2.5F);
//        animate(((RhinoEntity) entity).idleAnimationState, ModAnimationDefinitions.RHINO_IDLE, ageInTicks, 1F);
//        animate(((RhinoEntity) entity).attackAnimationState, ModAnimationDefinitions.RHINO_ATTACK, ageInTicks, 1F);

        RhinoModelKt.setupAnimDelegated(this, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        rhino.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return rhino;
    }

    void animateWalkDelegated(AnimationDefinition pAnimationDefinition, float pLimbSwing, float pLimbSwingAmount, float pMaxAnimationSpeed, float pAnimationScaleFactor) {
        animateWalk(pAnimationDefinition, pLimbSwing, pLimbSwingAmount, pMaxAnimationSpeed, pAnimationScaleFactor);
    }

    void animateDelegated(AnimationState pAnimationState, AnimationDefinition pAnimationDefinition, float pAgeInTicks, float pSpeed) {
        animate(pAnimationState, pAnimationDefinition, pAgeInTicks, pSpeed);
    }
}