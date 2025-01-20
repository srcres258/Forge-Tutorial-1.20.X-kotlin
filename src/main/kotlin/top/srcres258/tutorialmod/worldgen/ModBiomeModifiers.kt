package top.srcres258.tutorialmod.worldgen

import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BiomeTags
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraftforge.common.Tags
import net.minecraftforge.common.world.BiomeModifier
import net.minecraftforge.common.world.ForgeBiomeModifiers
import net.minecraftforge.registries.ForgeRegistries
import top.srcres258.tutorialmod.TutorialMod

object ModBiomeModifiers {
    val ADD_SAPPHIRE_ORE: ResourceKey<BiomeModifier> = registerKey("add_sapphire_ore")
    val ADD_NETHER_SAPPHIRE_ORE: ResourceKey<BiomeModifier> = registerKey("add_nether_sapphire_ore")
    val ADD_END_SAPPHIRE_ORE: ResourceKey<BiomeModifier> = registerKey("add_end_sapphire_ore")
    val ADD_TREE_PINE: ResourceKey<BiomeModifier> = registerKey("add_tree_pine")

    fun bootstrap(context: BootstapContext<BiomeModifier>) {
        val pf = context.lookup(Registries.PLACED_FEATURE)
        val biomes = context.lookup(Registries.BIOME)

        context.run {
            register(
                ADD_SAPPHIRE_ORE,
                ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                    biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                    HolderSet.direct(pf.getOrThrow(ModPlacedFeatures.SAPPHIRE_ORE_PLACED_KEY)),
                    GenerationStep.Decoration.UNDERGROUND_ORES
                )
            )
            register(
                ADD_NETHER_SAPPHIRE_ORE,
                ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                    biomes.getOrThrow(BiomeTags.IS_NETHER),
                    HolderSet.direct(pf.getOrThrow(ModPlacedFeatures.NETHER_SAPPHIRE_ORE_PLACED_KEY)),
                    GenerationStep.Decoration.UNDERGROUND_ORES
                )
            )
            register(
                ADD_END_SAPPHIRE_ORE,
                ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                    biomes.getOrThrow(BiomeTags.IS_END),
                    HolderSet.direct(pf.getOrThrow(ModPlacedFeatures.END_SAPPHIRE_ORE_PLACED_KEY)),
                    GenerationStep.Decoration.UNDERGROUND_ORES
                )
            )
            register(
                ADD_TREE_PINE,
                ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                    biomes.getOrThrow(Tags.Biomes.IS_PLAINS),
                    HolderSet.direct(pf.getOrThrow(ModPlacedFeatures.PINE_PLACED_KEY)),
                    GenerationStep.Decoration.VEGETAL_DECORATION
                )
            )
        }
    }

    private fun registerKey(name: String) =
        ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS,
            ResourceLocation(TutorialMod.MOD_ID, name)
        )
}