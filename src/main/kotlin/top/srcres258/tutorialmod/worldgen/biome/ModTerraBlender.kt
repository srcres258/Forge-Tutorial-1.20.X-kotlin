package top.srcres258.tutorialmod.worldgen.biome

import net.minecraft.resources.ResourceLocation
import terrablender.api.Regions
import top.srcres258.tutorialmod.TutorialMod

object ModTerraBlender {
    fun registerBiomes() {
        Regions.register(ModOverworldRegion(ResourceLocation(TutorialMod.MOD_ID, "overworld"), 5))
    }
}