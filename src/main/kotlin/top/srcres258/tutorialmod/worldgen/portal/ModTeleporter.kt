package top.srcres258.tutorialmod.worldgen.portal

import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.material.Fluids
import net.minecraftforge.common.util.ITeleporter
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.block.custom.ModPortalBlock
import java.util.function.Function

class ModTeleporter : ITeleporter {
    companion object {
        var thisPos = BlockPos.ZERO
        var insideDimension = true
    }

    constructor(pos: BlockPos, insideDim: Boolean) {
        thisPos = pos
        insideDimension = insideDim
    }

    override fun placeEntity(
        pEntity: Entity,
        currentWorld: ServerLevel,
        destWorld: ServerLevel,
        yaw: Float,
        repositionEntity: Function<Boolean, Entity>
    ): Entity {
        val entity = repositionEntity.apply(false)
        var y = 61

        if (!insideDimension) {
            y = thisPos.y
        }
        var destPos = BlockPos(thisPos.x, y, thisPos.z)

        val getBS = { pos: BlockPos -> destWorld.getBlockState(destPos) }

        var tries = 0
        while (getBS(destPos).block != Blocks.AIR
            && getBS(destPos).canBeReplaced(Fluids.WATER)
            && getBS(destPos.above()).block != Blocks.AIR
            && !getBS(destPos.above()).canBeReplaced(Fluids.WATER)
            && tries < 25
        ) {
            destPos = destPos.above(2)
            tries++
        }

        entity.setPos(destPos.x.toDouble(), destPos.y.toDouble(), destPos.z.toDouble())
        if (insideDimension) {
            var doSetBlock = true
            for (checkPos in BlockPos.betweenClosed(
                destPos.below(10).west(10),
                destPos.above(10).east(10)
            )) {
                if (getBS(checkPos).block is ModPortalBlock) {
                    doSetBlock = false
                    break
                }
            }
            if (doSetBlock) {
                destWorld.setBlock(destPos, ModBlocks.MOD_PORTAL.get().defaultBlockState(), 3)
            }
        }

        return entity
    }
}