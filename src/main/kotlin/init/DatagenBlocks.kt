package com.dannbrown.remix.init

import com.dannbrown.remix.lib.LibBlockNames
import com.dannbrown.remix.RemixMod
import com.dannbrown.remix.content.block.*
import net.minecraft.world.item.DyeColor
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.DropExperienceBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.MapColor
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

  // Glow wool blocks
  val GLOW_WOOL = BLOCKS.register(LibBlockNames.GLOW_WOOL) { GlowWoolBlock(MapColor.SNOW) }
  val GLOW_ORANGE_WOOL = BLOCKS.register(LibBlockNames.GLOW_ORANGE_WOOL) { GlowWoolBlock(MapColor.COLOR_ORANGE) }
  val GLOW_MAGENTA_WOOL = BLOCKS.register(LibBlockNames.GLOW_MAGENTA_WOOL) { GlowWoolBlock(MapColor.COLOR_MAGENTA) }
  val GLOW_LIGHT_BLUE_WOOL =
    BLOCKS.register(LibBlockNames.GLOW_LIGHT_BLUE_WOOL) { GlowWoolBlock(MapColor.COLOR_LIGHT_BLUE) }
  val GLOW_YELLOW_WOOL = BLOCKS.register(LibBlockNames.GLOW_YELLOW_WOOL) { GlowWoolBlock(MapColor.COLOR_YELLOW) }
  val GLOW_LIME_WOOL = BLOCKS.register(LibBlockNames.GLOW_LIME_WOOL) { GlowWoolBlock(MapColor.COLOR_LIGHT_GREEN) }
  val GLOW_PINK_WOOL = BLOCKS.register(LibBlockNames.GLOW_PINK_WOOL) { GlowWoolBlock(MapColor.COLOR_PINK) }
  val GLOW_GRAY_WOOL = BLOCKS.register(LibBlockNames.GLOW_GRAY_WOOL) { GlowWoolBlock(MapColor.COLOR_GRAY) }
  val GLOW_LIGHT_GRAY_WOOL =
    BLOCKS.register(LibBlockNames.GLOW_LIGHT_GRAY_WOOL) { GlowWoolBlock(MapColor.COLOR_LIGHT_GRAY) }
  val GLOW_CYAN_WOOL = BLOCKS.register(LibBlockNames.GLOW_CYAN_WOOL) { GlowWoolBlock(MapColor.COLOR_CYAN) }
  val GLOW_PURPLE_WOOL = BLOCKS.register(LibBlockNames.GLOW_PURPLE_WOOL) { GlowWoolBlock(MapColor.COLOR_PURPLE) }
  val GLOW_BLUE_WOOL = BLOCKS.register(LibBlockNames.GLOW_BLUE_WOOL) { GlowWoolBlock(MapColor.COLOR_BLUE) }
  val GLOW_BROWN_WOOL = BLOCKS.register(LibBlockNames.GLOW_BROWN_WOOL) { GlowWoolBlock(MapColor.COLOR_BROWN) }
  val GLOW_GREEN_WOOL = BLOCKS.register(LibBlockNames.GLOW_GREEN_WOOL) { GlowWoolBlock(MapColor.COLOR_GREEN) }
  val GLOW_RED_WOOL = BLOCKS.register(LibBlockNames.GLOW_RED_WOOL) { GlowWoolBlock(MapColor.COLOR_RED) }
  val GLOW_BLACK_WOOL = BLOCKS.register(LibBlockNames.GLOW_BLACK_WOOL) { GlowWoolBlock(MapColor.COLOR_BLACK) }

  // Glow carpet blocks
  val GLOW_CARPET = BLOCKS.register(LibBlockNames.GLOW_CARPET) { GlowCarpetBlock(MapColor.SNOW, DyeColor.WHITE) }
  val GLOW_ORANGE_CARPET =
    BLOCKS.register(LibBlockNames.GLOW_ORANGE_CARPET) { GlowCarpetBlock(MapColor.COLOR_ORANGE, DyeColor.ORANGE) }
  val GLOW_MAGENTA_CARPET =
    BLOCKS.register(LibBlockNames.GLOW_MAGENTA_CARPET) { GlowCarpetBlock(MapColor.COLOR_MAGENTA, DyeColor.MAGENTA) }
  val GLOW_LIGHT_BLUE_CARPET = BLOCKS.register(LibBlockNames.GLOW_LIGHT_BLUE_CARPET) {
    GlowCarpetBlock(
      MapColor.COLOR_LIGHT_BLUE,
      DyeColor.LIGHT_BLUE
    )
  }
  val GLOW_YELLOW_CARPET =
    BLOCKS.register(LibBlockNames.GLOW_YELLOW_CARPET) { GlowCarpetBlock(MapColor.COLOR_YELLOW, DyeColor.YELLOW) }
  val GLOW_LIME_CARPET =
    BLOCKS.register(LibBlockNames.GLOW_LIME_CARPET) { GlowCarpetBlock(MapColor.COLOR_LIGHT_GREEN, DyeColor.LIME) }
  val GLOW_PINK_CARPET =
    BLOCKS.register(LibBlockNames.GLOW_PINK_CARPET) { GlowCarpetBlock(MapColor.COLOR_PINK, DyeColor.PINK) }
  val GLOW_GRAY_CARPET =
    BLOCKS.register(LibBlockNames.GLOW_GRAY_CARPET) { GlowCarpetBlock(MapColor.COLOR_GRAY, DyeColor.GRAY) }
  val GLOW_LIGHT_GRAY_CARPET = BLOCKS.register(LibBlockNames.GLOW_LIGHT_GRAY_CARPET) {
    GlowCarpetBlock(
      MapColor.COLOR_LIGHT_GRAY,
      DyeColor.LIGHT_GRAY
    )
  }
  val GLOW_CYAN_CARPET =
    BLOCKS.register(LibBlockNames.GLOW_CYAN_CARPET) { GlowCarpetBlock(MapColor.COLOR_CYAN, DyeColor.CYAN) }
  val GLOW_PURPLE_CARPET =
    BLOCKS.register(LibBlockNames.GLOW_PURPLE_CARPET) { GlowCarpetBlock(MapColor.COLOR_PURPLE, DyeColor.PURPLE) }
  val GLOW_BLUE_CARPET =
    BLOCKS.register(LibBlockNames.GLOW_BLUE_CARPET) { GlowCarpetBlock(MapColor.COLOR_BLUE, DyeColor.BLUE) }
  val GLOW_BROWN_CARPET =
    BLOCKS.register(LibBlockNames.GLOW_BROWN_CARPET) { GlowCarpetBlock(MapColor.COLOR_BROWN, DyeColor.BROWN) }
  val GLOW_GREEN_CARPET =
    BLOCKS.register(LibBlockNames.GLOW_GREEN_CARPET) { GlowCarpetBlock(MapColor.COLOR_GREEN, DyeColor.GREEN) }
  val GLOW_RED_CARPET =
    BLOCKS.register(LibBlockNames.GLOW_RED_CARPET) { GlowCarpetBlock(MapColor.COLOR_RED, DyeColor.RED) }
  val GLOW_BLACK_CARPET =
    BLOCKS.register(LibBlockNames.GLOW_BLACK_CARPET) { GlowCarpetBlock(MapColor.COLOR_BLACK, DyeColor.BLACK) }


  // @ Blacklist for block items, it will not register a block item for these
  // blocks
  val DONT_INCLUDE_BLOCK_ITEM: List<RegistryObject<Block>> = mutableListOf()

  // @ Blacklist for creative tab, block item may still be registered
  val DONT_INCLUDE_CREATIVE: List<RegistryObject<Block>> = mutableListOf()
}
