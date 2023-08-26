package com.dannbrown.remix.datagen

import com.dannbrown.remix.lib.LibUtils.resourceLocation
import com.dannbrown.remix.init.DatagenBlocks
import com.google.common.collect.ImmutableList
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration.TargetBlockState
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest

object DatagenConfiguredFeatures {
  val BASALT_ORE_REPLACEABLES: RuleTest = TagMatchTest(DatagenTags.Blocks.BASALT_ORE_REPLACEABLES)
  val ADAMANTIUM_ORE = create("adamantium_ore")
  fun create(name: String?): ResourceKey<ConfiguredFeature<*, *>> {
    return ResourceKey.create(Registries.CONFIGURED_FEATURE, resourceLocation(name!!))
  }

  fun bootstrap(context: BootstapContext<ConfiguredFeature<*, *>>) {
    val targets: List<TargetBlockState> = ImmutableList.of<TargetBlockState>(
      OreConfiguration.target(BASALT_ORE_REPLACEABLES, DatagenBlocks.ADAMANTIUM_ORE.get().defaultBlockState()),
      OreConfiguration.target(BASALT_ORE_REPLACEABLES, DatagenBlocks.ADAMANTIUM_ORE.get().defaultBlockState())
    )
    context.register(
      ADAMANTIUM_ORE, ConfiguredFeature(
        Feature.ORE, OreConfiguration(
          targets,
          17
        )
      )
    )
    return

    // context.register(
    // ADAMANTIUM_ORE,
    // new ConfiguredFeature<>(
    // Feature.ORE,
    // new OreConfiguration(targets,
    // 17)).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE)
    // .decorate(ConfiguredFeatures.Decorators.TOP_SOLID_HEIGHTMAP)
    // .applyChance(4)
    // .build());
  }
}
