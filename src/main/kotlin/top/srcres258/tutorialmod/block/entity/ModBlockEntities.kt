package top.srcres258.tutorialmod.block.entity

import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.ModBlocks

object ModBlockEntities {
    val BLOCK_ENTITIES: DeferredRegister<BlockEntityType<*>> =
        DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TutorialMod.MOD_ID)

    val GEM_POLISHING_BE: RegistryObject<BlockEntityType<GemPolishingStationBlockEntity>> =
        BLOCK_ENTITIES.register("gem_polishing_be") {
            BlockEntityType.Builder.of(
                ::GemPolishingStationBlockEntity,
                ModBlocks.GEM_POLISHING_STATION.get()
            ).build(null)
        }

    val MOD_SIGN: RegistryObject<BlockEntityType<ModSignBlockEntity>> =
        BLOCK_ENTITIES.register("mod_sign") {
            BlockEntityType.Builder.of(
                ::ModSignBlockEntity,
                ModBlocks.PINE_SIGN.get(),
                ModBlocks.PINE_WALL_SIGN.get()
            ).build(null)
        }

    val MOD_HANGING_SIGN: RegistryObject<BlockEntityType<ModHangingSignBlockEntity>> =
        BLOCK_ENTITIES.register("mod_hanging_sign") {
            BlockEntityType.Builder.of(
                ::ModHangingSignBlockEntity,
                ModBlocks.PINE_HANGING_SIGN.get(),
                ModBlocks.PINE_WALL_HANGING_SIGN.get()
            ).build(null)
        }

    fun register(eventBus: IEventBus) {
        BLOCK_ENTITIES.register(eventBus)
    }
}