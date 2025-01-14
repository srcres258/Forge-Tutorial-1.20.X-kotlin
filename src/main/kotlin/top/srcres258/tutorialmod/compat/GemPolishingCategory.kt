package top.srcres258.tutorialmod.compat

import mezz.jei.api.constants.VanillaTypes
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder
import mezz.jei.api.gui.drawable.IDrawable
import mezz.jei.api.helpers.IGuiHelper
import mezz.jei.api.recipe.IFocusGroup
import mezz.jei.api.recipe.RecipeIngredientRole
import mezz.jei.api.recipe.RecipeType
import mezz.jei.api.recipe.category.IRecipeCategory
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.recipe.GemPolishingRecipe
import top.srcres258.tutorialmod.util.BLANK_REGISTRY_ACCESS

class GemPolishingCategory(
    helper: IGuiHelper
) : IRecipeCategory<GemPolishingRecipe> {
    companion object {
        val UID = ResourceLocation(TutorialMod.MOD_ID, "gem_polishing")
        val TEXTURE = ResourceLocation(TutorialMod.MOD_ID, "textures/gui/gem_polishing_station_gui.png")

        val GEM_POLISHING_TYPE = RecipeType(UID, GemPolishingRecipe::class.java)
    }

    private val background: IDrawable =
        helper.createDrawable(TEXTURE, 0, 0, 176, 85)
    private val icon: IDrawable =
        helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, ItemStack(ModBlocks.GEM_POLISHING_STATION.get()))

    override fun getRecipeType() = GEM_POLISHING_TYPE

    override fun getTitle(): Component = Component.translatable("block.tutorialmod.gem_polishing_station")

    override fun getBackground() = background

    override fun getIcon() = icon

    override fun setRecipe(
        builder: IRecipeLayoutBuilder,
        recipe: GemPolishingRecipe,
        focuses: IFocusGroup
    ) {
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 11).addIngredients(recipe.ingredients[0])
        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 59).addItemStack(recipe.getResultItem(BLANK_REGISTRY_ACCESS))
    }
}