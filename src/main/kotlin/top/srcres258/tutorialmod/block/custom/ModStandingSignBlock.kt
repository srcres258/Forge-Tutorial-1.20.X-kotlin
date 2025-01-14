package top.srcres258.tutorialmod.block.custom

import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.StandingSignBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.WoodType
import top.srcres258.tutorialmod.block.entity.ModSignBlockEntity

class ModStandingSignBlock(
    pProperties: Properties,
    pType: WoodType
) : StandingSignBlock(pProperties, pType) {
    override fun newBlockEntity(pPos: BlockPos, pState: BlockState): BlockEntity =
        ModSignBlockEntity(pPos, pState)
}