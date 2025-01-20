package top.srcres258.tutorialmod.worldgen.tree

import net.minecraft.util.RandomSource
import net.minecraft.world.level.block.grower.AbstractTreeGrower
import top.srcres258.tutorialmod.worldgen.ModConfiguredFeatures

class PineTreeGrower : AbstractTreeGrower() {
    override fun getConfiguredFeature(p0: RandomSource, p1: Boolean) = ModConfiguredFeatures.PINE_KEY
}