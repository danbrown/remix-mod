package com.dannbrown.remix.lib

import com.dannbrown.remix.datagen.DatagenConfiguredCarvers
import com.dannbrown.remix.datagen.DatagenPlacedFeatures
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.BiomeGenerationSettings
import net.minecraft.world.level.biome.BiomeSpecialEffects
import net.minecraft.world.level.biome.MobSpawnSettings
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData
import net.minecraft.world.level.levelgen.GenerationStep

object LibBiomeFeatures {
  fun addOresAndCaves(builder: BiomeGenerationSettings.Builder): BiomeGenerationSettings.Builder {
    return builder
      .addCarver(GenerationStep.Carving.AIR, DatagenConfiguredCarvers.SAMPLE_CAVE)
      .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DatagenPlacedFeatures.ADAMANTIUM_ORE)
  }

  fun addCaveMobs(builder: MobSpawnSettings.Builder): MobSpawnSettings.Builder {
    return builder
      .addSpawn(MobCategory.MONSTER, SpawnerData(EntityType.FROG, 50, 1, 1))
      .addSpawn(MobCategory.MONSTER, SpawnerData(EntityType.CAVE_SPIDER, 50, 1, 1))
      .addSpawn(MobCategory.MONSTER, SpawnerData(EntityType.STRIDER, 50, 1, 1))
  }

  fun addRotspawn(builder: MobSpawnSettings.Builder): MobSpawnSettings.Builder {
    return builder
      .addSpawn(MobCategory.MONSTER, SpawnerData(EntityType.EVOKER, 100, 2, 4))
      .addSpawn(MobCategory.MONSTER, SpawnerData(EntityType.ALLAY, 100, 4, 4))
      .addSpawn(MobCategory.MONSTER, SpawnerData(EntityType.SNOW_GOLEM, 100, 1, 2))
  }

  fun generateColors(
    builder: BiomeSpecialEffects.Builder,
    skyFog: Int,
    grass: Int
  ): BiomeSpecialEffects.Builder {
    return builder
      .skyColor(1186057)
      .fogColor(skyFog)
      .waterColor(342306)
      .waterFogColor(332810)
      .grassColorOverride(grass)
      .foliageColorOverride(grass)
  }
}
