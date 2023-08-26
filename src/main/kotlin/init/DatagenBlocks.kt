package com.dannbrown.remix.init

import com.dannbrown.remix.content.block.CoreBlock
import com.dannbrown.remix.content.block.JumpBlock
import com.dannbrown.remix.lib.LibBlockNames
import com.dannbrown.remix.RemixMod
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.DropExperienceBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject

object DatagenBlocks {
  // @ Registering
  val BLOCKS = DeferredRegister.create<Block>(
    ForgeRegistries.BLOCKS,
    RemixMod.MOD_ID
  )

  fun register(bus: IEventBus?) {
    BLOCKS.register(bus)
  }

  // @ Blocks
  // Adamantium
  val ADAMANTIUM_BLOCK = BLOCKS.register(
    LibBlockNames.ADAMANTIUM_BLOCK
  ) {
    Block(
      BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)
        .sound(SoundType.NETHERITE_BLOCK)
    )
  }
  val ADAMANTIUM_ORE = BLOCKS.register<Block>(
    LibBlockNames.ADAMANTIUM_ORE
  ) {
    DropExperienceBlock(
      BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE)
        .sound(SoundType.BASALT)
    )
  }
  val DEEPSLATE_ADAMANTIUM_ORE = BLOCKS
    .register<Block>(
      LibBlockNames.DEEPSLATE_ADAMANTIUM_ORE
    ) {
      DropExperienceBlock(
        BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE)
          .sound(SoundType.BASALT)
      )
    }
  val ADAMANTIUM_DEBRIS = BLOCKS.register(
    LibBlockNames.ADAMANTIUM_DEBRIS
  ) {
    Block(
      BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS)
        .sound(SoundType.ANCIENT_DEBRIS)
    )
  }

  // Jump Block
  val JUMP_BLOCK = BLOCKS.register<Block>(
    LibBlockNames.JUMP_BLOCK
  ) { JumpBlock(BlockBehaviour.Properties.copy(Blocks.DIRT)) }

  // Core Block
  val CORE_BLOCK = BLOCKS.register<Block>(
    LibBlockNames.CORE_BLOCK
  ) {
    CoreBlock(
      BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
        .lightLevel { state: BlockState -> if (state.getValue(CoreBlock.lit)) 15 else 0 }
    )
  }

  // @ Blacklist for block items, it will not register a block item for these
  // blocks
  val DONT_INCLUDE_BLOCK_ITEM: List<RegistryObject<Block>> = mutableListOf()

  // @ Blacklist for creative tab, block item may still be registered
  val DONT_INCLUDE_CREATIVE: List<RegistryObject<Block>> = mutableListOf()
}
