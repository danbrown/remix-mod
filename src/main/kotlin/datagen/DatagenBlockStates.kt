package com.dannbrown.remix.datagen

import com.dannbrown.remix.datagen.provider.DatagenBlockstateProvider
import com.dannbrown.remix.init.DatagenBlocks
import net.minecraft.data.PackOutput
import net.minecraftforge.common.data.ExistingFileHelper

class DatagenBlockStates(output: PackOutput?, fileHelper: ExistingFileHelper?) :
  DatagenBlockstateProvider(output, fileHelper) {

  override fun getName(): String {
    return "Remix Block States"
  }

  override fun registerStatesAndModels() {
    block(DatagenBlocks.ADAMANTIUM_BLOCK)
    block(DatagenBlocks.ADAMANTIUM_DEBRIS)
    block(DatagenBlocks.ADAMANTIUM_ORE)
    block(DatagenBlocks.DEEPSLATE_ADAMANTIUM_ORE)
    blockLamp(DatagenBlocks.CORE_BLOCK)
    block(DatagenBlocks.JUMP_BLOCK)
  }
}