package top.srcres258.tutorialmod.worldgen.biome

import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BiomeDefaultFeatures
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.data.worldgen.placement.VegetationPlacements
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.Musics
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.AmbientMoodSettings
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.BiomeGenerationSettings
import net.minecraft.world.level.biome.BiomeSpecialEffects
import net.minecraft.world.level.biome.MobSpawnSettings
import net.minecraft.world.level.levelgen.GenerationStep
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.entity.ModEntities
import top.srcres258.tutorialmod.sound.ModSounds
import top.srcres258.tutorialmod.worldgen.ModPlacedFeatures

object ModBiomes {
    val TEST_BIOME: ResourceKey<Biome> =
        ResourceKey.create(Registries.BIOME, ResourceLocation(TutorialMod.MOD_ID, "test_biome"))

    fun bootstrap(context: BootstapContext<Biome>) {
        context.register(TEST_BIOME, testBiome(context))
    }

    fun globalOverworldGeneration(builder: BiomeGenerationSettings.Builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder)
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder)
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder)
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder)
        BiomeDefaultFeatures.addDefaultSprings(builder)
        BiomeDefaultFeatures.addSurfaceFreezing(builder)
    }

    fun testBiome(context: BootstapContext<Biome>) =
        Biome.BiomeBuilder()
            .hasPrecipitation(true)
            .downfall(0.8F)
            .temperature(0.7F)
            .generationSettings(
                BiomeGenerationSettings.Builder(
                    context.lookup(Registries.PLACED_FEATURE),
                    context.lookup(Registries.CONFIGURED_CARVER)
                )
                    .also { biomeBuilder ->
                        globalOverworldGeneration(biomeBuilder)
                        BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder)
                        BiomeDefaultFeatures.addForestFlowers(biomeBuilder)
                        BiomeDefaultFeatures.addFerns(biomeBuilder)
                        BiomeDefaultFeatures.addDefaultOres(biomeBuilder)
                        BiomeDefaultFeatures.addExtraGold(biomeBuilder)

                        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                            VegetationPlacements.TREES_PLAINS)

                        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder)
                        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder)
                        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                            ModPlacedFeatures.PINE_PLACED_KEY)
                    }
                    .build()
            )
            .mobSpawnSettings(
                MobSpawnSettings.Builder()
                    .apply {
                        addSpawn(
                            MobCategory.CREATURE,
                            MobSpawnSettings.SpawnerData(ModEntities.RHINO.get(), 2, 3, 5)
                        )
                        addSpawn(
                            MobCategory.CREATURE,
                            MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4)
                        )
                    }
                    .also { spawnBuilder ->
                        BiomeDefaultFeatures.farmAnimals(spawnBuilder)
                        BiomeDefaultFeatures.commonSpawns(spawnBuilder)
                    }
                    .build()
            )
            .specialEffects(
                BiomeSpecialEffects.Builder()
                    .waterColor(0xE82E3B)
                    .waterFogColor(0xBF1B26)
                    .skyColor(0x30C918)
                    .grassColorOverride(0x7F03FC)
                    .foliageColorOverride(0xD203FC)
                    .fogColor(0x22A1E6)
                    .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                    .backgroundMusic(Musics.createGameMusic(ModSounds.BAR_BRAWL.holder.get()))
                    .build()
            )
            .build()
}