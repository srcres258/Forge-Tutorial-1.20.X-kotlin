package top.srcres258.tutorialmod.villager

import com.google.common.collect.ImmutableSet
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.entity.ai.village.poi.PoiType
import net.minecraft.world.entity.npc.VillagerProfession
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.ModBlocks

object ModVillagers {
    val POI_TYPES: DeferredRegister<PoiType> =
        DeferredRegister.create(ForgeRegistries.POI_TYPES, TutorialMod.MOD_ID)
    val VILLAGER_PROFESSIONS: DeferredRegister<VillagerProfession> =
        DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, TutorialMod.MOD_ID)

    val SOUND_POI: RegistryObject<PoiType> = POI_TYPES.register("sound_poi") {
        PoiType(ModBlocks.SOUND_BLOCK.get().stateDefinition.possibleStates.toSet(),
            1, 1)
    }

    val SOUND_MASTER: RegistryObject<VillagerProfession> =
        VILLAGER_PROFESSIONS.register("soundmaster") {
            VillagerProfession(
                "soundmaster",
                { holder -> holder.get() == SOUND_POI.get() },
                { holder -> holder.get() == SOUND_POI.get() },
                ImmutableSet.of(),
                ImmutableSet.of(),
                SoundEvents.VILLAGER_WORK_ARMORER
            )
        }

    fun register(eventBus: IEventBus) {
        POI_TYPES.register(eventBus)
        VILLAGER_PROFESSIONS.register(eventBus)
    }
}