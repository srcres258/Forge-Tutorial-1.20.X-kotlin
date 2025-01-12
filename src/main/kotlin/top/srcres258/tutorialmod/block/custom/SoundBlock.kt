package top.srcres258.tutorialmod.block.custom

import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult

class SoundBlock(properties: Properties) : Block(properties) {
    @Deprecated("")
    override fun use(
        pState: BlockState,
        pLevel: Level,
        pPos: BlockPos,
        pPlayer: Player,
        pHand: InteractionHand,
        pHit: BlockHitResult
    ): InteractionResult {
        pLevel.playSound(pPlayer, pPos, SoundEvents.NOTE_BLOCK_DIDGERIDOO.get(), SoundSource.BLOCKS, 1F, 1F)
        return InteractionResult.SUCCESS
    }

    override fun appendHoverText(
        pStack: ItemStack,
        pLevel: BlockGetter?,
        pTooltip: MutableList<Component>,
        pFlag: TooltipFlag
    ) {
        pTooltip.add(Component.literal("Makes sweet sounds when right-clicked!"))
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag)
    }
}