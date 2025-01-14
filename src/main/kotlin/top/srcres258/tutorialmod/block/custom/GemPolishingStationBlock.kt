package top.srcres258.tutorialmod.block.custom

import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraftforge.network.NetworkHooks
import top.srcres258.tutorialmod.block.entity.GemPolishingStationBlockEntity
import top.srcres258.tutorialmod.block.entity.ModBlockEntities

class GemPolishingStationBlock(pProperties: Properties) : BaseEntityBlock(pProperties) {
    companion object {
        val SHAPE: VoxelShape = box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0)
    }

    @Deprecated("Deprecated in Java")
    override fun getShape(
        pState: BlockState,
        pLevel: BlockGetter,
        pPos: BlockPos,
        pContext: CollisionContext
    ) = SHAPE

    @Deprecated("Deprecated in Java")
    override fun getRenderShape(pState: BlockState) = RenderShape.MODEL

    @Deprecated("Deprecated in Java")
    override fun onRemove(
        pState: BlockState,
        pLevel: Level,
        pPos: BlockPos,
        pNewState: BlockState,
        pMovedByPiston: Boolean
    ) {
        if (pState.block != pNewState.block) {
            val blockEntity = pLevel.getBlockEntity(pPos)
            if (blockEntity is GemPolishingStationBlockEntity) {
                blockEntity.drops()
            }
        }

        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston)
    }

    @Deprecated("Deprecated in Java")
    override fun use(
        pState: BlockState,
        pLevel: Level,
        pPos: BlockPos,
        pPlayer: Player,
        pHand: InteractionHand,
        pHit: BlockHitResult
    ): InteractionResult {
        if (!pLevel.isClientSide) {
            val entity = pLevel.getBlockEntity(pPos)
            if (entity is GemPolishingStationBlockEntity) {
                // NOTE: **not work** for Minecraft 1.20.2 and above.
                NetworkHooks.openScreen(pPlayer as ServerPlayer, entity, pPos)
            } else {
                throw IllegalStateException("Our container provider is missing!")
            }
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide)
    }

    override fun newBlockEntity(pos: BlockPos, state: BlockState) =
        GemPolishingStationBlockEntity(pos, state)

    override fun <T : BlockEntity?> getTicker(
        pLevel: Level,
        pState: BlockState,
        pBlockEntityType: BlockEntityType<T>
    ) = if (pLevel.isClientSide) {
        null
    } else {
        createTickerHelper(pBlockEntityType, ModBlockEntities.GEM_POLISHING_BE.get())
        { level, pos, state, blockEntity -> blockEntity.tick(level, pos, state) }
    }
}