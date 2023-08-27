package com.dannbrown.remix.content.item

import com.dannbrown.remix.content.entity.ThrownBoomerang
import net.minecraft.core.BlockPos
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.util.Mth
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.MoverType
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.AbstractArrow
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.UseAnim
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.Vec3

class BoomerangItem( ) : Item(Properties().durability(100)) {

  override fun canAttackBlock(blockState: BlockState, level: Level, blockPos: BlockPos, player: Player): Boolean {
    return !player.isCreative
  }

  override fun getUseAnimation(itemStack: ItemStack): UseAnim {
    return UseAnim.SPEAR
  }

  override fun getUseDuration(itemStack: ItemStack): Int {
    return 72000
  }



  override fun releaseUsing(itemStack: ItemStack, level: Level, entity: LivingEntity, timeLeft: Int){
    if (entity is Player) {
      val i = getUseDuration(itemStack) - timeLeft
      if (i >= 10) {
        val j = EnchantmentHelper.getRiptide(itemStack)
        if (j <= 0 || entity.isInWaterOrRain()) {
          if (!level.isClientSide) {
            itemStack.hurtAndBreak(
              1, entity
            ) { p_43388_: Player ->
              p_43388_.broadcastBreakEvent(
                entity.getUsedItemHand()
              )
            }
            if (j == 0) {
              val throwntrident = ThrownBoomerang(level, entity, itemStack)
              throwntrident.shootFromRotation(
                entity,
                entity.getXRot(),
                entity.getYRot(),
                0.0f,
                2.5f + j.toFloat() * 0.5f,
                1.0f
              )
              if (entity.abilities.instabuild) {
                throwntrident.pickup = AbstractArrow.Pickup.CREATIVE_ONLY
              }
              level.addFreshEntity(throwntrident)
              level.playSound(
                null as Player?,
                throwntrident,
                SoundEvents.TRIDENT_THROW,
                SoundSource.PLAYERS,
                1.0f,
                1.0f
              )
              if (!entity.abilities.instabuild) {
                entity.inventory.removeItem(itemStack)
              }
            }
          }

          entity.awardStat(Stats.ITEM_USED[this])

          if (j > 0) {
            val f7 = entity.getYRot()
            val f = entity.getXRot()
            var f1 = -Mth.sin(f7 * (Math.PI.toFloat() / 180f)) * Mth.cos(f * (Math.PI.toFloat() / 180f))
            var f2 = -Mth.sin(f * (Math.PI.toFloat() / 180f))
            var f3 = Mth.cos(f7 * (Math.PI.toFloat() / 180f)) * Mth.cos(f * (Math.PI.toFloat() / 180f))
            val f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3)
            val f5 = 3.0f * ((1.0f + j.toFloat()) / 4.0f)
            f1 *= f5 / f4
            f2 *= f5 / f4
            f3 *= f5 / f4
            entity.push(f1.toDouble(), f2.toDouble(), f3.toDouble())
            entity.startAutoSpinAttack(20)
            if (entity.onGround()) {
              val f6 = 1.1999999f
              entity.move(MoverType.SELF, Vec3(0.0, 1.1999999, 0.0))
            }
            val soundevent: SoundEvent
            soundevent = if (j >= 3) {
              SoundEvents.TRIDENT_RIPTIDE_3
            } else if (j == 2) {
              SoundEvents.TRIDENT_RIPTIDE_2
            } else {
              SoundEvents.TRIDENT_RIPTIDE_1
            }
            level.playSound(null as Player?, entity, soundevent, SoundSource.PLAYERS, 1.0f, 1.0f)
          }
        }
      }
    }

  }

  override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResultHolder<ItemStack> {
    val itemstack = player.getItemInHand(hand)
    return if (itemstack.damageValue >= itemstack.maxDamage - 1) {
      InteractionResultHolder.fail(itemstack)
    } else if (EnchantmentHelper.getRiptide(itemstack) > 0 && !player.isInWaterOrRain) {
      InteractionResultHolder.fail(itemstack)
    } else {
      player.startUsingItem(hand)
      InteractionResultHolder.consume(itemstack)
    }
  }
}