package top.srcres258.tutorialmod.worldgen.tree.custom

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.util.RandomSource
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.level.LevelSimulatedReader
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer
import top.srcres258.tutorialmod.worldgen.tree.ModFoliagePlacers

class PineFoliagePlacer(
    pRadius: IntProvider,
    pOffset: IntProvider,
    private val height: Int
) : FoliagePlacer(pRadius, pOffset) {
    companion object {
        val CODEC: Codec<PineFoliagePlacer> =
            RecordCodecBuilder.create { pineFoliagePlacerInstance ->
                foliagePlacerParts(pineFoliagePlacerInstance)
                    .and(
                        Codec.intRange(0, 16)
                            .fieldOf("height")
                            .forGetter { fp ->
                                fp.height
                            }
                    )
                    .apply(pineFoliagePlacerInstance, ::PineFoliagePlacer)
            }
    }

    override fun type() = ModFoliagePlacers.PINE_FOLIAGE_PLACER.get()

    override fun createFoliage(
        level: LevelSimulatedReader,
        blockSetter: FoliageSetter,
        random: RandomSource,
        config: TreeConfiguration,
        maxFreeTreeHeight: Int,
        attachment: FoliageAttachment,
        foliageHeight: Int,
        foliageRadius: Int,
        offset: Int
    ) {
        attachment.pos().let { pos ->
            for (i in 0 ..< height) {
                placeLeavesRow(level, blockSetter, random, config, pos.above(i), 2, 2,
                    attachment.doubleTrunk())
            }
        }
    }

    override fun foliageHeight(p0: RandomSource, p1: Int, p2: TreeConfiguration) = height

    override fun shouldSkipLocation(p0: RandomSource, p1: Int, p2: Int, p3: Int, p4: Int, p5: Boolean) = false
}