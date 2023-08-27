package com.dannbrown.remix.datagen

import com.dannbrown.remix.datagen.provider.DatagenBlockLootTableProvider
import com.dannbrown.remix.init.DatagenBlocks
import com.dannbrown.remix.init.DatagenEntities
import com.google.common.collect.ImmutableSet
import net.minecraft.advancements.critereon.BlockPredicate
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.advancements.critereon.LocationPredicate
import net.minecraft.advancements.critereon.StatePropertiesPredicate
import net.minecraft.core.BlockPos
import net.minecraft.data.PackOutput
import net.minecraft.data.loot.BlockLootSubProvider
import net.minecraft.data.loot.EntityLootSubProvider
import net.minecraft.data.loot.LootTableProvider
import net.minecraft.data.loot.LootTableSubProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.properties.BedPart
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.storage.loot.*
import net.minecraft.world.level.storage.loot.IntRange
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer
import net.minecraft.world.level.storage.loot.functions.*
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.minecraft.world.level.storage.loot.predicates.*
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import java.util.List
import java.util.function.BiConsumer
import java.util.function.Supplier
import java.util.stream.Collectors
import java.util.stream.Stream

class DatagenLootTables(output: PackOutput) :
  LootTableProvider(
    output, setOf<ResourceLocation>(), listOf(
      SubProviderEntry({ Blocks() }, LootContextParamSets.BLOCK),
      SubProviderEntry({ Chests() }, LootContextParamSets.CHEST),
      SubProviderEntry({ Entities() }, LootContextParamSets.ENTITY)
    )
  ) {
  @Override
  override fun validate(map: Map<ResourceLocation, LootTable>, validationtracker: ValidationContext) {
  }

  class Blocks : DatagenBlockLootTableProvider() {
    @Override
    override fun generate() {

      // glow wool
      dropSelf(DatagenBlocks.GLOW_WOOL)
      dropSelf(DatagenBlocks.GLOW_ORANGE_WOOL)
      dropSelf(DatagenBlocks.GLOW_MAGENTA_WOOL)
      dropSelf(DatagenBlocks.GLOW_LIGHT_BLUE_WOOL)
      dropSelf(DatagenBlocks.GLOW_YELLOW_WOOL)
      dropSelf(DatagenBlocks.GLOW_LIME_WOOL)
      dropSelf(DatagenBlocks.GLOW_PINK_WOOL)
      dropSelf(DatagenBlocks.GLOW_GRAY_WOOL)
      dropSelf(DatagenBlocks.GLOW_LIGHT_GRAY_WOOL)
      dropSelf(DatagenBlocks.GLOW_CYAN_WOOL)
      dropSelf(DatagenBlocks.GLOW_PURPLE_WOOL)
      dropSelf(DatagenBlocks.GLOW_BLUE_WOOL)
      dropSelf(DatagenBlocks.GLOW_BROWN_WOOL)
      dropSelf(DatagenBlocks.GLOW_GREEN_WOOL)
      dropSelf(DatagenBlocks.GLOW_RED_WOOL)
      dropSelf(DatagenBlocks.GLOW_BLACK_WOOL)
      // glow carpet
      dropSelf(DatagenBlocks.GLOW_CARPET)
      dropSelf(DatagenBlocks.GLOW_ORANGE_CARPET)
      dropSelf(DatagenBlocks.GLOW_MAGENTA_CARPET)
      dropSelf(DatagenBlocks.GLOW_LIGHT_BLUE_CARPET)
      dropSelf(DatagenBlocks.GLOW_YELLOW_CARPET)
      dropSelf(DatagenBlocks.GLOW_LIME_CARPET)
      dropSelf(DatagenBlocks.GLOW_PINK_CARPET)
      dropSelf(DatagenBlocks.GLOW_GRAY_CARPET)
      dropSelf(DatagenBlocks.GLOW_LIGHT_GRAY_CARPET)
      dropSelf(DatagenBlocks.GLOW_CYAN_CARPET)
      dropSelf(DatagenBlocks.GLOW_PURPLE_CARPET)
      dropSelf(DatagenBlocks.GLOW_BLUE_CARPET)
      dropSelf(DatagenBlocks.GLOW_BROWN_CARPET)
      dropSelf(DatagenBlocks.GLOW_GREEN_CARPET)
      dropSelf(DatagenBlocks.GLOW_RED_CARPET)
      dropSelf(DatagenBlocks.GLOW_BLACK_CARPET)
    }

//    protected val knownBlocks: Iterable<Block>
//      protected get() = DatagenBlocks.BLOCKS.getEntries().stream().map { obj: Supplier<*> -> obj.get() }.collect(
//        Collectors.toList()
//      )
  }

  class Entities : EntityLootSubProvider(FeatureFlags.REGISTRY.allFlags()) {
    @Override
    override fun generate() {

    }

    @Override
    override fun getKnownEntityTypes(): Stream<EntityType<*>> {
      return DatagenEntities.ENTITY_TYPES.entries.stream().map { obj: Supplier<EntityType<*>> -> obj.get() }
    }
  }

  class Chests : LootTableSubProvider {
    @Override
    override fun generate(consumer: BiConsumer<ResourceLocation, LootTable.Builder>) {

    }
  }
}