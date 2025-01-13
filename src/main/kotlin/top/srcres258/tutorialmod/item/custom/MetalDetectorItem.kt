package top.srcres258.tutorialmod.item.custom

import net.minecraft.client.resources.language.I18n
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import top.srcres258.tutorialmod.sound.ModSounds
import top.srcres258.tutorialmod.util.ModTags

class MetalDetectorItem(pProperties: Properties) : Item(pProperties) {
    override fun useOn(pContext: UseOnContext): InteractionResult {
        val contextPlayer = pContext.player!!

        if (!pContext.level.isClientSide) {
            val positionClicked = pContext.clickedPos
            var foundBlock = false

            for (i in 0 .. positionClicked.y + 64) {
                val state = pContext.level.getBlockState(positionClicked.below(i))

                if (isValuableBlock(state)) {
                    outputValuableCoordinates(positionClicked.below(i), contextPlayer, state.block)
                    foundBlock = true

                    pContext.level.playSeededSound(null,
                        positionClicked.x.toDouble(), positionClicked.y.toDouble(), positionClicked.z.toDouble(),
                        ModSounds.METAL_DETECTOR_FOUND_ORE.get(), SoundSource.BLOCKS,
                        1F, 1F, 0L)

                    break
                }
            }

            if (!foundBlock) {
                contextPlayer.sendSystemMessage(Component.literal("No valuables found!"))
            }
        }

        pContext.itemInHand.hurtAndBreak(1, contextPlayer) { player ->
            player.broadcastBreakEvent(player.usedItemHand)
        }

        return InteractionResult.SUCCESS
    }

    override fun appendHoverText(
        pStack: ItemStack,
        pLevel: Level?,
        pTooltipComponents: MutableList<Component>,
        pIsAdvanced: TooltipFlag
    ) {
        pTooltipComponents.add(Component.translatable("tooltip.tutorialmod.metal_detector.tooltip"))
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced)
    }
}

private fun outputValuableCoordinates(blockPos: BlockPos, player: Player, block: Block) {
    player.sendSystemMessage(Component.literal("Found ${I18n.get(block.descriptionId)} at " +
            "(${blockPos.x}, ${blockPos.y}, ${blockPos.z})"))
}

private fun isValuableBlock(state: BlockState) =
    state.`is`(ModTags.Blocks.METAL_DETECTOR_VALUABLES)