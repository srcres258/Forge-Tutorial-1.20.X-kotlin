package top.srcres258.tutorialmod.block

import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.DropExperienceBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.custom.SoundBlock
import top.srcres258.tutorialmod.item.ModItems

object ModBlocks {
    val BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID)

    val SAPPHIRE_BLOCK = registerBlock("sapphire_block") {
        Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
            .sound(SoundType.AMETHYST))
    }
    val RAW_SAPPHIRE_BLOCK = registerBlock("raw_sapphire_block") {
        Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
            .sound(SoundType.AMETHYST))
    }

    val SAPPHIRE_ORE = registerBlock("sapphire_ore") {
        DropExperienceBlock(
            BlockBehaviour.Properties.copy(Blocks.STONE)
                .strength(2F)
                .requiresCorrectToolForDrops(),
            UniformInt.of(3, 6)
        )
    }
    val DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore") {
        DropExperienceBlock(
            BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                .strength(3F)
                .requiresCorrectToolForDrops(),
            UniformInt.of(3, 7)
        )
    }
    val NETHER_SAPPHIRE_ORE = registerBlock("nether_sapphire_ore") {
        DropExperienceBlock(
            BlockBehaviour.Properties.copy(Blocks.NETHERRACK)
                .strength(5F)
                .requiresCorrectToolForDrops(),
            UniformInt.of(3, 7)
        )
    }
    val END_STONE_SAPPHIRE_ORE = registerBlock("end_stone_sapphire_ore") {
        DropExperienceBlock(
            BlockBehaviour.Properties.copy(Blocks.END_STONE)
                .strength(5F)
                .requiresCorrectToolForDrops(),
            UniformInt.of(3, 7)
        )
    }

    val SOUND_BLOCK = registerBlock("sound_block") {
        SoundBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK))
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