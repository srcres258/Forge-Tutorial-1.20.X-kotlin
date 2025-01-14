package top.srcres258.tutorialmod.entity.renderer

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.LightTexture
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.core.BlockPos
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.LightLayer
import thedarkcolour.kotlinforforge.forge.use
import top.srcres258.tutorialmod.block.entity.GemPolishingStationBlockEntity

class GemPolishingBlockEntityRenderer(
    context: BlockEntityRendererProvider.Context
) : BlockEntityRenderer<GemPolishingStationBlockEntity> {
    override fun render(
        blockEntity: GemPolishingStationBlockEntity,
        partialTick: Float,
        poseStack: PoseStack,
        buffer: MultiBufferSource,
        packedLight: Int,
        packedOverlay: Int
    ) {
        val itemRenderer = Minecraft.getInstance().itemRenderer
        val itemStack = blockEntity.renderStack

        poseStack.use {
            poseStack.run {
                translate(0.5F, 0.75F, 0.5F)
                scale(0.35F, 0.35F, 0.35F)
                mulPose(Axis.XP.rotationDegrees(270F))
            }

            itemRenderer.renderStatic(
                itemStack,
                ItemDisplayContext.FIXED,
                getLightLevel(blockEntity.level!!, blockEntity.blockPos),
                OverlayTexture.NO_OVERLAY,
                poseStack,
                buffer,
                blockEntity.level,
                1
            )
        }
    }
}

private fun getLightLevel(level: Level, pos: BlockPos): Int {
    val bLight = level.getBrightness(LightLayer.BLOCK, pos)
    val sLight = level.getBrightness(LightLayer.SKY, pos)
    return LightTexture.pack(bLight, sLight)
}