package top.srcres258.tutorialmod.entity.custom

import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.AnimationState
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.BreedGoal
import net.minecraft.world.entity.ai.goal.FloatGoal
import net.minecraft.world.entity.ai.goal.FollowParentGoal
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
import net.minecraft.world.entity.ai.goal.TemptGoal
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level
import top.srcres258.tutorialmod.entity.ModEntities
import kotlin.math.min

class RhinoEntity(pEntityType: EntityType<out Animal>, pLevel: Level) : Animal(pEntityType, pLevel) {
    companion object {
        fun createAttributes(): AttributeSupplier.Builder = createLivingAttributes()
            .add(Attributes.MAX_HEALTH, 20.0)
            .add(Attributes.FOLLOW_RANGE, 24.0)
            .add(Attributes.MOVEMENT_SPEED, 0.25)
            .add(Attributes.ARMOR_TOUGHNESS, 0.1)
            .add(Attributes.ATTACK_KNOCKBACK, 0.5)
            .add(Attributes.ATTACK_DAMAGE, 2.0)
    }

    val idleAnimationState = AnimationState()
    var idleAnimationTimeout = 0

    override fun tick() {
        super.tick()

        if (level().isClientSide) {
            setupAnimationStates()
        }
    }

    private fun setupAnimationStates() {
        if (idleAnimationTimeout <= 0) {
            idleAnimationTimeout = random.nextInt(40) + 80
            idleAnimationState.start(tickCount)
        } else {
            idleAnimationTimeout--
        }
    }

    override fun updateWalkAnimation(pPartialTick: Float) {
        val f = if (pose == Pose.STANDING) min(pPartialTick * 6F, 1F) else 0F

        walkAnimation.update(f, 0.2F)
    }

    override fun registerGoals() {
        goalSelector.run {
            addGoal(0, FloatGoal(this@RhinoEntity))

            addGoal(1, BreedGoal(this@RhinoEntity, 1.15))
            addGoal(2, TemptGoal(this@RhinoEntity, 1.2, Ingredient.of(Items.COOKED_BEEF), false))

            addGoal(3, FollowParentGoal(this@RhinoEntity, 1.1))

            addGoal(4, WaterAvoidingRandomStrollGoal(this@RhinoEntity, 1.1))
            addGoal(5, LookAtPlayerGoal(this@RhinoEntity, Player::class.java, 3F))
            addGoal(6, RandomLookAroundGoal(this@RhinoEntity))
        }
    }

    override fun getBreedOffspring(level: ServerLevel, p1: AgeableMob) =
        ModEntities.RHINO.get().create(level)

    override fun isFood(pStack: ItemStack) = pStack.`is`(Items.COOKED_BEEF)

    override fun getAmbientSound(): SoundEvent? = SoundEvents.HOGLIN_AMBIENT

    override fun getHurtSound(pDamageSource: DamageSource): SoundEvent? = SoundEvents.RAVAGER_HURT

    override fun getDeathSound(): SoundEvent? = SoundEvents.DOLPHIN_DEATH
}