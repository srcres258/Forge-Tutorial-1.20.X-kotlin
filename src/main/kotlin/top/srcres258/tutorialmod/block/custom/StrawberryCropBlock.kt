package top.srcres258.tutorialmod.block.custom

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.CropBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.IntegerProperty
import top.srcres258.tutorialmod.item.ModItems

class StrawberryCropBlock(pProperties: Properties) : CropBlock(pProperties) {
    companion object {
        val MAX_AGE = 5
        val AGE: IntegerProperty = BlockStateProperties.AGE_5
    }

    override fun getBaseSeedId() = ModItems.STRAWBERRY_SEEDS.get()

    public override fun getAgeProperty() = AGE

    override fun getMaxAge() = MAX_AGE

    override fun createBlockStateDefinition(pBuilder: StateDefinition.Builder<Block, BlockState>) {
        pBuilder.add(AGE)
    }
}