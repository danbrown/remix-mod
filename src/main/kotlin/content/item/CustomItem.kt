package com.dannbrown.remix.content.item

import com.dannbrown.remix.lib.LibUtils
import net.minecraft.ChatFormatting
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class CustomItem( ) : Item(Properties().durability(100)) {

  //  append a tooltip to the item
  override fun appendHoverText(
    stack: ItemStack,
    level: Level?,
    components: MutableList<Component>,
    tooltipFlag: TooltipFlag
  ) {
    if (Screen.hasShiftDown()) {
      components.add(Component.literal("This is a custom item!").withStyle(ChatFormatting.GRAY))
    } else {
      components.add(Component.literal("Hold SHIFT for more info").withStyle(ChatFormatting.YELLOW))
    }

    super.appendHoverText(stack, level, components, tooltipFlag)
  }

  //  override the use method to output a random number in the chat
  override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResultHolder<ItemStack> {
    if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
      // output a random number between 0 and 100 in the chat
      outputRandomNumber(player)
      // add a cooldown to prevent spam
      player.cooldowns.addCooldown(this, 20)

      // damage the item
      LibUtils.damageItem(this, 1)
    }
    return super.use(level, player, hand)
  }

  //  utility functions
  private fun outputRandomNumber(player: Player) {
    player.sendSystemMessage(Component.literal("Random number: " + getRandomNumber()))
  }

  private fun getRandomNumber(): Int {
    return (Math.random() * (100 - 0) + 0).toInt()
  }
}