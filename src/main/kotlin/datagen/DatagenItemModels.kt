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
    // @ BLOCKS
    block(DatagenBlocks.ADAMANTIUM_BLOCK)
    block(DatagenBlocks.ADAMANTIUM_ORE)
    block(DatagenBlocks.ADAMANTIUM_DEBRIS)
    blockLamp(DatagenBlocks.CORE_BLOCK)
    block(DatagenBlocks.JUMP_BLOCK)

    // glow wool
    blockVanilla(DatagenBlocks.GLOW_WOOL, "white_wool")
    blockVanilla(DatagenBlocks.GLOW_ORANGE_WOOL, "orange_wool")
    blockVanilla(DatagenBlocks.GLOW_MAGENTA_WOOL, "magenta_wool")
    blockVanilla(DatagenBlocks.GLOW_LIGHT_BLUE_WOOL, "light_blue_wool")
    blockVanilla(DatagenBlocks.GLOW_YELLOW_WOOL, "yellow_wool")
    blockVanilla(DatagenBlocks.GLOW_LIME_WOOL, "lime_wool")
    blockVanilla(DatagenBlocks.GLOW_PINK_WOOL, "pink_wool")
    blockVanilla(DatagenBlocks.GLOW_GRAY_WOOL, "gray_wool")
    blockVanilla(DatagenBlocks.GLOW_LIGHT_GRAY_WOOL, "light_gray_wool")
    blockVanilla(DatagenBlocks.GLOW_CYAN_WOOL, "cyan_wool")
    blockVanilla(DatagenBlocks.GLOW_PURPLE_WOOL, "purple_wool")
    blockVanilla(DatagenBlocks.GLOW_BLUE_WOOL, "blue_wool")
    blockVanilla(DatagenBlocks.GLOW_BROWN_WOOL, "brown_wool")
    blockVanilla(DatagenBlocks.GLOW_GREEN_WOOL, "green_wool")
    blockVanilla(DatagenBlocks.GLOW_RED_WOOL, "red_wool")
    blockVanilla(DatagenBlocks.GLOW_BLACK_WOOL, "black_wool")

    // glow carpet
    blockVanilla(DatagenBlocks.GLOW_CARPET, "white_carpet")
    blockVanilla(DatagenBlocks.GLOW_ORANGE_CARPET, "orange_carpet")
    blockVanilla(DatagenBlocks.GLOW_MAGENTA_CARPET, "magenta_carpet")
    blockVanilla(DatagenBlocks.GLOW_LIGHT_BLUE_CARPET, "light_blue_carpet")
    blockVanilla(DatagenBlocks.GLOW_YELLOW_CARPET, "yellow_carpet")
    blockVanilla(DatagenBlocks.GLOW_LIME_CARPET, "lime_carpet")
    blockVanilla(DatagenBlocks.GLOW_PINK_CARPET, "pink_carpet")
    blockVanilla(DatagenBlocks.GLOW_GRAY_CARPET, "gray_carpet")
    blockVanilla(DatagenBlocks.GLOW_LIGHT_GRAY_CARPET, "light_gray_carpet")
    blockVanilla(DatagenBlocks.GLOW_CYAN_CARPET, "cyan_carpet")
    blockVanilla(DatagenBlocks.GLOW_PURPLE_CARPET, "purple_carpet")
    blockVanilla(DatagenBlocks.GLOW_BLUE_CARPET, "blue_carpet")
    blockVanilla(DatagenBlocks.GLOW_BROWN_CARPET, "brown_carpet")
    blockVanilla(DatagenBlocks.GLOW_GREEN_CARPET, "green_carpet")
    blockVanilla(DatagenBlocks.GLOW_RED_CARPET, "red_carpet")
    blockVanilla(DatagenBlocks.GLOW_BLACK_CARPET, "black_carpet")

    // @ ITEMS
    normalItem(DatagenItems.ADAMANTIUM_INGOT)
    normalItem(DatagenItems.ADAMANTIUM_FRAGMENT)
    normalItem(DatagenItems.CUSTOM_ITEM)
    normalItem(DatagenItems.BOOMERANG)
  }
}