package top.srcres258.tutorialmod.worldgen.tree

import net.minecraft.core.registries.Registries
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.RegistryObject
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.worldgen.tree.custom.PineTrunkPlacer

object ModTrunkPlacerTypes {
    val TRUNK_PLACER: DeferredRegister<TrunkPlacerType<*>> =
        DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, TutorialMod.MOD_ID)

    val PINE_TRUNK_PLACER: RegistryObject<TrunkPlacerType<PineTrunkPlacer>> =
        TRUNK_PLACER.register("pine_trunk_placer") {
            TrunkPlacerType(PineTrunkPlacer.CODEC)
        }

    fun register(eventBus: IEventBus) {
        TRUNK_PLACER.register(eventBus)
    }
}