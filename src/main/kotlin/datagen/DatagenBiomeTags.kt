package com.dannbrown.remix.datagen

import com.dannbrown.remix.RemixMod
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.BiomeTagsProvider
import net.minecraft.tags.BiomeTags
import net.minecraftforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class DatagenBiomeTags(
  output: PackOutput?,
  future: CompletableFuture<HolderLookup.Provider?>?,
  existingFileHelper: ExistingFileHelper?
) : BiomeTagsProvider(output, future, RemixMod.MOD_ID, existingFileHelper) {
  override fun addTags(provider: HolderLookup.Provider) {
    super.addTags(provider)

    // databox
    tag(DatagenTags.Biomes.IS_DATABOX).add(
      DatagenBiomes.ANCIENT_SEA
    )

    // vanilla
    tag(BiomeTags.WITHOUT_ZOMBIE_SIEGES).addTag(DatagenTags.Biomes.IS_DATABOX)
    tag(BiomeTags.WITHOUT_PATROL_SPAWNS).addTag(DatagenTags.Biomes.IS_DATABOX)
    tag(BiomeTags.WITHOUT_WANDERING_TRADER_SPAWNS).addTag(DatagenTags.Biomes.IS_DATABOX)
  }

  override fun getName(): String {
    return "Remix Biome Tags"
  }
}
