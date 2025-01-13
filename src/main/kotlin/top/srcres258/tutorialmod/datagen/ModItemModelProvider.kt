package top.srcres258.tutorialmod.datagen

import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.PackType
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.armortrim.TrimMaterial
import net.minecraft.world.item.armortrim.TrimMaterials
import net.minecraft.world.level.block.Block
import net.minecraftforge.client.model.generators.ItemModelProvider
import net.minecraftforge.client.model.generators.ModelFile
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.item.ModItems

private val trimMaterials = linkedMapOf<ResourceKey<TrimMaterial>, Float>(
    Pair(TrimMaterials.QUARTZ, 0.1F),
    Pair(TrimMaterials.IRON, 0.2F),
    Pair(TrimMaterials.NETHERITE, 0.3F),
    Pair(TrimMaterials.REDSTONE, 0.4F),
    Pair(TrimMaterials.COPPER, 0.5F),
    Pair(TrimMaterials.GOLD, 0.6F),
    Pair(TrimMaterials.EMERALD, 0.7F),
    Pair(TrimMaterials.DIAMOND, 0.8F),
    Pair(TrimMaterials.LAPIS, 0.9F),
    Pair(TrimMaterials.AMETHYST, 1.0F)
)

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

        trimmedArmorItem(ModItems.SAPPHIRE_HELMET)
        trimmedArmorItem(ModItems.SAPPHIRE_CHESTPLATE)
        trimmedArmorItem(ModItems.SAPPHIRE_LEGGINGS)
        trimmedArmorItem(ModItems.SAPPHIRE_BOOTS)
    }

    private fun trimmedArmorItem(itemRegObj: RegistryObject<out Item>) {
        val MOD_ID = TutorialMod.MOD_ID

        val armorItem = itemRegObj.get()
        if (armorItem is ArmorItem) {
            trimMaterials.entries.forEach { entry ->
                val trimMaterial = entry.key
                val trimValue = entry.value

                val armorType = when (armorItem.equipmentSlot) {
                    EquipmentSlot.HEAD -> "helmet"
                    EquipmentSlot.CHEST -> "chestplate"
                    EquipmentSlot.LEGS -> "leggings"
                    EquipmentSlot.FEET -> "boots"
                    else -> ""
                }

                val armorItemPath = "item/$armorItem"
                val trimPath = "trims/items/${armorType}_trim_${trimMaterial.location().path}"
                val currentTrimName = "${armorItemPath}_${trimMaterial.location().path}_trim"
                val armorItemResLoc = ResourceLocation(MOD_ID, armorItemPath)
                val trimResLoc = ResourceLocation(trimPath)
                val trimNameResLoc = ResourceLocation(MOD_ID, currentTrimName)

                existingFileHelper?.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures")

                getBuilder(currentTrimName)
                    .parent(ModelFile.UncheckedModelFile("item/generated"))
                    .texture("layer0", armorItemResLoc)
                    .texture("layer1", trimResLoc)

                withExistingParent(itemRegObj.id.path, mcLoc("item/generated"))
                    .override()
                    .model(ModelFile.UncheckedModelFile(trimNameResLoc))
                    .predicate(mcLoc("trim_type"), trimValue)
                    .end()
                    .texture("layer0", ResourceLocation(MOD_ID, "item/${itemRegObj.id.path}"))
            }
        }
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