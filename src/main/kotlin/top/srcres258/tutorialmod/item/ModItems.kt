package top.srcres258.tutorialmod.item

import net.minecraft.world.item.AxeItem
import net.minecraft.world.item.HoeItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.PickaxeItem
import net.minecraft.world.item.ShovelItem
import net.minecraft.world.item.SwordItem
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

    val SAPPHIRE_SWORD: RegistryObject<Item> = ITEMS.register("sapphire_sword") {
        SwordItem(ModToolTiers.SAPPHIRE, 4, 2F, Item.Properties())
    }
    val SAPPHIRE_PICKAXE: RegistryObject<Item> = ITEMS.register("sapphire_pickaxe") {
        PickaxeItem(ModToolTiers.SAPPHIRE, 1, 1F, Item.Properties())
    }
    val SAPPHIRE_AXE: RegistryObject<Item> = ITEMS.register("sapphire_axe") {
        AxeItem(ModToolTiers.SAPPHIRE, 7F, 1F, Item.Properties())
    }
    val SAPPHIRE_SHOVEL: RegistryObject<Item> = ITEMS.register("sapphire_shovel") {
        ShovelItem(ModToolTiers.SAPPHIRE, 0F, 0F, Item.Properties())
    }
    val SAPPHIRE_HOE: RegistryObject<Item> = ITEMS.register("sapphire_hoe") {
        HoeItem(ModToolTiers.SAPPHIRE, 0, 0F, Item.Properties())
    }

    fun register(eventBus: IEventBus) {
        ITEMS.register(eventBus)
    }
}