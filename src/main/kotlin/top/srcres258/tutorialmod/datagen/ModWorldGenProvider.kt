package top.srcres258.tutorialmod.datagen

import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider
import net.minecraftforge.registries.ForgeRegistries
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.worldgen.ModBiomeModifiers
import top.srcres258.tutorialmod.worldgen.ModConfiguredFeatures
import top.srcres258.tutorialmod.worldgen.ModPlacedFeatures
import java.util.concurrent.CompletableFuture

class ModWorldGenProvider(
    output: PackOutput,
    registries: CompletableFuture<HolderLookup.Provider>
) : DatapackBuiltinEntriesProvider(
    output,
    registries,
    BUILDER,
    setOf(TutorialMod.MOD_ID)
) {
    companion object {
        val BUILDER: RegistrySetBuilder = RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
    }
}