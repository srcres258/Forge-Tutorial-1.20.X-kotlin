package top.srcres258.tutorialmod.datagen

import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.data.recipes.ShapelessRecipeBuilder
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder
import net.minecraft.world.item.crafting.AbstractCookingRecipe
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.level.ItemLike
import net.minecraftforge.common.crafting.conditions.IConditionBuilder
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.item.ModItems
import java.util.function.Consumer

private val SAPPHIRE_SMELTABLES = listOf<ItemLike>(ModItems.RAW_SAPPHIRE.get(),
    ModBlocks.SAPPHIRE_ORE.get(), ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),
    ModBlocks.NETHER_SAPPHIRE_ORE.get(), ModBlocks.END_STONE_SAPPHIRE_ORE.get())

class ModRecipeProvider(pOutput: PackOutput) : RecipeProvider(pOutput), IConditionBuilder {
    override fun buildRecipes(pWriter: Consumer<FinishedRecipe>) {
        oreSmelting(pWriter::accept, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(),
            0.25F, 100, "sapphire")
        oreBlasting(pWriter::accept, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(),
            0.25F, 100, "sapphire")

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SAPPHIRE_BLOCK.get())
            .pattern("SSS")
            .pattern("SSS")
            .pattern("SSS")
            .define('S', ModItems.SAPPHIRE.get())
            .unlockedBy(getHasName(ModItems.SAPPHIRE.get()), has(ModItems.SAPPHIRE.get()))
            .save(pWriter)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 9)
            .requires(ModBlocks.SAPPHIRE_BLOCK.get())
            .unlockedBy(getHasName(ModBlocks.SAPPHIRE_BLOCK.get()), has(ModBlocks.SAPPHIRE_BLOCK.get()))
            .save(pWriter)
    }

    private fun oreSmelting(
        finishedRecipeConsumer: (FinishedRecipe) -> Unit,
        ingredients: List<ItemLike>,
        category: RecipeCategory,
        result: ItemLike,
        experience: Float,
        cookingTime: Int,
        group: String
    ) {
        oreCooking(finishedRecipeConsumer,
            RecipeSerializer.SMELTING_RECIPE,
            ingredients,
            category,
            result,
            experience,
            cookingTime,
            group,
            "_from_smelting")
    }

    private fun oreBlasting(
        finishedRecipeConsumer: (FinishedRecipe) -> Unit,
        ingredients: List<ItemLike>,
        category: RecipeCategory,
        result: ItemLike,
        experience: Float,
        cookingTime: Int,
        group: String
    ) {
        oreCooking(finishedRecipeConsumer,
            RecipeSerializer.BLASTING_RECIPE,
            ingredients,
            category,
            result,
            experience,
            cookingTime,
            group,
            "_from_blasting")
    }

    private fun oreCooking(
        finishedRecipeConsumer: (FinishedRecipe) -> Unit,
        cookingSerializer: RecipeSerializer<out AbstractCookingRecipe>,
        ingredients: List<ItemLike>,
        category: RecipeCategory,
        result: ItemLike,
        experience: Float,
        cookingTime: Int,
        group: String,
        recipeName: String
    ) {
        for (item in ingredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(item),
                category,
                result,
                experience,
                cookingTime,
                cookingSerializer)
                .group(group)
                .unlockedBy(getHasName(item), has(item))
                .save(finishedRecipeConsumer, "${TutorialMod.MOD_ID}:${getItemName(item)}${recipeName}" +
                        "_${getItemName(item)}")
        }
    }
}