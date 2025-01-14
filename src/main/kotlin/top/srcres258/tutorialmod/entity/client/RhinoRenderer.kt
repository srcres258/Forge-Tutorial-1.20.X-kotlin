package top.srcres258.tutorialmod.entity.client

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.resources.ResourceLocation
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.entity.custom.RhinoEntity

class RhinoRenderer(
    pContext: EntityRendererProvider.Context
) : MobRenderer<RhinoEntity, RhinoModel<RhinoEntity>>(
    pContext,
    RhinoModel(pContext.bakeLayer(ModModelLayers.RHINO_LAYER)),
    2F
) {
    override fun getTextureLocation(entity: RhinoEntity) =
        ResourceLocation(TutorialMod.MOD_ID, "textures/entity/rhino.png")

    override fun render(
        pEntity: RhinoEntity,
        pEntityYaw: Float,
        pPartialTicks: Float,
        pPoseStack: PoseStack,
        pBuffer: MultiBufferSource,
        pPackedLight: Int
    ) {
        if (pEntity.isBaby) {
            pPoseStack.scale(0.5F, 0.5F, 0.5F)
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight)
    }
}