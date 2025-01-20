package top.srcres258.tutorialmod.worldgen

import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraft.world.level.levelgen.placement.PlacementModifier
import top.srcres258.tutorialmod.TutorialMod

object ModPlacedFeatures {
    val SAPPHIRE_ORE_PLACED_KEY: ResourceKey<PlacedFeature> =
        registerKey("sapphire_ore_placed")
    val NETHER_SAPPHIRE_ORE_PLACED_KEY: ResourceKey<PlacedFeature> =
        registerKey("nether_sapphire_ore_placed")
    val END_SAPPHIRE_ORE_PLACED_KEY: ResourceKey<PlacedFeature> =
        registerKey("end_sapphire_ore_placed")

    fun bootstrap(context: BootstapContext<PlacedFeature>) {
        val configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE)

        register(
            context,
            SAPPHIRE_ORE_PLACED_KEY,
            configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_SAPPHIRE_ORE_KEY),
            ModOrePlacement.commonOrePlacement(
                12,
                HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(-64),
                    VerticalAnchor.absolute(80)
                )
            )
        )
        register(
            context,
            NETHER_SAPPHIRE_ORE_PLACED_KEY,
            configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_SAPPHIRE_ORE_KEY),
            ModOrePlacement.commonOrePlacement(
                12,
                HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(-64),
                    VerticalAnchor.absolute(80)
                )
            )
        )
        register(
            context,
            END_SAPPHIRE_ORE_PLACED_KEY,
            configuredFeatures.getOrThrow(ModConfiguredFeatures.END_SAPPHIRE_ORE_KEY),
            ModOrePlacement.commonOrePlacement(
                12,
                HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(-64),
                    VerticalAnchor.absolute(80)
                )
            )
        )
    }

    private fun registerKey(name: String) =
        ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation(TutorialMod.MOD_ID, name))

    private fun register(
        context: BootstapContext<PlacedFeature>,
        key: ResourceKey<PlacedFeature>,
        configuration: Holder<ConfiguredFeature<*, *>>,
        modifers: List<PlacementModifier>
    ) {
        context.register(key, PlacedFeature(configuration, modifers.toList()))
    }
}