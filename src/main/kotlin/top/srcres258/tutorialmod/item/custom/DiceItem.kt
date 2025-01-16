package top.srcres258.tutorialmod.item.custom

import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import top.srcres258.tutorialmod.entity.custom.DiceProjectileEntity

class DiceItem(pProperties: Properties) : Item(pProperties) {
    override fun use(pLevel: Level, pPlayer: Player, pUsedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        val itemStack = pPlayer.getItemInHand(pUsedHand)
        pLevel.playSound(
            null,
            pPlayer.x,
            pPlayer.y,
            pPlayer.z,
            SoundEvents.SNOWBALL_THROW,
            SoundSource.NEUTRAL,
            0.5F,
            0.4F / (pLevel.random.nextFloat() * 0.4F + 0.8F)
        )

        if (!pLevel.isClientSide) {
            val dice = DiceProjectileEntity(pLevel, pPlayer)
            dice.item = itemStack
            dice.shootFromRotation(pPlayer, pPlayer.xRot, pPlayer.yRot, 0F, 1.5F, 1F)
            pLevel.addFreshEntity(dice)
        }

        pPlayer.awardStat(Stats.ITEM_USED.get(this))
        if (!pPlayer.abilities.instabuild) {
            itemStack.shrink(1)
        }

        return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide)
    }
}