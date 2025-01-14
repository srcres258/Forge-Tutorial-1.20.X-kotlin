package top.srcres258.tutorialmod.entity.ai

import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal
import top.srcres258.tutorialmod.entity.custom.RhinoEntity
import kotlin.math.max

class RhinoAttackGoal(
    private val entity: RhinoEntity,
    pSpeedModifier: Double,
    pFollowingTargetEvenIfNotSeen: Boolean
) : MeleeAttackGoal(entity, pSpeedModifier, pFollowingTargetEvenIfNotSeen) {
    private var attackDelay = 40
    private var ticksUntilNextAttack = 40
    private var shouldCountTillNextAttack = false

    override fun start() {
        super.start()
        attackDelay = 40
        ticksUntilNextAttack = 40
    }

    override fun checkAndPerformAttack(pEnemy: LivingEntity, pDistToEnemySqr: Double) {
        if (isEnemyWithinAttackDistance(pEnemy, pDistToEnemySqr)) {
            shouldCountTillNextAttack = true

            if (isTimeToStartAttackAnimation) {
                entity.isAttacking = true
            }

            if (isTimeToAttack) {
                mob.lookControl.setLookAt(pEnemy.x, pEnemy.eyeY, pEnemy.z)
                performAttack(pEnemy)
            }
        } else {
            resetAttackCooldown()
            shouldCountTillNextAttack = false
            entity.isAttacking = false
            entity.attackAnimationTimeout = 0
        }
    }

    private fun isEnemyWithinAttackDistance(enemy: LivingEntity, distToEnemySqr: Double) =
        distToEnemySqr <= getAttackReachSqr(enemy)

    override fun resetAttackCooldown() {
        ticksUntilNextAttack = adjustedTickDelay(attackDelay * 2)
    }

    override fun isTimeToAttack() = ticksUntilNextAttack <= 0

    private val isTimeToStartAttackAnimation
        get() = ticksUntilNextAttack <= attackDelay

    override fun getTicksUntilNextAttack() = ticksUntilNextAttack

    private fun performAttack(enemy: LivingEntity) {
        resetAttackCooldown()
        mob.swing(InteractionHand.MAIN_HAND)
        mob.doHurtTarget(enemy)
    }

    override fun tick() {
        super.tick()
        if (shouldCountTillNextAttack) {
            ticksUntilNextAttack = max(ticksUntilNextAttack - 1, 0)
        }
    }

    override fun stop() {
        entity.isAttacking = false
        super.stop()
    }
}