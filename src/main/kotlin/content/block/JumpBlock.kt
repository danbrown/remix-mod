package com.dannbrown.remix.content.block

import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult


class JumpBlock() : Block(Properties.copy(Blocks.DIRT)) {
  override fun use(
    blockState: BlockState?, level: Level, blockPos: BlockPos?, player: Player,
    hand: InteractionHand?, blockHitResult: BlockHitResult?
  ): InteractionResult {
    player.sendSystemMessage(Component.literal("You clicked on a jump block!"))
    return super.use(blockState, level, blockPos, player, hand, blockHitResult)
  }

  override fun stepOn(level: Level, blockPos: BlockPos?, blockState: BlockState, entity: Entity) {
    if (entity is LivingEntity) {
      if (entity is Player && entity.isShiftKeyDown()) {
        return
      }
      entity.setDeltaMovement(entity.getDeltaMovement().add(0.0, 1.0, 0.0))
    }
    super.stepOn(level, blockPos, blockState, entity)
  }
}