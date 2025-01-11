package top.srcres258.tutorialmod.item

import net.minecraft.world.item.Item
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject
import top.srcres258.tutorialmod.TutorialMod

object ModItems {
    val ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID)

    val SAPPHIRE by ITEMS.registerObject("sapphire") {
        Item(Item.Properties())
    }
    val RAW_SAPPHIRE by ITEMS.registerObject("raw_sapphire") {
        Item(Item.Properties())
    }

    fun register(eventBus: IEventBus) {
        ITEMS.register(eventBus)
    }
}