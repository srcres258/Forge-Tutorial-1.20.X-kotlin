package top.srcres258.tutorialmod.datagen

import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraftforge.client.model.generators.ItemModelProvider
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.item.ModItems

class ModItemModelProvider(
    output: PackOutput,
    existingFileHelper: ExistingFileHelper?
) : ItemModelProvider(output, TutorialMod.MOD_ID, existingFileHelper) {
    override fun registerModels() {
        simpleItem(ModItems.SAPPHIRE)
        simpleItem(ModItems.RAW_SAPPHIRE)

        simpleItem(ModItems.METAL_DETECTOR)
        simpleItem(ModItems.PINE_CONE)
        simpleItem(ModItems.STRAWBERRY)

        simpleBlockItem(ModBlocks.SAPPHIRE_DOOR)

        fenceItem(ModBlocks.SAPPHIRE_FENCE, ModBlocks.SAPPHIRE_BLOCK)
        buttonItem(ModBlocks.SAPPHIRE_BUTTON, ModBlocks.SAPPHIRE_BLOCK)
        wallItem(ModBlocks.SAPPHIRE_WALL, ModBlocks.SAPPHIRE_BLOCK)

        evenSimplerBlockItem(ModBlocks.SAPPHIRE_STAIRS)
        evenSimplerBlockItem(ModBlocks.SAPPHIRE_SLAB)
        evenSimplerBlockItem(ModBlocks.SAPPHIRE_PRESSURE_PLATE)
        evenSimplerBlockItem(ModBlocks.SAPPHIRE_FENCE_GATE)

        trapdoorItem(ModBlocks.SAPPHIRE_TRAPDOOR)

        handheldItem(ModItems.SAPPHIRE_SWORD)
        handheldItem(ModItems.SAPPHIRE_PICKAXE)
        handheldItem(ModItems.SAPPHIRE_AXE)
        handheldItem(ModItems.SAPPHIRE_SHOVEL)
        handheldItem(ModItems.SAPPHIRE_HOE)
    }

    private fun simpleItem(item: RegistryObject<out Item>) =
        withExistingParent(item.id.path, ResourceLocation("item/generated"))
            .texture("layer0", ResourceLocation(TutorialMod.MOD_ID, "item/${item.id.path}"))

    private fun evenSimplerBlockItem(block: RegistryObject<out Block>) {
        withExistingParent("${TutorialMod.MOD_ID}:${ForgeRegistries.BLOCKS.getKey(block.get())!!.path}",
            modLoc("block/${ForgeRegistries.BLOCKS.getKey(block.get())!!.path}"))
    }

    private fun trapdoorItem(block: RegistryObject<out Block>) {
        withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get())!!.path,
            modLoc("block/${ForgeRegistries.BLOCKS.getKey(block.get())!!.path}_bottom"))
    }

    private fun fenceItem(block: RegistryObject<out Block>, baseBlock: RegistryObject<out Block>) {
        withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get())!!.path,
            mcLoc("block/fence_inventory"))
            .texture("texture", ResourceLocation(TutorialMod.MOD_ID,
                "block/${ForgeRegistries.BLOCKS.getKey(baseBlock.get())!!.path}"))
    }

    private fun buttonItem(block: RegistryObject<out Block>, baseBlock: RegistryObject<out Block>) {
        withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get())!!.path,
            mcLoc("block/button_inventory"))
            .texture("texture", ResourceLocation(TutorialMod.MOD_ID,
                "block/${ForgeRegistries.BLOCKS.getKey(baseBlock.get())!!.path}"))
    }

    private fun wallItem(block: RegistryObject<out Block>, baseBlock: RegistryObject<out Block>) {
        withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get())!!.path,
            mcLoc("block/wall_inventory"))
            .texture("wall", ResourceLocation(TutorialMod.MOD_ID,
                "block/${ForgeRegistries.BLOCKS.getKey(baseBlock.get())!!.path}"))
    }

    private fun handheldItem(item: RegistryObject<out Item>) =
        withExistingParent(item.id.path, ResourceLocation("item/handheld"))
            .texture("layer0", ResourceLocation(TutorialMod.MOD_ID, "item/${item.id.path}"))

    private fun simpleBlockItem(item: RegistryObject<out Block>) =
        withExistingParent(item.id.path, ResourceLocation("item/generated"))
            .texture("layer0", ResourceLocation(TutorialMod.MOD_ID, "item/${item.id.path}"))
}