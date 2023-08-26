package com.dannbrown.remix.datagen.provider

import net.minecraft.data.loot.BlockLootSubProvider
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.item.Item
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SlabBlock
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import java.util.function.Supplier

abstract class DatagenBlockLootTableProvider protected constructor() :
  BlockLootSubProvider(setOf(), FeatureFlags.REGISTRY.allFlags()) {
  fun dropSelf(block: Supplier<out Block?>) {
    super.dropSelf(block.get())
  }

  fun slab(slab: Supplier<out SlabBlock?>) {
    this.add(
      slab.get()
    ) { p_251313_: Block? ->
      createSlabItemTable(
        p_251313_
      )
    }
  }

  fun dropOther(brokenBlock: Supplier<out Block?>, droppedBlock: ItemLike?) {
    super.dropOther(brokenBlock.get(), droppedBlock)
  }

  fun dropAsSilk(block: Supplier<out Block?>) {
    super.dropWhenSilkTouch(block.get())
  }

  fun dropWithSilk(block: Supplier<out Block?>, drop: Supplier<out ItemLike?>) {
    add(
      block.get()
    ) { result: Block? ->
      createSingleItemTableWithSilkTouch(
        result,
        drop.get()
      )
    }
  }

  fun ore(block: Supplier<out Block?>, drop: Supplier<out Item?>) {
    super.add(
      block.get()
    ) { result: Block? ->
      createOreDrop(
        result,
        drop.get()
      )
    }
  }

  fun ore(block: Supplier<out Block?>, drop: Item?) {
    super.add(
      block.get()
    ) { result: Block? ->
      createOreDrop(
        result,
        drop
      )
    }
  }

  fun nuggetOre(block: Supplier<out Block?>, drop: Item?) {
    this.add(
      block.get()
    ) { ore: Block? ->
      createSilkTouchDispatchTable(
        ore,
        applyExplosionDecay(
          ore,
          LootItem.lootTableItem(drop).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f, 6.0f)))
            .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
        )
      )
    }
  }
}