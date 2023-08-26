package com.dannbrown.remix.content.biome

import com.dannbrown.remix.lib.LibBiomeFeatures
import net.minecraft.core.HolderGetter
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.BiomeGenerationSettings
import net.minecraft.world.level.biome.BiomeSpecialEffects
import net.minecraft.world.level.biome.MobSpawnSettings
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver
import net.minecraft.world.level.levelgen.placement.PlacedFeature

object AncientSeaBiome {
  
  fun createBiome(
    placedFeatures: HolderGetter<PlacedFeature>,
    caveGetter: HolderGetter<ConfiguredWorldCarver<*>>
  ): Biome {
    val generationSettings: BiomeGenerationSettings =
      LibBiomeFeatures.addOresAndCaves(BiomeGenerationSettings.Builder(placedFeatures, caveGetter)).build()

    val mobSpawnSettings: MobSpawnSettings = MobSpawnSettings.EMPTY

    val biomeSpecialEffects: BiomeSpecialEffects =
      LibBiomeFeatures.generateColors(BiomeSpecialEffects.Builder(), 1186057, 4477507).build()

    return Biome.BiomeBuilder()
      .generationSettings(generationSettings)
      .mobSpawnSettings(mobSpawnSettings)
      .specialEffects(biomeSpecialEffects)
      .hasPrecipitation(false)
      .downfall(0.0F)
      .temperature(0.8F)
      .build()
  }
}