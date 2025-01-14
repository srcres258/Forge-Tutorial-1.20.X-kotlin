package top.srcres258.tutorialmod.recipe

import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import top.srcres258.tutorialmod.TutorialMod

object ModRecipes {
    val SERIALIZERS: DeferredRegister<RecipeSerializer<*>> =
        DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TutorialMod.MOD_ID)

    val GEM_POLISHING_SERIALIZER: RegistryObject<RecipeSerializer<GemPolishingRecipe>> =
        SERIALIZERS.register("gem_polishing") {
            GemPolishingRecipe.Serializer
        }

    fun register(eventBus: IEventBus) {
        SERIALIZERS.register(eventBus)
    }
}