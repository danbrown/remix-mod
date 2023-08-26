package com.dannbrown.remix.init

import com.dannbrown.remix.lib.LibEnchantmentNames
import com.dannbrown.remix.RemixMod
import com.dannbrown.remix.content.enchantment.BlinkingStrikeEnchantment
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import java.util.function.Supplier

object DatagenEnchantments {
  // @ Register
  val ENCHANTMENTS = DeferredRegister.create<Enchantment>(
    ForgeRegistries.ENCHANTMENTS,
    RemixMod.MOD_ID
  )

  fun register(bus: IEventBus?) {
    ENCHANTMENTS.register(bus)
  }

  // @ Enchantments
  val BLINKING_STRIKE = ENCHANTMENTS
    .register(LibEnchantmentNames.BLINKING_STRIKE) { BlinkingStrikeEnchantment() }

  // @ Blacklist for creative tab
  val DONT_INCLUDE_CREATIVE = listOf<RegistryObject<Enchantment>>()
}
