package com.dannbrown.remix.datagen

import com.dannbrown.remix.content.biome.AncientSeaBiome
import com.dannbrown.remix.content.biome.Custom1Biome
import com.dannbrown.remix.lib.LibUtils.resourceLocation
import com.google.common.collect.ImmutableList
import com.mojang.datafixers.util.Pair
import net.minecraft.core.HolderGetter
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.BiomeSource
import net.minecraft.world.level.biome.Climate
import net.minecraft.world.level.biome.Climate.ParameterList
import net.minecraft.world.level.biome.MultiNoiseBiomeSource

class DatagenBiomes {
  companion object{
    val CUSTOM_BIOME_1 = createBiomeKey("custom_biome_1")
    val ANCIENT_SEA = createBiomeKey("ancient_sea")
    private fun createBiomeKey(name: String): ResourceKey<Biome> {
      return ResourceKey.create(Registries.BIOME, resourceLocation(name))
    }

    fun bootstrap(context: BootstapContext<Biome>) {
      val placedFeatures = context.lookup(Registries.PLACED_FEATURE)
      val worldCarvers = context.lookup(Registries.CONFIGURED_CARVER)
      context.register(CUSTOM_BIOME_1, Custom1Biome.createBiome(placedFeatures, worldCarvers))
      context.register(ANCIENT_SEA, AncientSeaBiome.createBiome(placedFeatures, worldCarvers))
    }

    // Utility functions
    fun buildBiomeSource(biomes: HolderGetter<Biome>): BiomeSource {
      return MultiNoiseBiomeSource.createFromList(
        ParameterList(
          ImmutableList.of(
            Pair.of(
              Climate.parameters(0.0f, 0.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f), biomes.getOrThrow(
                CUSTOM_BIOME_1
              )
            ),
            Pair.of(
              Climate.parameters(-1.0f, -0.4f, -0.9f, -0.7f, -2.0f, 0.0f, 0.0f), biomes.getOrThrow(
                CUSTOM_BIOME_1
              )
            ),
            Pair.of(
              Climate.parameters(1.0f, 0.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f), biomes.getOrThrow(
                CUSTOM_BIOME_1
              )
            ),
            Pair.of(
              Climate.parameters(0.0f, -0.4f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f), biomes.getOrThrow(
                CUSTOM_BIOME_1
              )
            ),
            Pair.of(
              Climate.parameters(1.0f, 0.4f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f), biomes.getOrThrow(
                CUSTOM_BIOME_1
              )
            ),
            Pair.of(
              Climate.parameters(0.0f, 0.0f, 0.9f, 0.0f, -2.0f, 0.0f, 0.0f), biomes.getOrThrow(
                CUSTOM_BIOME_1
              )
            ),
            Pair.of(
              Climate.parameters(0.0f, 0.0f, 0.0f, 0.7f, -2.0f, 0.0f, 0.0f), biomes.getOrThrow(
                CUSTOM_BIOME_1
              )
            ),
            Pair.of(
              Climate.parameters(0.0f, 0.0f, 0.0f, 1.0f, -2.0f, 0.0f, 0.0f), biomes.getOrThrow(
                CUSTOM_BIOME_1
              )
            ),
            Pair.of(
              Climate.parameters(0.0f, 0.0f, 0.0f, 0.0f, -2.0f, 1.0f, 0.0f), biomes.getOrThrow(
                CUSTOM_BIOME_1
              )
            ),
            Pair.of(
              Climate.parameters(1.0f, 0.0f, 0.0f, 0.0f, -2.0f, 1.0f, 0.0f), biomes.getOrThrow(
                CUSTOM_BIOME_1
              )
            ),
            Pair.of(
              Climate.parameters(0.0f, 0.0f, 0.0f, 0.0f, -2.0f, -1.0f, 0.0f), biomes.getOrThrow(
                CUSTOM_BIOME_1
              )
            ),
            Pair.of(
              Climate.parameters(0.0f, 0.0f, 0.0f, 0.7f, -2.0f, -1.0f, 0.0f), biomes.getOrThrow(
                CUSTOM_BIOME_1
              )
            ),
            Pair.of(
              Climate.parameters(
                Climate.Parameter.span(0.0f, 1.0f), Climate.Parameter.span(0.0f, 0.4f),
                Climate.Parameter.span(0.0f, 0.9f), Climate.Parameter.point(0.0f), Climate.Parameter.point(2.0f),
                Climate.Parameter.span(-1.0f, 1.0f), 0.0f
              ), biomes.getOrThrow(ANCIENT_SEA)
            ),
            Pair.of(
              Climate.parameters(
                Climate.Parameter.point(0.0f), Climate.Parameter.point(0.0f),
                Climate.Parameter.point(0.0f), Climate.Parameter.span(0.7f, 1.0f), Climate.Parameter.point(2.0f),
                Climate.Parameter.point(0.0f), 0.0f
              ), biomes.getOrThrow(ANCIENT_SEA)
            ),
            Pair.of(
              Climate.parameters(-0.7f, -0.7f, -0.7f, 0.0f, 2.0f, 0.0f, 0.0f), biomes.getOrThrow(
                ANCIENT_SEA
              )
            )
          )
        )
      )
    }
  }
}
