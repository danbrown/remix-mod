package com.dannbrown.remix.content.enchantment

import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraft.world.item.enchantment.Enchantments
import java.util.List


class BlinkingStrikeEnchantment(vararg slots: EquipmentSlot?) :
  Enchantment(Rarity.RARE, EnchantmentCategory.TRIDENT, slots) {

  override fun checkCompatibility(ench: Enchantment): Boolean {
    // allow enchantment to be combined with other enchantments, except for itself,
    // and riptide
    return (this !== ench
            && !List.of(Enchantments.RIPTIDE).contains(ench))
  }

  override fun canApplyAtEnchantingTable(stack: ItemStack): Boolean {
    // allow enchantment to be applied only to tridents and if it's not enchanted
    return stack.item is Item && stack.item === Items.TRIDENT && !stack.isEnchanted
  }

  override fun canEnchant(stack: ItemStack): Boolean {
    // allow enchantment to be applied only to tridents
    return stack.item is Item && stack.item === Items.TRIDENT
  }

  override fun isTreasureOnly(): Boolean {
    return true
  }
}

