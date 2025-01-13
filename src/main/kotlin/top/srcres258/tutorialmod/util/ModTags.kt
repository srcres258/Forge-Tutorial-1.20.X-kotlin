package top.srcres258.tutorialmod.util

import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block
import top.srcres258.tutorialmod.TutorialMod

object ModTags {
    object Blocks {
        val METAL_DETECTOR_VALUABLES: TagKey<Block> = tag("metal_detector_valuables")
        val NEEDS_SAPPHIRE_TOOL: TagKey<Block> = tag("needs_sapphire_tool")

        private fun tag(name: String) = BlockTags.create(ResourceLocation(TutorialMod.MOD_ID, name))
    }

    object Items {
        private fun tag(name: String) = ItemTags.create(ResourceLocation(TutorialMod.MOD_ID, name))
    }
}