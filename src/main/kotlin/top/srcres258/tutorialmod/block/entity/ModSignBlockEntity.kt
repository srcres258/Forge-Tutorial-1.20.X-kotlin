package top.srcres258.tutorialmod.block.entity

import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.entity.SignBlockEntity
import net.minecraft.world.level.block.state.BlockState

class ModSignBlockEntity(
    pPos: BlockPos,
    pBlockState: BlockState
) : SignBlockEntity(ModBlockEntities.MOD_SIGN.get(), pPos, pBlockState) {
    override fun getType(): BlockEntityType<*> = ModBlockEntities.MOD_SIGN.get()
}