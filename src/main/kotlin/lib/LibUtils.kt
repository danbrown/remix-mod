package com.dannbrown.remix.lib

import com.dannbrown.remix.RemixMod
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraftforge.versions.forge.ForgeVersion

object LibUtils {
  fun resourceLocation(path: String): ResourceLocation {
    return ResourceLocation(RemixMod.MOD_ID, path)
  }

  fun forgeResourceLocation(path: String): ResourceLocation {
    return ResourceLocation(ForgeVersion.MOD_ID, path)
  }

  fun defaultItemProps(): Item.Properties {
    return Item.Properties()
  }

  fun damageItem(item: Item, amount: Int) {
    item.defaultInstance.hurtAndBreak(amount, null, null)
  }
}