package com.dannbrown.remix.init

import com.dannbrown.remix.content.item.CustomItem
import com.dannbrown.remix.lib.LibItemNames
import com.dannbrown.remix.lib.LibUtils.defaultItemProps
import com.dannbrown.remix.RemixMod
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import java.util.function.Consumer

object DatagenItems {
  // @ Utils
  private val defaultProps = defaultItemProps()

  // @ Registry
  val ITEMS = DeferredRegister.create<Item>(ForgeRegistries.ITEMS, RemixMod.MOD_ID)
  val BLOCK_ITEMS = DeferredRegister.create<Item>(
    ForgeRegistries.ITEMS,
    RemixMod.MOD_ID
  )

  fun register(bus: IEventBus?) {
    ITEMS.register(bus)
    BLOCK_ITEMS.register(bus)
  }

  init {
    registerBlockItems()
  }

  private fun registerBlockItems() {
    DatagenBlocks.BLOCKS.entries.forEach(Consumer { item: RegistryObject<Block> ->
      if (!DatagenBlocks.DONT_INCLUDE_BLOCK_ITEM.contains(item)) {
        BLOCK_ITEMS.register(
          item.id.path
        ) { BlockItem(item.get(), defaultProps) }
      }
    })
  }

  // @ Items
  // adamantium
  val ADAMANTIUM_INGOT = ITEMS.register(
    LibItemNames.ADAMANTIUM_INGOT
  ) { Item(defaultProps) }
  val ADAMANTIUM_FRAGMENT = ITEMS.register(
    LibItemNames.ADAMANTIUM_FRAGMENT
  ) { Item(defaultProps) }

  // custom items
  val CUSTOM_ITEM = ITEMS.register<Item>(
    LibItemNames.CUSTOM_ITEM
  ) { CustomItem(defaultProps) }

  // @ Blacklist for creative tab
  val DONT_INCLUDE_CREATIVE: MutableList<RegistryObject<Item>> = ArrayList()

  init {
    DONT_INCLUDE_CREATIVE.add(ADAMANTIUM_INGOT)
    DONT_INCLUDE_CREATIVE.add(CUSTOM_ITEM)
  }
}
