package top.srcres258.tutorialmod.entity.client

import net.minecraft.client.model.geom.ModelPart
import net.minecraft.util.Mth
import net.minecraft.world.entity.Entity
import top.srcres258.tutorialmod.entity.animations.ModAnimationDefinitions
import top.srcres258.tutorialmod.entity.custom.RhinoEntity
import kotlin.math.PI

internal fun <T: Entity> RhinoModel<T>.setupAnimDelegated(
    entity: Entity,
    limbSwing: Float,
    limbSwingAmount: Float,
    ageInTicks: Float,
    netHeadYaw: Float,
    headPitch: Float
) {
    root().allParts.forEach(ModelPart::resetPose)
    applyHeadRotation(netHeadYaw, headPitch)

    animateWalkDelegated(ModAnimationDefinitions.RHINO_WALK, limbSwing, limbSwingAmount, 2F, 2.5F)
    (entity as RhinoEntity).let { entity ->
        animateDelegated(entity.idleAnimationState, ModAnimationDefinitions.RHINO_IDLE, ageInTicks, 1F)
        // TODO
    }
}

private fun <T: Entity> RhinoModel<T>.applyHeadRotation(pNetHeadYaw: Float, pHeadPitch: Float) {
    var pNetHeadYaw = pNetHeadYaw
    var pHeadPitch = pHeadPitch

    pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30F, 30F)
    pHeadPitch = Mth.clamp(pHeadPitch, -25F, 45F)

    head.yRot = pNetHeadYaw * (PI.toFloat() / 180F)
    head.xRot = pHeadPitch * (PI.toFloat() / 180F)
}