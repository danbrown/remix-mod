package com.dannbrown.remix.init

import com.dannbrown.remix.lib.LibBlockNames
import com.dannbrown.remix.RemixMod
import com.dannbrown.remix.content.block.*
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
  val BLOCKS = DeferredRegister.create(
    ForgeRegistries.BLOCKS,
    RemixMod.MOD_ID
  )

  fun register(bus: IEventBus?) {
    BLOCKS.register(bus)
  }

  // @ Blocks
  // Adamantium
  val ADAMANTIUM_BLOCK = BLOCKS.register(LibBlockNames.ADAMANTIUM_BLOCK) { AdamantiumBlock() }
  val ADAMANTIUM_ORE = BLOCKS.register(LibBlockNames.ADAMANTIUM_ORE) { AdamantiumOre() }
  val ADAMANTIUM_DEBRIS = BLOCKS.register(LibBlockNames.ADAMANTIUM_DEBRIS) { AdamantiumDebris() }

  // Jump Block
  val JUMP_BLOCK = BLOCKS.register(LibBlockNames.JUMP_BLOCK) { JumpBlock() }

  // Core Block
  val CORE_BLOCK = BLOCKS.register(LibBlockNames.CORE_BLOCK) { CoreBlock() }

  // @ Blacklist for block items, it will not register a block item for these
  // blocks
  val DONT_INCLUDE_BLOCK_ITEM: List<RegistryObject<Block>> = mutableListOf()

  // @ Blacklist for creative tab, block item may still be registered
  val DONT_INCLUDE_CREATIVE: List<RegistryObject<Block>> = mutableListOf()
}
