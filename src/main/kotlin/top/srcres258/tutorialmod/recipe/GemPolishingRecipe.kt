package top.srcres258.tutorialmod.recipe

import com.google.gson.JsonObject
import net.minecraft.core.NonNullList
import net.minecraft.core.RegistryAccess
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.GsonHelper
import net.minecraft.world.SimpleContainer
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.item.crafting.RecipeType
import net.minecraft.world.item.crafting.ShapedRecipe
import net.minecraft.world.level.Level
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.util.BLANK_REGISTRY_ACCESS

class GemPolishingRecipe(
    private val inputItems: List<Ingredient>,
    private val output: ItemStack,
    private val id: ResourceLocation
) : Recipe<SimpleContainer> {
    override fun matches(container: SimpleContainer, level: Level) =
        if (level.isClientSide) {
            false
        } else {
            inputItems[0].test(container.getItem(0))
        }

    override fun getIngredients(): NonNullList<Ingredient> =
        NonNullList.withSize(inputItems.size, Ingredient.EMPTY)
            .also { ingredients ->
                ingredients.indices.forEach { i ->
                    ingredients[i] = inputItems[i]
                }
            }

    override fun assemble(p0: SimpleContainer, p1: RegistryAccess): ItemStack = output.copy()

    override fun canCraftInDimensions(p0: Int, p1: Int) = true

    override fun getResultItem(p0: RegistryAccess): ItemStack = output.copy()

    override fun getId() = id

    override fun getSerializer() = Serializer

    override fun getType() = Type

    object Type : RecipeType<GemPolishingRecipe> {
        const val ID = "gem_polishing"
    }

    object Serializer : RecipeSerializer<GemPolishingRecipe> {
        val ID = ResourceLocation(TutorialMod.MOD_ID, "gem_polishing")

        override fun fromJson(recipeId: ResourceLocation, serializedRecipe: JsonObject): GemPolishingRecipe {
            val output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(
                serializedRecipe, "output"))
            val ingredients = GsonHelper.getAsJsonArray(serializedRecipe, "ingredients")
            val inputs = MutableList<Ingredient>(1) { Ingredient.EMPTY }

            for (i in inputs.indices) {
                inputs[i] = Ingredient.fromJson(ingredients[i])
            }

            return GemPolishingRecipe(inputs, output, recipeId)
        }

        override fun fromNetwork(recipeId: ResourceLocation, buffer: FriendlyByteBuf): GemPolishingRecipe {
            val inputs = MutableList(buffer.readInt()) { Ingredient.EMPTY }

            for (i in inputs.indices) {
                inputs[i] = Ingredient.fromNetwork(buffer)
            }

            val output = buffer.readItem()
            return GemPolishingRecipe(inputs, output, recipeId)
        }

        override fun toNetwork(buffer: FriendlyByteBuf, recipe: GemPolishingRecipe) {
            buffer.writeInt(recipe.inputItems.size)

            for (ingredient in recipe.ingredients) {
                ingredient.toNetwork(buffer)
            }

            buffer.writeItemStack(recipe.getResultItem(BLANK_REGISTRY_ACCESS), false)
        }
    }
}