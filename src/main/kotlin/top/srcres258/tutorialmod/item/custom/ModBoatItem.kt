package top.srcres258.tutorialmod.item.custom

import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntitySelector
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ClipContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.phys.HitResult
import top.srcres258.tutorialmod.entity.custom.ModBoatEntity
import top.srcres258.tutorialmod.entity.custom.ModChestBoatEntity

private val ENTITY_PREDICATE: (Entity) -> Boolean =
    EntitySelector.NO_SPECTATORS.and(Entity::isPickable)::test

class ModBoatItem(
    private val hasChest: Boolean,
    private val type: ModBoatEntity.Type,
    pProperties: Properties
) : Item(pProperties) {
    override fun use(
        pLevel: Level,
        pPlayer: Player,
        pUsedHand: InteractionHand
    ): InteractionResultHolder<ItemStack> {
        val itemStack = pPlayer.getItemInHand(pUsedHand)
        val hitResult = getPlayerPOVHitResult(pLevel, pPlayer, ClipContext.Fluid.ANY)

        if (hitResult.type == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemStack)
        } else {
            val vec3 = pPlayer.getViewVector(1F)
            val list = pLevel.getEntities(
                pPlayer,
                pPlayer.boundingBox
                    .expandTowards(vec3.scale(5.0))
                    .inflate(1.0),
                ENTITY_PREDICATE
            )

            if (list.isNotEmpty()) {
                val vec31 = pPlayer.eyePosition

                for (entity in list) {
                    val aabb = entity.boundingBox.inflate(entity.pickRadius.toDouble())
                    if (aabb.contains(vec31)) {
                        return InteractionResultHolder.pass(itemStack)
                    }
                }
            }

            if (hitResult.type == HitResult.Type.BLOCK) {
                val boat = getBoat(pLevel, hitResult)
                when (boat) {
                    is ModChestBoatEntity -> {
                        boat.variant = type
                    }
                    is ModBoatEntity -> {
                        boat.variant = type
                    }
                }
                boat.yRot = pPlayer.yRot
                if (!pLevel.noCollision(boat, boat.boundingBox)) {
                    return InteractionResultHolder.fail(itemStack)
                } else {
                    if (!pLevel.isClientSide) {
                        pLevel.addFreshEntity(boat)
                        pLevel.gameEvent(pPlayer, GameEvent.ENTITY_PLACE, hitResult.location)
                        if (!pPlayer.abilities.instabuild) {
                            itemStack.shrink(1)
                        }
                    }

                    pPlayer.awardStat(Stats.ITEM_USED.get(this))
                    return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide)
                }
            } else {
                return InteractionResultHolder.pass(itemStack)
            }
        }
    }

    private fun getBoat(level: Level, hitResult: HitResult) =
        if (hasChest) {
            hitResult.location.run { ModChestBoatEntity(level, x, y, z) }
        } else {
            hitResult.location.run { ModBoatEntity(level, x, y, z) }
        }
}