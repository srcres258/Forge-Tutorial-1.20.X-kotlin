package top.srcres258.tutorialmod.event

import net.minecraftforge.event.entity.EntityAttributeCreationEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.entity.ModEntities
import top.srcres258.tutorialmod.entity.custom.RhinoEntity

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
object ModEventBusEvents {
    @SubscribeEvent
    fun registerAttributes(event: EntityAttributeCreationEvent) {
        event.put(ModEntities.RHINO.get(), RhinoEntity.createAttributes().build())
    }
}