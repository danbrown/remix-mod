package com.dannbrown.remix.datagen

import com.dannbrown.remix.RemixMod
import com.dannbrown.remix.init.DatagenBlocks
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.IntrinsicHolderTagsProvider
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.block.Block
import net.minecraftforge.common.Tags
import net.minecraftforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture
import java.util.function.Function

class DatagenBlockTags(
  output: PackOutput,
  future: CompletableFuture<HolderLookup.Provider>,
  existingFileHelper: ExistingFileHelper
) : IntrinsicHolderTagsProvider<Block>(
  output,
  Registries.BLOCK,
  future,
  Function<Block, ResourceKey<Block>> { block: Block ->
    block.builtInRegistryHolder().key()
  }, RemixMod.MOD_ID,
  existingFileHelper
) {
  override fun getName(): String {
    return "Remix Block Tags"
  }

  override fun addTags(provider: HolderLookup.Provider) {

    // @ MOD FORGE
    tag(DatagenTags.Blocks.ORES_ADAMANTIUM).add(DatagenBlocks.ADAMANTIUM_ORE.get())
    tag(DatagenTags.Blocks.STORAGE_BLOCKS_ADAMANTIUM).add(DatagenBlocks.ADAMANTIUM_BLOCK.get())
    tag(DatagenTags.Blocks.STORAGE_BLOCKS_ADAMANTIUM_DEBRIS).add(DatagenBlocks.ADAMANTIUM_DEBRIS.get())

    // @ VANILLA
    // glow wool
    tag(BlockTags.WOOL).add(
      DatagenBlocks.GLOW_WOOL.get(),
      DatagenBlocks.GLOW_ORANGE_WOOL.get(),
      DatagenBlocks.GLOW_MAGENTA_WOOL.get(),
      DatagenBlocks.GLOW_LIGHT_BLUE_WOOL.get(),
      DatagenBlocks.GLOW_YELLOW_WOOL.get(),
      DatagenBlocks.GLOW_LIME_WOOL.get(),
      DatagenBlocks.GLOW_PINK_WOOL.get(),
      DatagenBlocks.GLOW_GRAY_WOOL.get(),
      DatagenBlocks.GLOW_LIGHT_GRAY_WOOL.get(),
      DatagenBlocks.GLOW_CYAN_WOOL.get(),
      DatagenBlocks.GLOW_PURPLE_WOOL.get(),
      DatagenBlocks.GLOW_BLUE_WOOL.get(),
      DatagenBlocks.GLOW_BROWN_WOOL.get(),
      DatagenBlocks.GLOW_GREEN_WOOL.get(),
      DatagenBlocks.GLOW_RED_WOOL.get(),
      DatagenBlocks.GLOW_BLACK_WOOL.get()
    )

    // glow carpet
    tag(BlockTags.WOOL_CARPETS).add(
      DatagenBlocks.GLOW_CARPET.get(),
      DatagenBlocks.GLOW_ORANGE_CARPET.get(),
      DatagenBlocks.GLOW_MAGENTA_CARPET.get(),
      DatagenBlocks.GLOW_LIGHT_BLUE_CARPET.get(),
      DatagenBlocks.GLOW_YELLOW_CARPET.get(),
      DatagenBlocks.GLOW_LIME_CARPET.get(),
      DatagenBlocks.GLOW_PINK_CARPET.get(),
      DatagenBlocks.GLOW_GRAY_CARPET.get(),
      DatagenBlocks.GLOW_LIGHT_GRAY_CARPET.get(),
      DatagenBlocks.GLOW_CYAN_CARPET.get(),
      DatagenBlocks.GLOW_PURPLE_CARPET.get(),
      DatagenBlocks.GLOW_BLUE_CARPET.get(),
      DatagenBlocks.GLOW_BROWN_CARPET.get(),
      DatagenBlocks.GLOW_GREEN_CARPET.get(),
      DatagenBlocks.GLOW_RED_CARPET.get(),
      DatagenBlocks.GLOW_BLACK_CARPET.get()
    )

    // @ FORGE
    tag(Tags.Blocks.ORES).addTags(DatagenTags.Blocks.ORES_ADAMANTIUM)

    // mineables lists
    tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
      DatagenBlocks.ADAMANTIUM_BLOCK.get(),
      DatagenBlocks.ADAMANTIUM_DEBRIS.get(),
      DatagenBlocks.ADAMANTIUM_ORE.get(),
      DatagenBlocks.CORE_BLOCK.get(),
      DatagenBlocks.JUMP_BLOCK.get()
    )

    // tag(BlockTags.MINEABLE_WITH_AXE).add( );

    // tag(BlockTags.MINEABLE_WITH_SHOVEL).add();

    // tag(BlockTags.MINEABLE_WITH_HOE).add();

    // tag(BlockTags.NEEDS_STONE_TOOL).add();
    tag(BlockTags.NEEDS_IRON_TOOL).add(
      DatagenBlocks.CORE_BLOCK.get(),
      DatagenBlocks.JUMP_BLOCK.get()
    )
    tag(BlockTags.NEEDS_DIAMOND_TOOL).add(
      DatagenBlocks.ADAMANTIUM_BLOCK.get(),
      DatagenBlocks.ADAMANTIUM_DEBRIS.get(),
      DatagenBlocks.ADAMANTIUM_ORE.get(),
    )
  }
}