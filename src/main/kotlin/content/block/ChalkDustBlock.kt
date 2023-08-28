package com.dannbrown.remix.content.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.item.Items
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.material.MapColor
import net.minecraft.world.level.material.PushReaction
import java.util.function.ToIntFunction

//class ChalkDustBlock() :
//  RedStoneWireBlock(Properties.of().noCollission().instabreak().pushReaction(PushReaction.DESTROY)) {
//}


class ChalkDustBlock() : MultifaceBlock(
  Properties.of()
    .mapColor(MapColor.GLOW_LICHEN)
    .replaceable()
    .noCollission()
    .noOcclusion()
    .instabreak()
    .sound(SoundType.CALCITE)
    .lightLevel(emission(7))
    .ignitedByLava()
    .pushReaction(PushReaction.DESTROY)
),
  BonemealableBlock,
  SimpleWaterloggedBlock {
  private val spreader = MultifaceSpreader(this)

  init {
    registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false))
  }

  override fun createBlockStateDefinition(p_153309_: StateDefinition.Builder<Block, BlockState>) {
    super.createBlockStateDefinition(p_153309_)
    p_153309_.add(WATERLOGGED)
  }

  override fun updateShape(
    p_153302_: BlockState,
    p_153303_: Direction,
    p_153304_: BlockState,
    p_153305_: LevelAccessor,
    p_153306_: BlockPos,
    p_153307_: BlockPos
  ): BlockState {
    if (p_153302_.getValue(WATERLOGGED)) {
      p_153305_.scheduleTick(p_153306_, Fluids.WATER, Fluids.WATER.getTickDelay(p_153305_))
    }
    return super.updateShape(p_153302_, p_153303_, p_153304_, p_153305_, p_153306_, p_153307_)
  }

  override fun canBeReplaced(p_153299_: BlockState, p_153300_: BlockPlaceContext): Boolean {
    return !p_153300_.itemInHand.`is`(Items.GLOW_LICHEN) || super.canBeReplaced(p_153299_, p_153300_)
  }

  override fun isValidBonemealTarget(
    p_256569_: LevelReader,
    p_153290_: BlockPos,
    p_153291_: BlockState,
    p_153292_: Boolean
  ): Boolean {
    return Direction.stream().anyMatch { p_153316_: Direction ->
      spreader.canSpreadInAnyDirection(
        p_153291_,
        p_256569_,
        p_153290_,
        p_153316_.opposite
      )
    }
  }

  override fun isBonemealSuccess(
    p_221264_: Level,
    p_221265_: RandomSource,
    p_221266_: BlockPos,
    p_221267_: BlockState
  ): Boolean {
    return true
  }

  override fun performBonemeal(
    p_221259_: ServerLevel,
    p_221260_: RandomSource,
    p_221261_: BlockPos,
    p_221262_: BlockState
  ) {
    spreader.spreadFromRandomFaceTowardRandomDirection(p_221262_, p_221259_, p_221261_, p_221260_)
  }

  override fun getFluidState(p_153311_: BlockState): FluidState {
    return if (p_153311_.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(p_153311_)
  }

  override fun propagatesSkylightDown(p_181225_: BlockState, p_181226_: BlockGetter, p_181227_: BlockPos): Boolean {
    return p_181225_.fluidState.isEmpty
  }

  override fun getSpreader(): MultifaceSpreader {
    return spreader
  }

  companion object {
    private val WATERLOGGED = BlockStateProperties.WATERLOGGED
    fun emission(p_181223_: Int): ToIntFunction<BlockState> {
      return ToIntFunction { p_181221_: BlockState? -> if (hasAnyFace(p_181221_)) p_181223_ else 0 }
    }
  }
}