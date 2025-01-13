package top.srcres258.tutorialmod.block.custom

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.CropBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraftforge.common.ForgeHooks
import net.minecraftforge.common.IPlantable
import top.srcres258.tutorialmod.item.ModItems

class CornCropBlock(pProperties: Properties) : CropBlock(pProperties) {
    companion object {
        const val FIRST_STAGE_MAX_AGE = 7
        const val SECOND_STAGE_MAX_AGE = 1

        private val SHAPE_BY_AGE = arrayOf<VoxelShape>(
            Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
        )

        val AGE: IntegerProperty = IntegerProperty.create("age", 0,
            FIRST_STAGE_MAX_AGE + SECOND_STAGE_MAX_AGE)
    }

    override fun getShape(
        pState: BlockState,
        pLevel: BlockGetter,
        pPos: BlockPos,
        pContext: CollisionContext
    ) = SHAPE_BY_AGE[getAge(pState)]

    @Deprecated("Deprecated in Java")
    override fun randomTick(pState: BlockState, pLevel: ServerLevel, pPos: BlockPos, pRandom: RandomSource) {
        if (!pLevel.isAreaLoaded(pPos, 1)) {
            return
        }
        if (pLevel.getRawBrightness(pPos, 0) >= 9) {
            val currentAge = getAge(pState)

            if (currentAge < maxAge) {
                val growthSpeed = getGrowthSpeed(this, pLevel, pPos)

                if (ForgeHooks.onCropsGrowPre(pLevel, pPos, pState,
                        pRandom.nextInt((25F / growthSpeed).toInt() + 1) == 0)) {
                    if (currentAge == FIRST_STAGE_MAX_AGE) {
                        if (pLevel.getBlockState(pPos.above()).`is`(Blocks.AIR)) {
                            pLevel.setBlock(pPos.above(), getStateForAge(currentAge + 1), 2)
                        }
                    } else {
                        pLevel.setBlock(pPos, getStateForAge(currentAge + 1), 2)
                    }

                    ForgeHooks.onCropsGrowPost(pLevel, pPos, pState)
                }
            }
        }
    }

    override fun canSustainPlant(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        facing: Direction,
        plantable: IPlantable
    ) = mayPlaceOn(state, world, pos)

    override fun canSurvive(pState: BlockState, pLevel: LevelReader, pPos: BlockPos) =
        super.canSurvive(pState, pLevel, pPos) || (pLevel.getBlockState(pPos.below()).`is`(this)
                && pLevel.getBlockState(pPos.below()).getValue(AGE) == FIRST_STAGE_MAX_AGE)

    override fun growCrops(pLevel: Level, pPos: BlockPos, pState: BlockState) {
        var nextAge = getAge(pState) + getBonemealAgeIncrease(pLevel)
        if (nextAge > maxAge) {
            nextAge = maxAge
        }

        if (getAge(pState) == FIRST_STAGE_MAX_AGE && pLevel.getBlockState(pPos.above()).`is`(Blocks.AIR)) {
            pLevel.setBlock(pPos.above(), getStateForAge(nextAge), 2)
        } else {
            pLevel.setBlock(pPos, getStateForAge(nextAge - SECOND_STAGE_MAX_AGE), 2)
        }
    }

    override fun getMaxAge() = FIRST_STAGE_MAX_AGE + SECOND_STAGE_MAX_AGE

    override fun getBaseSeedId() = ModItems.CORN_SEEDS.get()

    public override fun getAgeProperty() = AGE

    override fun createBlockStateDefinition(pBuilder: StateDefinition.Builder<Block, BlockState>) {
        pBuilder.add(AGE)
    }
}