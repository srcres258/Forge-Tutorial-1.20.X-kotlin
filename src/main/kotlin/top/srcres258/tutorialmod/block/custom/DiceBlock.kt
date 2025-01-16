package top.srcres258.tutorialmod.block.custom

import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.DirectionProperty

class DiceBlock(pProperties: Properties) : Block(pProperties) {
    companion object {
        val FACING: DirectionProperty = DirectionProperty.create(
            "number",
            Direction.UP,
            Direction.NORTH,
            Direction.EAST,
            Direction.SOUTH,
            Direction.WEST,
            Direction.DOWN
        )
    }

    override fun getStateForPlacement(pContext: BlockPlaceContext) = randomBlockState

    val randomBlockState: BlockState
        get() = defaultBlockState().setValue(FACING, randomDirection())

    override fun createBlockStateDefinition(pBuilder: StateDefinition.Builder<Block, BlockState>) {
        pBuilder.add(FACING)
    }
}

private fun randomDirection() =
    arrayOf(
        Direction.UP,
        Direction.NORTH,
        Direction.EAST,
        Direction.SOUTH,
        Direction.WEST,
        Direction.DOWN
    ).let { dirs ->
        dirs[RandomSource.create()
            .nextIntBetweenInclusive(0, dirs.size - 1)]
    }