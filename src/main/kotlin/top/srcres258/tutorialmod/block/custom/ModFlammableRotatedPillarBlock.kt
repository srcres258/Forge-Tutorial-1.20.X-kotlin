package top.srcres258.tutorialmod.block.custom

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.AxeItem
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.RotatedPillarBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.common.ToolAction
import net.minecraftforge.registries.RegistryObject
import top.srcres258.tutorialmod.block.ModBlocks

class ModFlammableRotatedPillarBlock(pProperties: Properties) : RotatedPillarBlock(pProperties) {
    override fun isFlammable(
        state: BlockState?,
        level: BlockGetter?,
        pos: BlockPos?,
        direction: Direction?
    ) = true

    override fun getFlammability(
        state: BlockState?,
        level: BlockGetter?,
        pos: BlockPos?,
        direction: Direction?
    ) = 5

    override fun getFireSpreadSpeed(
        state: BlockState?,
        level: BlockGetter?,
        pos: BlockPos?,
        direction: Direction?
    ) = 5

    override fun getToolModifiedState(
        state: BlockState,
        context: UseOnContext,
        toolAction: ToolAction,
        simulate: Boolean
    ): BlockState? {
        if (context.itemInHand.item is AxeItem) {
            val judge: (RegistryObject<Block>) -> Boolean = { state.`is`(it.get()) }

            when {
                judge(ModBlocks.PINE_LOG) -> {
                    return ModBlocks.STRIPPED_PINE_LOG.get()
                        .defaultBlockState()
                        .setValue(AXIS, state.getValue(AXIS))
                }

                judge(ModBlocks.PINE_WOOD) -> {
                    return ModBlocks.STRIPPED_PINE_WOOD.get()
                        .defaultBlockState()
                        .setValue(AXIS, state.getValue(AXIS))
                }
            }
        }

        return super.getToolModifiedState(state, context, toolAction, simulate)
    }
}