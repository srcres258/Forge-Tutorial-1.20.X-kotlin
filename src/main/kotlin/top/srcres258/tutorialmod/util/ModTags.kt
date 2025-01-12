package top.srcres258.tutorialmod.util

import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ItemTags
import top.srcres258.tutorialmod.TutorialMod

object ModTags {
    object Blocks {
        val METAL_DETECTOR_VALUABLES = tag("metal_detector_valuables")

        private fun tag(name: String) = BlockTags.create(ResourceLocation(TutorialMod.MOD_ID, name))
    }

    object Items {
        private fun tag(name: String) = ItemTags.create(ResourceLocation(TutorialMod.MOD_ID, name))
    }
}