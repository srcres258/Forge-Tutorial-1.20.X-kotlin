package top.srcres258.tutorialmod.block

import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import top.srcres258.tutorialmod.TutorialMod
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