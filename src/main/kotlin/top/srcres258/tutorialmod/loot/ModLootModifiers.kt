package top.srcres258.tutorialmod.loot

import com.mojang.serialization.Codec
import net.minecraftforge.common.loot.IGlobalLootModifier
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import top.srcres258.tutorialmod.TutorialMod

object ModLootModifiers {
    val LOOT_MODIFIER_SERIALIZERS: DeferredRegister<Codec<out IGlobalLootModifier>> =
        DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, TutorialMod.MOD_ID)

    val ADD_ITEM: RegistryObject<Codec<out IGlobalLootModifier>> =
        LOOT_MODIFIER_SERIALIZERS.register("add_item") { AddItemModifier.CODEC }
    val ADD_SUS_SAND_ITEM: RegistryObject<Codec<out IGlobalLootModifier>> =
        LOOT_MODIFIER_SERIALIZERS.register("add_sus_sand_item") { AddSusSandItemModifier.CODEC }

    fun register(eventBus: IEventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus)
    }
}