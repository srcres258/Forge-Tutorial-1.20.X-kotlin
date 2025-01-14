package top.srcres258.tutorialmod.block.custom

import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.CeilingHangingSignBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.WoodType
import top.srcres258.tutorialmod.block.entity.ModHangingSignBlockEntity

class ModHangingSignBlock(
    pProperties: Properties,
    pType: WoodType
) : CeilingHangingSignBlock(pProperties, pType) {
    override fun newBlockEntity(pPos: BlockPos, pState: BlockState): BlockEntity =
        ModHangingSignBlockEntity(pPos, pState)
}