package com.dannbrown.remix.init

import com.dannbrown.remix.RemixMod
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.CreativeModeTab.ItemDisplayParameters
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.item.EnchantedBookItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.EnchantmentInstance
import net.minecraft.world.level.block.Block
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.RegistryObject
import java.util.function.Supplier

@EventBusSubscriber(modid = RemixMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
object DatagenCreativeTabs {
  // @ Registering
  val TABS = DeferredRegister
    .create<CreativeModeTab>(Registries.CREATIVE_MODE_TAB, RemixMod.MOD_ID)

  fun register(bus: IEventBus?) {
    TABS.register(bus)
  }

  // Creative mode tab for databox items
  val DATABOX_TAB = TABS.register<CreativeModeTab>(
    "examplemod_group"
  ) {
    CreativeModeTab.builder()
      .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
      .title(Component.translatable("itemGroup.examplemod_group"))
      .icon(Supplier<ItemStack> { ItemStack(DatagenItems.ADAMANTIUM_INGOT.get()) })
      .displayItems { parameters: ItemDisplayParameters?, output: CreativeModeTab.Output ->

        // Block Items
        DatagenItems.BLOCK_ITEMS.getEntries().forEach { item ->
          val block =
            Block.byItem(item.get())
          val blockRegistry =
            DatagenBlocks.BLOCKS.entries
              .stream()
              .filter { entry: RegistryObject<Block> -> entry.get() === block }
              .findFirst()
              .orElse(null)
          if (blockRegistry != null && !DatagenBlocks.DONT_INCLUDE_CREATIVE.contains(blockRegistry)) {
            output.accept(ItemStack(item.get()))
          }
        }

        // Items
        DatagenItems.ITEMS.getEntries().forEach { item ->
          if (!DatagenItems.DONT_INCLUDE_CREATIVE.contains(item)) {
            output.accept(item.get())
          }
        }

        // Enchanted books
        DatagenEnchantments.ENCHANTMENTS.getEntries().forEach { enchantment ->
          if (!DatagenEnchantments.DONT_INCLUDE_CREATIVE.contains(enchantment)) {
            output.accept(
              EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(enchantment.get(), enchantment.get().getMaxLevel())
              )
            )
          }
        }
      }.build()
  }

  // This will register items into the vanilla creative tabs
  @SubscribeEvent
  fun addVanillaCreativeTabs(event: BuildCreativeModeTabContentsEvent) {
    if (event.tabKey === CreativeModeTabs.BUILDING_BLOCKS) {
      event.accept(DatagenItems.ADAMANTIUM_INGOT)
    }
  }
}
