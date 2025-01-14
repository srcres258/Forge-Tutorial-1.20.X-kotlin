package top.srcres258.tutorialmod.block

import net.minecraft.sounds.SoundEvents
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.ButtonBlock
import net.minecraft.world.level.block.DoorBlock
import net.minecraft.world.level.block.DropExperienceBlock
import net.minecraft.world.level.block.FenceBlock
import net.minecraft.world.level.block.FenceGateBlock
import net.minecraft.world.level.block.FlowerBlock
import net.minecraft.world.level.block.FlowerPotBlock
import net.minecraft.world.level.block.PressurePlateBlock
import net.minecraft.world.level.block.SlabBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.StairBlock
import net.minecraft.world.level.block.TrapDoorBlock
import net.minecraft.world.level.block.WallBlock
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.properties.BlockSetType
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.custom.CornCropBlock
import top.srcres258.tutorialmod.block.custom.GemPolishingStationBlock
import top.srcres258.tutorialmod.block.custom.SoundBlock
import top.srcres258.tutorialmod.block.custom.StrawberryCropBlock
import top.srcres258.tutorialmod.item.ModItems
import top.srcres258.tutorialmod.sound.ModSounds

object ModBlocks {
    val BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID)

    val SAPPHIRE_BLOCK: RegistryObject<Block> = registerBlock("sapphire_block") {
        Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
            .sound(SoundType.AMETHYST))
    }
    val RAW_SAPPHIRE_BLOCK: RegistryObject<Block> = registerBlock("raw_sapphire_block") {
        Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
            .sound(SoundType.AMETHYST))
    }

    val SAPPHIRE_ORE: RegistryObject<Block> = registerBlock("sapphire_ore") {
        DropExperienceBlock(
            BlockBehaviour.Properties.copy(Blocks.STONE)
                .strength(2F)
                .requiresCorrectToolForDrops(),
            UniformInt.of(3, 6)
        )
    }
    val DEEPSLATE_SAPPHIRE_ORE: RegistryObject<Block> = registerBlock("deepslate_sapphire_ore") {
        DropExperienceBlock(
            BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                .strength(3F)
                .requiresCorrectToolForDrops(),
            UniformInt.of(3, 7)
        )
    }
    val NETHER_SAPPHIRE_ORE: RegistryObject<Block> = registerBlock("nether_sapphire_ore") {
        DropExperienceBlock(
            BlockBehaviour.Properties.copy(Blocks.NETHERRACK)
                .strength(5F)
                .requiresCorrectToolForDrops(),
            UniformInt.of(3, 7)
        )
    }
    val END_STONE_SAPPHIRE_ORE: RegistryObject<Block> = registerBlock("end_stone_sapphire_ore") {
        DropExperienceBlock(
            BlockBehaviour.Properties.copy(Blocks.END_STONE)
                .strength(5F)
                .requiresCorrectToolForDrops(),
            UniformInt.of(3, 7)
        )
    }

    val SOUND_BLOCK: RegistryObject<Block> = registerBlock("sound_block") {
        SoundBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
            .sound(ModSounds.SOUND_BLOCK_SOUNDS))
    }

    val SAPPHIRE_STAIRS: RegistryObject<Block> = registerBlock("sapphire_stairs") {
        StairBlock(
            { SAPPHIRE_BLOCK.get().defaultBlockState() },
            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                .sound(SoundType.AMETHYST)
        )
    }
    val SAPPHIRE_SLAB: RegistryObject<Block> = registerBlock("sapphire_slab") {
        SlabBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                .sound(SoundType.AMETHYST))
    }

    val SAPPHIRE_BUTTON: RegistryObject<Block> = registerBlock("sapphire_button") {
        ButtonBlock(
            BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON)
                .sound(SoundType.AMETHYST),
            BlockSetType.IRON,
            10,
            true
        )
    }
    val SAPPHIRE_PRESSURE_PLATE: RegistryObject<Block> = registerBlock("sapphire_pressure_plate") {
        PressurePlateBlock(
            PressurePlateBlock.Sensitivity.EVERYTHING,
            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                .sound(SoundType.AMETHYST),
            BlockSetType.IRON
        )
    }

    val SAPPHIRE_FENCE: RegistryObject<Block> = registerBlock("sapphire_fence") {
        FenceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
            .sound(SoundType.AMETHYST))
    }
    val SAPPHIRE_FENCE_GATE: RegistryObject<Block> = registerBlock("sapphire_fence_gate") {
        FenceGateBlock(
            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                .sound(SoundType.AMETHYST),
            SoundEvents.CHAIN_PLACE,
            SoundEvents.ANVIL_BREAK
        )
    }
    val SAPPHIRE_WALL: RegistryObject<Block> = registerBlock("sapphire_wall") {
        WallBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
            .sound(SoundType.AMETHYST))
    }

    val SAPPHIRE_DOOR: RegistryObject<Block> = registerBlock("sapphire_door") {
        DoorBlock(
            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                .sound(SoundType.AMETHYST)
                .noOcclusion(),
            BlockSetType.IRON
        )
    }
    val SAPPHIRE_TRAPDOOR: RegistryObject<Block> = registerBlock("sapphire_trapdoor") {
        TrapDoorBlock(
            BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                .sound(SoundType.AMETHYST)
                .noOcclusion(),
            BlockSetType.IRON
        )
    }

    val STRAWBERRY_CROP: RegistryObject<Block> = BLOCKS.register("strawberry_crop") {
        StrawberryCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)
            .noOcclusion()
            .noCollission())
    }

    val CORN_CROP: RegistryObject<Block> = BLOCKS.register("corn_crop") {
        CornCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)
            .noOcclusion()
            .noCollission())
    }

    val CATMINT: RegistryObject<Block> = registerBlock("catmint") {
        FlowerBlock(
            { MobEffects.LUCK },
            5,
            BlockBehaviour.Properties.copy(Blocks.ALLIUM)
                .noOcclusion()
                .noCollission()
        )
    }
    val POTTED_CATMINT: RegistryObject<Block> = BLOCKS.register("potted_catmint") {
        FlowerPotBlock(
            { Blocks.FLOWER_POT as FlowerPotBlock },
            CATMINT,
            BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM)
                .noOcclusion()
        )
    }

    val GEM_POLISHING_STATION: RegistryObject<Block> = registerBlock("gem_polishing_station") {
        GemPolishingStationBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
            .noOcclusion())
    }

    private fun <T: Block> registerBlock(
        name: String,
        block: () -> T
    ) = BLOCKS.register(name, block).apply { registerBlockItem(name, this) }

    private fun <T: Block> registerBlockItem(
        name: String,
        block: RegistryObject<T>
    ) = ModItems.ITEMS.register(name) { BlockItem(block.get(), Item.Properties()) }

    fun register(eventBus: IEventBus) {
        BLOCKS.register(eventBus)
    }
}