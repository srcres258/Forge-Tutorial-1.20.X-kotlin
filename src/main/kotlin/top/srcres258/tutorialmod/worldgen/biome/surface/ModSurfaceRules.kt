package top.srcres258.tutorialmod.worldgen.biome.surface

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.SurfaceRules
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.worldgen.biome.ModBiomes

object ModSurfaceRules {
    private val DIRT = makeStateRule(Blocks.DIRT)
    private val GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK)
    private val SAPPHIRE = makeStateRule(ModBlocks.SAPPHIRE_BLOCK.get())
    private val RAW_SAPPHIRE = makeStateRule(ModBlocks.RAW_SAPPHIRE_BLOCK.get())

    fun makeRules(): SurfaceRules.RuleSource {
        val isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0)
        val grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT)

        return SurfaceRules.sequence(
            SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                    SurfaceRules.isBiome(ModBiomes.TEST_BIOME),
                    SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, RAW_SAPPHIRE)
                ),
                SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, SAPPHIRE)
            ),
            // Default to a grass and dirt surface
            SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)
        )
    }

    private fun makeStateRule(block: Block): SurfaceRules.RuleSource =
        SurfaceRules.state(block.defaultBlockState())
}