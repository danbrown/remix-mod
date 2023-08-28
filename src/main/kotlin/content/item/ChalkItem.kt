package com.dannbrown.remix.content.item

import com.dannbrown.remix.init.DatagenBlocks
import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionResult
import net.minecraft.world.item.ItemNameBlockItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.Property
import net.minecraft.world.level.gameevent.GameEvent

class ChalkItem() : ItemNameBlockItem(DatagenBlocks.CHALK_DUST.get(), Properties().durability(64)) {

  override fun useOn(context: UseOnContext): InteractionResult {
    val interactionresult = place(BlockPlaceContext(context))
    return if (!interactionresult.consumesAction() && this.isEdible) {
      val interactionresult1 = this.use(context.level, context.player, context.hand).result
      if (interactionresult1 == InteractionResult.CONSUME) InteractionResult.CONSUME_PARTIAL else interactionresult1
    } else {
      interactionresult
    }
  }

  override fun place(placeContext: BlockPlaceContext): InteractionResult {
    return if (!block.isEnabled(placeContext.level.enabledFeatures())) {
      InteractionResult.FAIL
    } else if (!placeContext.canPlace()) {
      InteractionResult.FAIL
    } else {
      val blockplacecontext = updatePlacementContext(placeContext)
      if (blockplacecontext == null) {
        InteractionResult.FAIL
      } else {
        val blockstate = getPlacementState(blockplacecontext)
        if (blockstate == null) {
          InteractionResult.FAIL
        } else if (!placeBlock(blockplacecontext, blockstate)) {
          InteractionResult.FAIL
        } else {
          val blockpos = blockplacecontext.clickedPos
          val level = blockplacecontext.level
          val player = blockplacecontext.player
          val itemstack = blockplacecontext.itemInHand
          var blockstate1 = level.getBlockState(blockpos)
          if (blockstate1.`is`(blockstate.block)) {
            blockstate1 = updateBlockStateFromTag(blockpos, level, itemstack, blockstate1)
            this.updateCustomBlockEntityTag(blockpos, level, player, itemstack, blockstate1)
            blockstate1.block.setPlacedBy(level, blockpos, blockstate1, player, itemstack)
            if (player is ServerPlayer) {
              CriteriaTriggers.PLACED_BLOCK.trigger(player as ServerPlayer?, blockpos, itemstack)
            }
          }
          val soundtype = blockstate1.getSoundType(level, blockpos, placeContext.player)
          level.playSound(
            player,
            blockpos,
            this.getPlaceSound(blockstate1, level, blockpos, placeContext.player),
            SoundSource.BLOCKS, (soundtype.getVolume() + 1.0f) / 2.0f, soundtype.getPitch() * 0.8f
          )
          level.gameEvent(GameEvent.BLOCK_PLACE, blockpos, GameEvent.Context.of(player, blockstate1))
          if (player == null || !player.abilities.instabuild) {
            itemstack.shrink(1)
          }
          InteractionResult.sidedSuccess(level.isClientSide)
        }
      }
    }
  }

  private fun updateBlockStateFromTag(
    blockPos: BlockPos,
    level: Level,
    itemStack: ItemStack,
    originalState: BlockState
  ): BlockState {
    var blockstate: BlockState = originalState;
    val compoundtag: CompoundTag? = itemStack.tag;
    if (compoundtag != null) {
      val compoundtag1: CompoundTag = compoundtag.getCompound("BlockStateTag");
      val statedefinition: StateDefinition<Block, BlockState> = originalState.block.stateDefinition;

      for (s in compoundtag1.allKeys) {
        val property: Property<*>? = statedefinition.getProperty(s);
        if (property != null) {
          val s1: String = compoundtag1.get(s)?.asString ?: continue;
          blockstate = updateState(blockstate, property, s1);
        }
      }
    }

    if (blockstate !== originalState) {
      level.setBlock(blockPos, blockstate, 2)
    }

    return blockstate
  }

  private fun <T : Comparable<T>?> updateState(blockState: BlockState, property: Property<T>, s: String): BlockState {
    return property.getValue(s).map { t: T -> blockState.setValue(property, t) }.orElse(blockState)
  }


}