package top.srcres258.tutorialmod.util

import net.minecraft.world.level.block.state.properties.BlockSetType
import net.minecraft.world.level.block.state.properties.WoodType
import top.srcres258.tutorialmod.TutorialMod

object ModWoodTypes {
    val PINE: WoodType = WoodType.register(WoodType("${TutorialMod.MOD_ID}:pine", BlockSetType.OAK))
}