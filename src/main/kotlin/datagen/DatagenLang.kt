package com.dannbrown.remix.datagen

import com.dannbrown.remix.RemixMod
import com.dannbrown.remix.init.DatagenBlocks
import com.dannbrown.remix.init.DatagenCreativeTabs
import com.dannbrown.remix.init.DatagenItems
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.alchemy.Potion
import net.minecraft.world.level.biome.Biome
import net.minecraftforge.common.data.LanguageProvider
import net.minecraftforge.registries.ForgeRegistries

import java.util.function.Supplier

class DatagenLang(output: PackOutput?) : LanguageProvider(output, RemixMod.MOD_ID, "en_us") {

  // @ Main function
  override fun addTranslations() {
    addItemGroup(DatagenCreativeTabs.REMIX_TAB.get(), "Remix")
    addBlock(DatagenBlocks.ADAMANTIUM_BLOCK, "Adamantium Block")
    addBlock(DatagenBlocks.ADAMANTIUM_ORE, "Adamantium Ore")
    addBlock(DatagenBlocks.ADAMANTIUM_DEBRIS, "Adamantium Debris")

    // glow wool
    addBlock(DatagenBlocks.GLOW_WOOL, "Glow Wool")
    addBlock(DatagenBlocks.GLOW_ORANGE_WOOL, "Glow Orange Wool")
    addBlock(DatagenBlocks.GLOW_MAGENTA_WOOL, "Glow Magenta Wool")
    addBlock(DatagenBlocks.GLOW_LIGHT_BLUE_WOOL, "Glow Light Blue Wool")
    addBlock(DatagenBlocks.GLOW_YELLOW_WOOL, "Glow Yellow Wool")
    addBlock(DatagenBlocks.GLOW_LIME_WOOL, "Glow Lime Wool")
    addBlock(DatagenBlocks.GLOW_PINK_WOOL, "Glow Pink Wool")
    addBlock(DatagenBlocks.GLOW_GRAY_WOOL, "Glow Gray Wool")
    addBlock(DatagenBlocks.GLOW_LIGHT_GRAY_WOOL, "Glow Light Gray Wool")
    addBlock(DatagenBlocks.GLOW_CYAN_WOOL, "Glow Cyan Wool")
    addBlock(DatagenBlocks.GLOW_PURPLE_WOOL, "Glow Purple Wool")
    addBlock(DatagenBlocks.GLOW_BLUE_WOOL, "Glow Blue Wool")
    addBlock(DatagenBlocks.GLOW_BROWN_WOOL, "Glow Brown Wool")
    addBlock(DatagenBlocks.GLOW_GREEN_WOOL, "Glow Green Wool")
    addBlock(DatagenBlocks.GLOW_RED_WOOL, "Glow Red Wool")
    addBlock(DatagenBlocks.GLOW_BLACK_WOOL, "Glow Black Wool")
    // glow carpet
    addBlock(DatagenBlocks.GLOW_CARPET, "Glow Carpet")
    addBlock(DatagenBlocks.GLOW_ORANGE_CARPET, "Glow Orange Carpet")
    addBlock(DatagenBlocks.GLOW_MAGENTA_CARPET, "Glow Magenta Carpet")
    addBlock(DatagenBlocks.GLOW_LIGHT_BLUE_CARPET, "Glow Light Blue Carpet")
    addBlock(DatagenBlocks.GLOW_YELLOW_CARPET, "Glow Yellow Carpet")
    addBlock(DatagenBlocks.GLOW_LIME_CARPET, "Glow Lime Carpet")
    addBlock(DatagenBlocks.GLOW_PINK_CARPET, "Glow Pink Carpet")
    addBlock(DatagenBlocks.GLOW_GRAY_CARPET, "Glow Gray Carpet")
    addBlock(DatagenBlocks.GLOW_LIGHT_GRAY_CARPET, "Glow Light Gray Carpet")
    addBlock(DatagenBlocks.GLOW_CYAN_CARPET, "Glow Cyan Carpet")
    addBlock(DatagenBlocks.GLOW_PURPLE_CARPET, "Glow Purple Carpet")
    addBlock(DatagenBlocks.GLOW_BLUE_CARPET, "Glow Blue Carpet")
    addBlock(DatagenBlocks.GLOW_BROWN_CARPET, "Glow Brown Carpet")
    addBlock(DatagenBlocks.GLOW_GREEN_CARPET, "Glow Green Carpet")
    addBlock(DatagenBlocks.GLOW_RED_CARPET, "Glow Red Carpet")
    addBlock(DatagenBlocks.GLOW_BLACK_CARPET, "Glow Black Carpet")

    // @ ITEMS
    addItem(DatagenItems.ADAMANTIUM_INGOT, "Adamantium Ingot")
    addItem(DatagenItems.ADAMANTIUM_FRAGMENT, "Adamantium Fragment")
    addItem(DatagenItems.CUSTOM_ITEM, "Custom Item")
    addItem(DatagenItems.BOOMERANG, "Boomerang")
  }

  // @ Utility functions
  private fun addItemGroup(group: CreativeModeTab, name: String) {
    add(group.displayName.string, name)
  }

  private fun addAdvTitle(advancementTitle: String, name: String) {
    add("advancement.${RemixMod.MOD_ID}.$advancementTitle.title", name)
  }

  private fun addAdvDesc(advancementTitle: String, name: String) {
    add("advancement.${RemixMod.MOD_ID}.$advancementTitle.desc", name)
  }

  private fun addSubtitle(category: String, subtitleName: String, name: String) {
    add("subtitles.$category.$subtitleName", name)
  }

  private fun addBiome(biomeKey: ResourceKey<Biome>, name: String) {
    add("biome.${RemixMod.MOD_ID}." + biomeKey.location().path, name)
  }

  private fun addDeath(deathName: String, name: String) {
    add("death.attack.$deathName", name)
  }

  private fun addPotion(potion: Supplier<out Potion?>, name: String) {
    add(
      "item.minecraft.potion.effect." + ForgeRegistries.POTIONS.getKey(potion.get())!!.path,
      "Potion of $name"
    )
    add(
      "item.minecraft.splash_potion.effect." + ForgeRegistries.POTIONS.getKey(potion.get())!!.path,
      "Splash Potion of $name"
    )
    add(
      "item.minecraft.lingering_potion.effect." + ForgeRegistries.POTIONS.getKey(potion.get())!!.path,
      "Lingering Potion of $name"
    )
    add(
      "item.minecraft.tipped_arrow.effect." + ForgeRegistries.POTIONS.getKey(potion.get())!!.path,
      "Arrow of $name"
    )
  }

  private fun addConfig(configName: String, name: String) {
    add("config.${RemixMod.MOD_ID}.$configName", name)
  }
}