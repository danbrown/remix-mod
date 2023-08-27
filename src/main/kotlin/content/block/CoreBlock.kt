package com.dannbrown.remix.content.block

import net.minecraft.core.BlockPos
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.phys.BlockHitResult

class CoreBlock() : Block(
  Properties.copy(Blocks.IRON_BLOCK)
    .lightLevel { state: BlockState -> if (state.getValue(lit)) 15 else 0 }
) {
  companion object {
    val lit: BooleanProperty = BooleanProperty.create("lit")
  }

  //
  override fun createBlockStateDefinition(builder: Builder<Block, BlockState>) {
    builder.add(lit)
    return super.createBlockStateDefinition(builder)
  }

  //  if clicked with main hand, toggle lit
  override fun use(
    blockState: BlockState, level: Level, blockPos: BlockPos?, player: Player,
    hand: InteractionHand, blockHitResult: BlockHitResult?
  ): InteractionResult {
    if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
      level.setBlock(blockPos, blockState.setValue(lit, !blockState.getValue(lit)), 3)
    }
    return super.use(blockState, level, blockPos, player, hand, blockHitResult)
  }
}