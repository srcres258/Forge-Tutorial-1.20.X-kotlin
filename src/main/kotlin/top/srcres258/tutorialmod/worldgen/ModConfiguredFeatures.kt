package top.srcres258.tutorialmod.worldgen

import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.worldgen.tree.custom.PineFoliagePlacer
import top.srcres258.tutorialmod.worldgen.tree.custom.PineTrunkPlacer

object ModConfiguredFeatures {
    val OVERWORLD_SAPPHIRE_ORE_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("sapphire_ore")
    val NETHER_SAPPHIRE_ORE_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("nether_sapphire_ore")
    val END_SAPPHIRE_ORE_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("end_sapphire_ore")

    val PINE_KEY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("pine")

    fun bootstrap(context: BootstapContext<ConfiguredFeature<*, *>>) {
        val stoneReplaceables = TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES)
        val deepslateReplaceables = TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES)
        val netherrackReplaceables = BlockMatchTest(Blocks.NETHERRACK)
        val endReplaceables = BlockMatchTest(Blocks.END_STONE)

        val overworldSapphireOres = listOf(
            OreConfiguration.target(
                stoneReplaceables,
                ModBlocks.SAPPHIRE_ORE.get().defaultBlockState()
            ),
            OreConfiguration.target(
                deepslateReplaceables,
                ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get().defaultBlockState()
            )
        )

        register(
            context,
            OVERWORLD_SAPPHIRE_ORE_KEY,
            Feature.ORE,
            OreConfiguration(overworldSapphireOres, 9)
        )
        register(
            context,
            NETHER_SAPPHIRE_ORE_KEY,
            Feature.ORE,
            OreConfiguration(
                netherrackReplaceables,
                ModBlocks.NETHER_SAPPHIRE_ORE.get().defaultBlockState(),
                9
            )
        )
        register(
            context,
            END_SAPPHIRE_ORE_KEY,
            Feature.ORE,
            OreConfiguration(
                endReplaceables,
                ModBlocks.END_STONE_SAPPHIRE_ORE.get().defaultBlockState(),
                9
            )
        )

        register(
            context,
            PINE_KEY,
            Feature.TREE,
            TreeConfiguration.TreeConfigurationBuilder(
                // configuration on tree blocks
                BlockStateProvider.simple(ModBlocks.PINE_LOG.get()),
                PineTrunkPlacer(5, 4, 3),

                // configuration on leave blocks
                BlockStateProvider.simple(ModBlocks.PINE_LEAVES.get()),
                PineFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 3),

                // size of the feature
                TwoLayersFeatureSize(1, 0, 2)
            ).build()
        )
    }

    fun registerKey(name: String): ResourceKey<ConfiguredFeature<*, *>> =
        ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation(TutorialMod.MOD_ID, name))

    private fun <FC: FeatureConfiguration, F: Feature<FC>> register(
        context: BootstapContext<ConfiguredFeature<*, *>>,
        key: ResourceKey<ConfiguredFeature<*, *>>,
        feature: F,
        configuration: FC
    ) {
        context.register(key, ConfiguredFeature(feature, configuration))
    }
}