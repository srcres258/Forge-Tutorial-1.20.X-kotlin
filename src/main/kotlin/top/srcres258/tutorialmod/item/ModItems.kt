package top.srcres258.tutorialmod.item

import net.minecraft.world.item.Item
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.item.custom.FuelItem
import top.srcres258.tutorialmod.item.custom.MetalDetectorItem

object ModItems {
    val ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID)

    val SAPPHIRE: RegistryObject<Item> = ITEMS.register("sapphire") {
        Item(Item.Properties())
    }
    val RAW_SAPPHIRE: RegistryObject<Item> = ITEMS.register("raw_sapphire") {
        Item(Item.Properties())
    }

    val METAL_DETECTOR: RegistryObject<Item> = ITEMS.register("metal_detector") {
        MetalDetectorItem(Item.Properties().durability(100))
    }

    val STRAWBERRY: RegistryObject<Item> = ITEMS.register("strawberry") {
        Item(Item.Properties().food(ModFoods.STRAWBERRY))
    }

    val PINE_CONE: RegistryObject<Item> = ITEMS.register("pine_cone") {
        FuelItem(Item.Properties(), 400)
    }

    val SAPPHIRE_STAFF: RegistryObject<Item> = ITEMS.register("sapphire_staff") {
        Item(Item.Properties().stacksTo(1))
    }

    fun register(eventBus: IEventBus) {
        ITEMS.register(eventBus)
    }
}