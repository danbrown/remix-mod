package com.dannbrown.remix.datagen.provider

import com.dannbrown.remix.RemixMod
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.*
import net.minecraftforge.client.model.generators.ItemModelBuilder
import net.minecraftforge.client.model.generators.ItemModelProvider
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.registries.ForgeRegistries
import java.util.function.Supplier

abstract class DatagenItemModelProvider(output: PackOutput?, fileHelper: ExistingFileHelper?) :
  ItemModelProvider(output, RemixMod.MOD_ID, fileHelper) {
  private fun blockName(block: Supplier<out Block?>): String {
    return ForgeRegistries.BLOCKS.getKey(block.get())!!.path
  }

  private fun texture(name: String): ResourceLocation {
    return modLoc("block/$name")
  }

  fun itemFence(block: Supplier<out Block?>, name: String) {
    withExistingParent(blockName(block), mcLoc("block/fence_inventory"))
      .texture("texture", "block/$name")
  }

  fun blockLamp(block: Supplier<out Block?>): ItemModelBuilder {
    val offName = blockName(block) + "_off"
    val onName = blockName(block) + "_on"
    val offVariant = withExistingParent(blockName(block), modLoc("block/$offName"))
    return withExistingParent(blockName(block), modLoc("block/$onName"))
  }

  @JvmOverloads
  fun block(block: Supplier<out Block?>, name: String = blockName(block)): ItemModelBuilder {
    return withExistingParent(blockName(block), modLoc("block/$name"))
  }

  fun blockFlat(block: Supplier<out Block?>) {
    withExistingParent(blockName(block), mcLoc("item/generated"))
      .texture("layer0", modLoc("block/" + blockName(block)))
  }

  fun blockFlatWithBlockTexture(block: Supplier<out Block?>, name: String) {
    withExistingParent(blockName(block), mcLoc("item/generated"))
      .texture("layer0", modLoc("block/$name"))
      .renderType("translucent")
  }

  fun blockFlatWithItemTexture(block: Supplier<out Block?>, name: String) {
    withExistingParent(blockName(block), mcLoc("item/generated"))
      .texture("layer0", modLoc("item/$name"))
  }

  fun normalItem(item: Supplier<out Item?>) {
    withExistingParent(ForgeRegistries.ITEMS.getKey(item.get())!!.path, mcLoc("item/generated"))
      .texture("layer0", modLoc("item/" + ForgeRegistries.ITEMS.getKey(item.get())!!.path))
  }

  fun torchItem(item: Supplier<out Block?>) {
    withExistingParent(ForgeRegistries.BLOCKS.getKey(item.get())!!.path, mcLoc("item/generated"))
      .texture("layer0", modLoc("block/" + ForgeRegistries.BLOCKS.getKey(item.get())!!.path))
  }

  fun toolItem(item: Supplier<out Item?>) {
    withExistingParent(ForgeRegistries.ITEMS.getKey(item.get())!!.path, mcLoc("item/handheld"))
      .texture("layer0", modLoc("item/" + ForgeRegistries.ITEMS.getKey(item.get())!!.path))
  }

  fun rodItem(item: Supplier<out Item?>) {
    withExistingParent(ForgeRegistries.ITEMS.getKey(item.get())!!.path, mcLoc("item/handheld_rod"))
      .texture("layer0", modLoc("item/" + ForgeRegistries.ITEMS.getKey(item.get())!!.path))
  }

  fun egg(item: Supplier<out Item?>) {
    withExistingParent(ForgeRegistries.ITEMS.getKey(item.get())!!.path, mcLoc("item/template_spawn_egg"))
  }

  fun sign(sign: Supplier<out SignBlock?>) {
    withExistingParent(blockName(sign), mcLoc("item/generated"))
      .texture("layer0", modLoc("item/" + blockName(sign)))
  }

  fun wall(wall: Supplier<out WallBlock?>, fullBlock: Supplier<out Block?>): ItemModelBuilder {
    return wallInventory(ForgeRegistries.BLOCKS.getKey(wall.get())!!.path, texture(blockName(fullBlock)))
  }

  fun button(button: Supplier<out ButtonBlock?>, fullBlock: Supplier<out Block?>): ItemModelBuilder {
    return buttonInventory(ForgeRegistries.BLOCKS.getKey(button.get())!!.path, texture(blockName(fullBlock)))
  }

  fun trapdoor(trapdoor: Supplier<out TrapDoorBlock?>) {
    withExistingParent(
      ForgeRegistries.BLOCKS.getKey(trapdoor.get())!!.path,
      ResourceLocation(RemixMod.MOD_ID, "block/" + blockName(trapdoor) + "_bottom")
    )
  }
}