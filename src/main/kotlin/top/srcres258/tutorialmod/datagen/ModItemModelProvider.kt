package top.srcres258.tutorialmod.datagen

import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraftforge.client.model.generators.ItemModelProvider
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.registries.RegistryObject
import top.srcres258.tutorialmod.TutorialMod
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
    }

    private fun simpleItem(item: RegistryObject<out Item>) =
        withExistingParent(item.id.path, ResourceLocation("item/generated"))
            .texture("layer0", ResourceLocation(TutorialMod.MOD_ID, "item/${item.id.path}"))
}