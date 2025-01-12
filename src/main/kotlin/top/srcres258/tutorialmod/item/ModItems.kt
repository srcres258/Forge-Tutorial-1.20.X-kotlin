package top.srcres258.tutorialmod.item

import net.minecraft.world.item.Item
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.item.custom.FuelItem
import top.srcres258.tutorialmod.item.custom.MetalDetectorItem

object ModItems {
    val ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID)

    val SAPPHIRE by ITEMS.registerObject("sapphire") {
        Item(Item.Properties())
    }
    val RAW_SAPPHIRE by ITEMS.registerObject("raw_sapphire") {
        Item(Item.Properties())
    }

    val METAL_DETECTOR by ITEMS.registerObject("metal_detector") {
        MetalDetectorItem(Item.Properties().durability(100))
    }

    val STRAWBERRY by ITEMS.registerObject("strawberry") {
        Item(Item.Properties().food(ModFoods.STRAWBERRY))
    }

    val PINE_CONE by ITEMS.registerObject("pine_cone") {
        FuelItem(Item.Properties(), 400)
    }

    fun register(eventBus: IEventBus) {
        ITEMS.register(eventBus)
    }
}