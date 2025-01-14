package top.srcres258.tutorialmod.event

import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.client.event.EntityRenderersEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.entity.client.ModModelLayers
import top.srcres258.tutorialmod.entity.client.RhinoModel

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
}