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
  output: PackOutput?, future: CompletableFuture<HolderLookup.Provider?>?,
  existingFileHelper: ExistingFileHelper?
) : IntrinsicHolderTagsProvider<Block>(
  output, Registries.BLOCK, future,
  Function<Block, ResourceKey<Block>> { block: Block ->
    block.builtInRegistryHolder().key()
  }, RemixMod.MOD_ID,
  existingFileHelper
) {
  override fun getName(): String {
    return "Remix Block Tags"
  }

  override fun addTags(provider: HolderLookup.Provider) {

    // mod forge
    tag(DatagenTags.Blocks.ORES_ADAMANTIUM)
      .add(DatagenBlocks.ADAMANTIUM_ORE.get(), DatagenBlocks.DEEPSLATE_ADAMANTIUM_ORE.get())
    tag(DatagenTags.Blocks.STORAGE_BLOCKS_ADAMANTIUM).add(DatagenBlocks.ADAMANTIUM_BLOCK.get())
    tag(DatagenTags.Blocks.STORAGE_BLOCKS_ADAMANTIUM_DEBRIS).add(DatagenBlocks.ADAMANTIUM_DEBRIS.get())

    // vanilla

    // forge
    tag(Tags.Blocks.ORES).addTags(DatagenTags.Blocks.ORES_ADAMANTIUM)

    // huge mineables lists!
    tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
      DatagenBlocks.ADAMANTIUM_BLOCK.get(),
      DatagenBlocks.ADAMANTIUM_DEBRIS.get(),
      DatagenBlocks.ADAMANTIUM_ORE.get(),
      DatagenBlocks.DEEPSLATE_ADAMANTIUM_ORE.get(),
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
      DatagenBlocks.DEEPSLATE_ADAMANTIUM_ORE.get()
    )
  }
}