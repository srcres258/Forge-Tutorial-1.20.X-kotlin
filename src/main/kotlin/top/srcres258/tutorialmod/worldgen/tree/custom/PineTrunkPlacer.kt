package top.srcres258.tutorialmod.worldgen.tree.custom

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelSimulatedReader
import net.minecraft.world.level.block.RotatedPillarBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer
import top.srcres258.tutorialmod.worldgen.tree.ModTrunkPlacerTypes
import java.util.function.BiConsumer

class PineTrunkPlacer(
    pBaseHeight: Int,
    pHeightRandA: Int,
    pHeightRandB: Int
) : TrunkPlacer(pBaseHeight, pHeightRandA, pHeightRandB) {
    companion object {
        val CODEC: Codec<PineTrunkPlacer> = RecordCodecBuilder.create { pineTrunkPlacerInstance ->
            trunkPlacerParts(pineTrunkPlacerInstance)
                .apply(pineTrunkPlacerInstance, ::PineTrunkPlacer)
        }
    }

    override fun type() = ModTrunkPlacerTypes.PINE_TRUNK_PLACER.get()

    override fun placeTrunk(
        level: LevelSimulatedReader,
        blockSetter: BiConsumer<BlockPos, BlockState>,
        random: RandomSource,
        freeTreeHeight: Int,
        pos: BlockPos,
        config: TreeConfiguration
    ): List<FoliagePlacer.FoliageAttachment> {
        // THIS WHERE BLOCK PLACING LOGIC GOES
        setDirtAt(level, blockSetter, random, pos.below(), config)
        val height = freeTreeHeight + random.nextInt(heightRandA, heightRandA + 3) +
                random.nextInt(heightRandB - 1, heightRandB + 1)

        for (i in 0 ..< height) {
            placeLog(level, blockSetter, random, pos.above(i), config)

            if (i % 2 == 0 && random.nextBoolean()) {
                val axesAndDirs = listOf(
                    Pair(Direction.Axis.Z, Direction.NORTH),
                    Pair(Direction.Axis.Z, Direction.SOUTH),
                    Pair(Direction.Axis.X, Direction.EAST),
                    Pair(Direction.Axis.X, Direction.WEST)
                )

                for ((axis, dir) in axesAndDirs) {
                    if (random.nextFloat() > 0.25F) {
                        for (x in 0 ..< 4) {
                            blockSetter.accept(
                                pos.above(i).relative(dir, x),
                                config.trunkProvider
                                    .getState(random, pos)
                                    .setValue(RotatedPillarBlock.AXIS, axis)
                            )
                        }
                    }
                }
            }
        }

        return listOf(FoliagePlacer.FoliageAttachment(pos.above(height), 0, false))
    }
}