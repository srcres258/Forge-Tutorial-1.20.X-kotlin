package top.srcres258.tutorialmod.worldgen

import net.minecraft.world.level.levelgen.placement.BiomeFilter
import net.minecraft.world.level.levelgen.placement.CountPlacement
import net.minecraft.world.level.levelgen.placement.InSquarePlacement
import net.minecraft.world.level.levelgen.placement.PlacementModifier
import net.minecraft.world.level.levelgen.placement.RarityFilter

object ModOrePlacement {
    fun orePlacement(
        modifier1: PlacementModifier,
        modifier2: PlacementModifier
    ) = listOf(
        modifier1,
        InSquarePlacement.spread(),
        modifier2,
        BiomeFilter.biome()
    )

    fun commonOrePlacement(
        count: Int,
        heightRange: PlacementModifier
    ) = orePlacement(CountPlacement.of(count), heightRange)

    fun rareOrePlacement(
        chance: Int,
        heightRange: PlacementModifier
    ) = orePlacement(RarityFilter.onAverageOnceEvery(chance), heightRange)
}