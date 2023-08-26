package com.dannbrown.remix.datagen

import com.dannbrown.remix.datagen.provider.DatagenItemModelProvider
import com.dannbrown.remix.init.DatagenBlocks
import com.dannbrown.remix.init.DatagenItems
import net.minecraft.data.PackOutput
import net.minecraftforge.common.data.ExistingFileHelper

class DatagenItemModels(output: PackOutput?, fileHelper: ExistingFileHelper?) :
  DatagenItemModelProvider(output, fileHelper) {

  override fun getName(): String {
    return "Remix Item Models"
  }


  override fun registerModels() {
    block(DatagenBlocks.ADAMANTIUM_BLOCK)
    block(DatagenBlocks.ADAMANTIUM_ORE)
    block(DatagenBlocks.DEEPSLATE_ADAMANTIUM_ORE)
    block(DatagenBlocks.ADAMANTIUM_DEBRIS)
    blockLamp(DatagenBlocks.CORE_BLOCK)
    block(DatagenBlocks.JUMP_BLOCK)

    // @ ITEMS
    normalItem(DatagenItems.ADAMANTIUM_INGOT)
    normalItem(DatagenItems.ADAMANTIUM_FRAGMENT)
    normalItem(DatagenItems.CUSTOM_ITEM)
  }
}