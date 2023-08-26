package com.dannbrown.remix.content.biome

import com.dannbrown.remix.lib.LibBiomeFeatures.addCaveMobs
import com.dannbrown.remix.lib.LibBiomeFeatures.addOresAndCaves
import com.dannbrown.remix.lib.LibBiomeFeatures.generateColors
import net.minecraft.core.HolderGetter
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.data.worldgen.BiomeDefaultFeatures
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.*
import net.minecraft.world.level.biome.Biome.BiomeBuilder
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver
import net.minecraft.world.level.levelgen.placement.PlacedFeature

object Custom1Biome {
  fun createBiome(
    placedFeatures: HolderGetter<PlacedFeature?>?,
    caveGetter: HolderGetter<ConfiguredWorldCarver<*>?>?
  ): Biome {
    val generationSettings = BiomeGenerationSettings.Builder(
      placedFeatures,
      caveGetter
    )
    BiomeDefaultFeatures.addDefaultCrystalFormations(generationSettings)
    BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings)
    BiomeDefaultFeatures.addDefaultOres(generationSettings)
    addOresAndCaves(generationSettings)
    val effects = BiomeSpecialEffects.Builder()
    generateColors(effects, 1186057, 4477507)
      .fogColor(12638463)
      .waterColor(4159204)
      .waterFogColor(329011)
      .skyColor(7972607)
      .foliageColorOverride(10387789)
      .grassColorOverride(9470285)
      .ambientParticle(AmbientParticleSettings(ParticleTypes.PORTAL, 0.005f))
    val mobSpawnInfo = MobSpawnSettings.Builder()
      .addSpawn(MobCategory.CREATURE, SpawnerData(EntityType.FROG, 20, 4, 4))
    addCaveMobs(mobSpawnInfo)
    return BiomeBuilder()
      .hasPrecipitation(true)
      .temperature(1.5f)
      .downfall(1.25f)
      .generationSettings(generationSettings.build())
      .mobSpawnSettings(mobSpawnInfo.build())
      .specialEffects(effects.build())
      .build()
  }
}
