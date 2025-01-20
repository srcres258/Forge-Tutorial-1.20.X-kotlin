package top.srcres258.tutorialmod.worldgen.tree

import net.minecraft.core.registries.Registries
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.RegistryObject
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.worldgen.tree.custom.PineFoliagePlacer

object ModFoliagePlacers {
    val FOLIAGE_PLACERS: DeferredRegister<FoliagePlacerType<*>> =
        DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, TutorialMod.MOD_ID)

    val PINE_FOLIAGE_PLACER: RegistryObject<FoliagePlacerType<PineFoliagePlacer>> =
        FOLIAGE_PLACERS.register("pine_foliage_placer") {
            FoliagePlacerType(PineFoliagePlacer.CODEC)
        }

    fun register(eventBus: IEventBus) {
        FOLIAGE_PLACERS.register(eventBus)
    }
}