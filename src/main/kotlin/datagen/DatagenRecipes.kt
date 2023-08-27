package com.dannbrown.remix.datagen

import com.dannbrown.remix.RemixMod
import com.dannbrown.remix.datagen.provider.DatagenRecipeProvider
import com.dannbrown.remix.init.DatagenBlocks
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.resources.ResourceLocation
import java.util.function.Consumer

class DatagenRecipes(output: PackOutput?) : DatagenRecipeProvider(output) {
  override fun buildRecipes(consumer: Consumer<FinishedRecipe?>?) {
    makeCarpet(DatagenBlocks.GLOW_CARPET, DatagenBlocks.GLOW_WOOL).save(consumer)
    makeCarpet(DatagenBlocks.GLOW_ORANGE_CARPET, DatagenBlocks.GLOW_ORANGE_WOOL).save(consumer)
    makeCarpet(DatagenBlocks.GLOW_MAGENTA_CARPET, DatagenBlocks.GLOW_MAGENTA_WOOL).save(consumer)
    makeCarpet(DatagenBlocks.GLOW_LIGHT_BLUE_CARPET, DatagenBlocks.GLOW_LIGHT_BLUE_WOOL).save(consumer)
    makeCarpet(DatagenBlocks.GLOW_YELLOW_CARPET, DatagenBlocks.GLOW_YELLOW_WOOL).save(consumer)
    makeCarpet(DatagenBlocks.GLOW_LIME_CARPET, DatagenBlocks.GLOW_LIME_WOOL).save(consumer)
    makeCarpet(DatagenBlocks.GLOW_PINK_CARPET, DatagenBlocks.GLOW_PINK_WOOL).save(consumer)
    makeCarpet(DatagenBlocks.GLOW_GRAY_CARPET, DatagenBlocks.GLOW_GRAY_WOOL).save(consumer)
    makeCarpet(DatagenBlocks.GLOW_LIGHT_GRAY_CARPET, DatagenBlocks.GLOW_LIGHT_GRAY_WOOL).save(consumer)
    makeCarpet(DatagenBlocks.GLOW_CYAN_CARPET, DatagenBlocks.GLOW_CYAN_WOOL).save(consumer)
    makeCarpet(DatagenBlocks.GLOW_PURPLE_CARPET, DatagenBlocks.GLOW_PURPLE_WOOL).save(consumer)
    makeCarpet(DatagenBlocks.GLOW_BLUE_CARPET, DatagenBlocks.GLOW_BLUE_WOOL).save(consumer)
    makeCarpet(DatagenBlocks.GLOW_BROWN_CARPET, DatagenBlocks.GLOW_BROWN_WOOL).save(consumer)
    makeCarpet(DatagenBlocks.GLOW_GREEN_CARPET, DatagenBlocks.GLOW_GREEN_WOOL).save(consumer)
    makeCarpet(DatagenBlocks.GLOW_RED_CARPET, DatagenBlocks.GLOW_RED_WOOL).save(consumer)
    makeCarpet(DatagenBlocks.GLOW_BLACK_CARPET, DatagenBlocks.GLOW_BLACK_WOOL).save(consumer)
  }

  private fun name(name: String): ResourceLocation {
    return ResourceLocation(RemixMod.MOD_ID, name)
  }
}