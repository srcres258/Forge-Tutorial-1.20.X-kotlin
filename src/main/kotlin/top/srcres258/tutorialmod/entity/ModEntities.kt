package top.srcres258.tutorialmod.entity

import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.entity.custom.ModBoatEntity
import top.srcres258.tutorialmod.entity.custom.ModChestBoatEntity
import top.srcres258.tutorialmod.entity.custom.RhinoEntity

object ModEntities {
    val ENTITY_TYPES: DeferredRegister<EntityType<*>> =
        DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TutorialMod.MOD_ID)

    val RHINO: RegistryObject<EntityType<RhinoEntity>> =
        ENTITY_TYPES.register("rhino") {
            EntityType.Builder.of(::RhinoEntity, MobCategory.CREATURE)
                .sized(2.5F, 2.5F)
                .build("rhino")
        }

    val MOD_BOAT: RegistryObject<EntityType<ModBoatEntity>> =
        ENTITY_TYPES.register("mod_boat") {
            EntityType.Builder.of(::ModBoatEntity, MobCategory.MISC)
                .sized(1.375F, 0.5625F)
                .build("mod_boat")
        }
    val MOD_CHEST_BOAT: RegistryObject<EntityType<ModChestBoatEntity>> =
        ENTITY_TYPES.register("mod_chest_boat") {
            EntityType.Builder.of(::ModChestBoatEntity, MobCategory.MISC)
                .sized(1.375F, 0.5625F)
                .build("mod_chest_boat")
        }

    fun register(eventBus: IEventBus) {
        ENTITY_TYPES.register(eventBus)
    }
}