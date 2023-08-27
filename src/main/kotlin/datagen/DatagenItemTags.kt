package com.dannbrown.remix.datagen

import com.dannbrown.remix.RemixMod
import com.dannbrown.remix.init.DatagenItems
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraftforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class DatagenItemTags(
  output: PackOutput,
  future: CompletableFuture<HolderLookup.Provider>,
  provider: CompletableFuture<TagLookup<Block>>,
  fileHelper: ExistingFileHelper
) :
  ItemTagsProvider(output, future, provider, RemixMod.MOD_ID, fileHelper) {
  override fun getName(): String {
    return "Datagen Item Tags"
  }

  override public fun tag(p_255730_: TagKey<Item>): IntrinsicTagAppender<Item> {
    return super.tag(p_255730_)
  }

  override fun addTags(provider: HolderLookup.Provider) {

    // modded forge
    tag(DatagenTags.Items.RAW_MATERIALS_ADADMANTIUM).add(DatagenItems.ADAMANTIUM_FRAGMENT.get())
    tag(DatagenTags.Items.INGOTS_ADAMANTIUM).add(DatagenItems.ADAMANTIUM_INGOT.get())
    copy(DatagenTags.Blocks.ORES_ADAMANTIUM, DatagenTags.Items.ORES_ADAMANTIUM)
    copy(
      DatagenTags.Blocks.STORAGE_BLOCKS_ADAMANTIUM,
      DatagenTags.Items.STORAGE_BLOCKS_ADAMANTIUM
    )
    copy(
      DatagenTags.Blocks.STORAGE_BLOCKS_ADAMANTIUM_DEBRIS,
      DatagenTags.Items.STORAGE_BLOCKS_ADAMANTIUM_DEBRIS
    )
  }
}