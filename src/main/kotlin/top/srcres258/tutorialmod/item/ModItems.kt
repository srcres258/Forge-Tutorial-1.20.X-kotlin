package top.srcres258.tutorialmod.item

import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.AxeItem
import net.minecraft.world.item.HangingSignItem
import net.minecraft.world.item.HoeItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemNameBlockItem
import net.minecraft.world.item.PickaxeItem
import net.minecraft.world.item.RecordItem
import net.minecraft.world.item.ShovelItem
import net.minecraft.world.item.SignItem
import net.minecraft.world.item.SwordItem
import net.minecraftforge.common.ForgeSpawnEggItem
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.entity.ModEntities
import top.srcres258.tutorialmod.entity.custom.ModBoatEntity
import top.srcres258.tutorialmod.item.custom.*
import top.srcres258.tutorialmod.sound.ModSounds

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

    val SAPPHIRE_HELMET: RegistryObject<Item> = ITEMS.register("sapphire_helmet") {
        ModArmorItem(ModArmorMaterials.SAPPHIRE, ArmorItem.Type.HELMET, Item.Properties())
    }
    val SAPPHIRE_CHESTPLATE: RegistryObject<Item> = ITEMS.register("sapphire_chestplate") {
        ArmorItem(ModArmorMaterials.SAPPHIRE, ArmorItem.Type.CHESTPLATE, Item.Properties())
    }
    val SAPPHIRE_LEGGINGS: RegistryObject<Item> = ITEMS.register("sapphire_leggings") {
        ArmorItem(ModArmorMaterials.SAPPHIRE, ArmorItem.Type.LEGGINGS, Item.Properties())
    }
    val SAPPHIRE_BOOTS: RegistryObject<Item> = ITEMS.register("sapphire_boots") {
        ArmorItem(ModArmorMaterials.SAPPHIRE, ArmorItem.Type.BOOTS, Item.Properties())
    }

    val STRAWBERRY_SEEDS: RegistryObject<Item> = ITEMS.register("strawberry_seeds") {
        ItemNameBlockItem(ModBlocks.STRAWBERRY_CROP.get(), Item.Properties())
    }

    val CORN_SEEDS: RegistryObject<Item> = ITEMS.register("corn_seeds") {
        ItemNameBlockItem(ModBlocks.CORN_CROP.get(), Item.Properties())
    }
    val CORN: RegistryObject<Item> = ITEMS.register("corn") {
        Item(Item.Properties())
    }

    val BAR_BRAWL_MUSIC_DISC: RegistryObject<Item> = ITEMS.register("bar_brawl_music_disc") {
        RecordItem(6, ModSounds.BAR_BRAWL, Item.Properties().stacksTo(1), 2440)
    }

    val RHINO_SPAWN_EGG: RegistryObject<Item> = ITEMS.register("rhino_spawn_egg") {
        ForgeSpawnEggItem(ModEntities.RHINO, 0x7E9680, 0xC5D1C5, Item.Properties())
    }

    val PINE_SIGN: RegistryObject<Item> = ITEMS.register("pine_sign") {
        SignItem(
            Item.Properties().stacksTo(16),
            ModBlocks.PINE_SIGN.get(),
            ModBlocks.PINE_WALL_SIGN.get()
        )
    }
    val PINE_HANGING_SIGN: RegistryObject<Item> = ITEMS.register("pine_hanging_sign") {
        HangingSignItem(
            ModBlocks.PINE_HANGING_SIGN.get(),
            ModBlocks.PINE_WALL_HANGING_SIGN.get(),
            Item.Properties().stacksTo(16)
        )
    }

    val PINE_BOAT: RegistryObject<Item> = ITEMS.register("pine_boat") {
        ModBoatItem(false, ModBoatEntity.Type.PINE, Item.Properties())
    }
    val PINE_CHEST_BOAT: RegistryObject<Item> = ITEMS.register("pine_chest_boat") {
        ModBoatItem(true, ModBoatEntity.Type.PINE, Item.Properties())
    }

    val DICE: RegistryObject<Item> = ITEMS.register("dice") {
        DiceItem(Item.Properties())
    }

    fun register(eventBus: IEventBus) {
        ITEMS.register(eventBus)
    }
}