package top.srcres258.tutorialmod.worldgen.dimension

import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.world.level.Level
import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.biome.Climate
import net.minecraft.world.level.biome.MultiNoiseBiomeSource
import net.minecraft.world.level.dimension.BuiltinDimensionTypes
import net.minecraft.world.level.dimension.DimensionType
import net.minecraft.world.level.dimension.LevelStem
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.worldgen.biome.ModBiomes
import java.util.*
import com.mojang.datafixers.util.Pair as MojangPair

object ModDimensions {
    val KAUPENDIM_KEY: ResourceKey<LevelStem> = ResourceKey.create(Registries.LEVEL_STEM,
        ResourceLocation(TutorialMod.MOD_ID, "kaupendim"))
    val KAUPENDIM_LEVEL_KEY: ResourceKey<Level> = ResourceKey.create(Registries.DIMENSION,
        ResourceLocation(TutorialMod.MOD_ID, "kaupendim"))
    val KAUPENDIM_TYPE: ResourceKey<DimensionType> = ResourceKey.create(Registries.DIMENSION_TYPE,
        ResourceLocation(TutorialMod.MOD_ID, "kaupendim_type"))

    fun bootstrapType(context: BootstapContext<DimensionType>) {
        context.register(
            KAUPENDIM_TYPE,
            DimensionType(
                OptionalLong.of(12000),
                false,
                false,
                false,
                false,
                1.0,
                true,
                false,
                0,
                256,
                256,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                1F,
                DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)
            )
        )
    }

    fun bootstrapStem(context: BootstapContext<LevelStem>) {
        val biomeRegistry = context.lookup(Registries.BIOME)
        val dimTypes = context.lookup(Registries.DIMENSION_TYPE)
        val noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS)

        val noiseBasedChunkGenerator = NoiseBasedChunkGenerator(
            MultiNoiseBiomeSource.createFromList(Climate.ParameterList(listOf(
                MojangPair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F),
                    biomeRegistry.getOrThrow(ModBiomes.TEST_BIOME)),
                MojangPair.of(Climate.parameters(0.1F, 0.2F, 0.0F, 0.2F, 0.0F, 0.0F, 0.0F),
                    biomeRegistry.getOrThrow(Biomes.BIRCH_FOREST)),
                MojangPair.of(Climate.parameters(0.3F, 0.6F, 0.1F, 0.1F, 0.0F, 0.0F, 0.0F),
                    biomeRegistry.getOrThrow(Biomes.OCEAN)),
                MojangPair.of(Climate.parameters(0.4F, 0.3F, 0.2F, 0.1F, 0.0F, 0.0F, 0.0F),
                    biomeRegistry.getOrThrow(Biomes.DARK_FOREST))
            ))),
            noiseGenSettings.getOrThrow(NoiseGeneratorSettings.AMPLIFIED)
        )

        val stem = LevelStem(dimTypes.getOrThrow(KAUPENDIM_TYPE), noiseBasedChunkGenerator)

        context.register(KAUPENDIM_KEY, stem)
    }
}