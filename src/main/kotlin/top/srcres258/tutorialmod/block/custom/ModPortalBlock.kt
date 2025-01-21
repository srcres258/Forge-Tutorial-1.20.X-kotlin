package top.srcres258.tutorialmod.block.custom

import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import top.srcres258.tutorialmod.worldgen.dimension.ModDimensions
import top.srcres258.tutorialmod.worldgen.portal.ModTeleporter

class ModPortalBlock(pProperties: Properties) : Block(pProperties) {
    @Deprecated("Deprecated in Java")
    override fun use(
        pState: BlockState,
        pLevel: Level,
        pPos: BlockPos,
        pPlayer: Player,
        pHand: InteractionHand,
        pHit: BlockHitResult
    ): InteractionResult {
        if (pPlayer.canChangeDimensions()) {
            handleKaupenPortal(pPlayer, pPos)
            return InteractionResult.SUCCESS
        } else {
            return InteractionResult.CONSUME
        }
    }

    private fun handleKaupenPortal(player: Entity, pos: BlockPos) {
        val level = player.level()
        if (level is ServerLevel) {
            val server = level.server
            val resKey = if (level.dimension() == ModDimensions.KAUPENDIM_LEVEL_KEY) {
                Level.OVERWORLD
            } else {
                ModDimensions.KAUPENDIM_LEVEL_KEY
            }
            val portalDimension = server.getLevel(resKey)
            if (portalDimension != null && !player.isPassenger) {
                player.changeDimension(portalDimension, ModTeleporter(pos, resKey == ModDimensions.KAUPENDIM_LEVEL_KEY))
            }
        }
    }
}