package top.srcres258.tutorialmod.event

import net.minecraft.client.renderer.blockentity.HangingSignRenderer
import net.minecraft.client.renderer.blockentity.SignRenderer
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.client.event.EntityRenderersEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.entity.ModBlockEntities
import top.srcres258.tutorialmod.entity.client.ModModelLayers
import top.srcres258.tutorialmod.entity.client.RhinoModel
import top.srcres258.tutorialmod.entity.renderer.GemPolishingBlockEntityRenderer

@Mod.EventBusSubscriber(
    modid = TutorialMod.MOD_ID,
    bus = Mod.EventBusSubscriber.Bus.MOD,
    value = [Dist.CLIENT]
)
object ModEventBusClientEvents {
    @SubscribeEvent
    fun registerLayer(event: EntityRenderersEvent.RegisterLayerDefinitions) {
        event.registerLayerDefinition(ModModelLayers.RHINO_LAYER) { RhinoModel.createBodyLayer() }
    }

    @SubscribeEvent
    fun registerBER(event: EntityRenderersEvent.RegisterRenderers) {
        event.registerBlockEntityRenderer(
            ModBlockEntities.GEM_POLISHING_BE.get(),
            ::GemPolishingBlockEntityRenderer
        )

        event.registerBlockEntityRenderer(
            ModBlockEntities.MOD_SIGN.get(),
            ::SignRenderer
        )
        event.registerBlockEntityRenderer(
            ModBlockEntities.MOD_HANGING_SIGN.get(),
            ::HangingSignRenderer
        )
    }
}