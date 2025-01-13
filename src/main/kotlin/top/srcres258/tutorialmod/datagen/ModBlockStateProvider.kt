package top.srcres258.tutorialmod.datagen

import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.client.model.generators.BlockStateProvider
import net.minecraftforge.client.model.generators.ConfiguredModel
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.registries.RegistryObject
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.block.custom.CornCropBlock
import top.srcres258.tutorialmod.block.custom.StrawberryCropBlock

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

        stairsBlock(ModBlocks.SAPPHIRE_STAIRS.get() as StairBlock,
            blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()))
        slabBlock(ModBlocks.SAPPHIRE_SLAB.get() as SlabBlock,
            blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()),
            blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()))
        buttonBlock(ModBlocks.SAPPHIRE_BUTTON.get() as ButtonBlock,
            blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()))
        pressurePlateBlock(ModBlocks.SAPPHIRE_PRESSURE_PLATE.get() as PressurePlateBlock,
            blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()))

        fenceBlock(ModBlocks.SAPPHIRE_FENCE.get() as FenceBlock,
            blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()))
        fenceGateBlock(ModBlocks.SAPPHIRE_FENCE_GATE.get() as FenceGateBlock,
            blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()))
        wallBlock(ModBlocks.SAPPHIRE_WALL.get() as WallBlock,
            blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()))

        doorBlockWithRenderType(ModBlocks.SAPPHIRE_DOOR.get() as DoorBlock,
            modLoc("block/sapphire_door_bottom"),
            modLoc("block/sapphire_door_top"),
            "cutout")
        trapdoorBlockWithRenderType(ModBlocks.SAPPHIRE_TRAPDOOR.get() as TrapDoorBlock,
            modLoc("block/sapphire_trapdoor"),
            true,
            "cutout")

        makeStrawberryCrop(ModBlocks.STRAWBERRY_CROP.get() as StrawberryCropBlock,
            "strawberry_stage", "strawberry_stage")
        makeCornCrop(ModBlocks.CORN_CROP.get() as CornCropBlock,
            "corn_stage_", "corn_stage_")

        simpleBlockWithItem(
            ModBlocks.CATMINT.get(),
            models()
                .cross(blockTexture(ModBlocks.CATMINT.get()).path, blockTexture(ModBlocks.CATMINT.get()))
                .renderType("cutout")
        )
        simpleBlockWithItem(
            ModBlocks.POTTED_CATMINT.get(),
            models()
                .singleTexture("potted_catmint", ResourceLocation("flower_pot_cross"), "plant",
                    blockTexture(ModBlocks.CATMINT.get()))
                .renderType("cutout")
        )
    }

    private fun makeStrawberryCrop(
        block: StrawberryCropBlock,
        modelName: String,
        textureName: String
    ) {
        getVariantBuilder(block).forAllStates { state ->
            strawberryStates(state, block, modelName, textureName)
        }
    }

    private fun strawberryStates(
        state: BlockState,
        block: StrawberryCropBlock,
        modelName: String,
        textureName: String
    ) = arrayOf(ConfiguredModel(
        models()
            .crop("$modelName${state.getValue(block.ageProperty)}",
                ResourceLocation(TutorialMod.MOD_ID, "block/$textureName${state.getValue(block.ageProperty)}"))
            .renderType("cutout")
    ))

    private fun makeCornCrop(
        block: CornCropBlock,
        modelName: String,
        textureName: String
    ) {
        getVariantBuilder(block).forAllStates { state ->
            cornStates(state, block, modelName, textureName)
        }
    }

    private fun cornStates(
        state: BlockState,
        block: CornCropBlock,
        modelName: String,
        textureName: String
    ) = arrayOf(ConfiguredModel(
        models()
            .crop("$modelName${state.getValue(block.ageProperty)}",
                ResourceLocation(TutorialMod.MOD_ID, "block/$textureName${state.getValue(block.ageProperty)}"))
            .renderType("cutout")
    ))

    private fun blockWithItem(blockRegistryObject: RegistryObject<out Block>) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()))
    }
}