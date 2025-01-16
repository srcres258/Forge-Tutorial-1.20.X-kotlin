package top.srcres258.tutorialmod.entity.custom

import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.ThrowableItemProjectile
import net.minecraft.world.level.Level
import net.minecraft.world.phys.BlockHitResult
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.block.custom.DiceBlock
import top.srcres258.tutorialmod.entity.ModEntities
import top.srcres258.tutorialmod.item.ModItems

class DiceProjectileEntity : ThrowableItemProjectile {
    constructor(
        pEntityType: EntityType<out ThrowableItemProjectile>,
        pLevel: Level
    ) : super(pEntityType, pLevel)

    constructor(level: Level) : super(ModEntities.DICE_PROJECTILE.get(), level)

    constructor(level: Level, livingEntity: LivingEntity) : super(ModEntities.DICE_PROJECTILE.get(), livingEntity, level)

    override fun getDefaultItem() = ModItems.DICE.get()

    override fun onHitBlock(pResult: BlockHitResult) {
        if (!level().isClientSide) {
            level().broadcastEntityEvent(this, 3)
            level().setBlock(
                blockPosition(),
                (ModBlocks.DICE_BLOCK.get() as DiceBlock).randomBlockState,
                3
            )
        }

        discard()
        super.onHitBlock(pResult)
    }
}