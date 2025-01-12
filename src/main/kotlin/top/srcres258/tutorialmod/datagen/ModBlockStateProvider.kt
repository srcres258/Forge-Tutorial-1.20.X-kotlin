package top.srcres258.tutorialmod.datagen

import net.minecraft.data.PackOutput
import net.minecraft.world.level.block.Block
import net.minecraftforge.client.model.generators.BlockStateProvider
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.registries.RegistryObject
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.ModBlocks

class ModBlockStateProvider(
    output: PackOutput,
    exFileHelper: ExistingFileHelper?
) : BlockStateProvider(output, TutorialMod.MOD_ID, exFileHelper) {
    override fun registerStatesAndModels() {
        blockWithItem(ModBlocks.SAPPHIRE_BLOCK)
        blockWithItem(ModBlocks.RAW_SAPPHIRE_BLOCK)

        blockWithItem(ModBlocks.SAPPHIRE_ORE)
        blockWithItem(ModBlocks.DEEPSLATE_SAPPHIRE_ORE)
        blockWithItem(ModBlocks.END_STONE_SAPPHIRE_ORE)
        blockWithItem(ModBlocks.NETHER_SAPPHIRE_ORE)

        blockWithItem(ModBlocks.SOUND_BLOCK)
    }

    private fun blockWithItem(blockRegistryObject: RegistryObject<out Block>) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()))
    }
}