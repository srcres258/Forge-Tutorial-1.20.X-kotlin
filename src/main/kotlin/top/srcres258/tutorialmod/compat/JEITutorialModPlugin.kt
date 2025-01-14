package top.srcres258.tutorialmod.compat

import mezz.jei.api.IModPlugin
import mezz.jei.api.JeiPlugin
import mezz.jei.api.registration.IGuiHandlerRegistration
import mezz.jei.api.registration.IRecipeCategoryRegistration
import mezz.jei.api.registration.IRecipeRegistration
import net.minecraft.client.Minecraft
import net.minecraft.resources.ResourceLocation
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.recipe.GemPolishingRecipe
import top.srcres258.tutorialmod.screen.GemPolishingStationScreen

@JeiPlugin
class JEITutorialModPlugin : IModPlugin {
    override fun getPluginUid() = ResourceLocation(TutorialMod.MOD_ID, "jei_plugin")

    override fun registerCategories(registration: IRecipeCategoryRegistration) {
        registration.addRecipeCategories(GemPolishingCategory(registration.jeiHelpers.guiHelper))
    }

    override fun registerRecipes(registration: IRecipeRegistration) {
        val recipeManager = Minecraft.getInstance().level?.recipeManager ?: return

        val polishingRecipes = recipeManager.getAllRecipesFor(GemPolishingRecipe.Type)
        registration.addRecipes(GemPolishingCategory.GEM_POLISHING_TYPE, polishingRecipes)
    }

    override fun registerGuiHandlers(registration: IGuiHandlerRegistration) {
        registration.addRecipeClickArea(GemPolishingStationScreen::class.java, 60, 30, 20, 30,
            GemPolishingCategory.GEM_POLISHING_TYPE)
    }
}