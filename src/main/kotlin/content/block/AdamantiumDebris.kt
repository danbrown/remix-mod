package com.dannbrown.remix.content.block

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SoundType

class AdamantiumDebris : Block(
  Properties.copy(Blocks.ANCIENT_DEBRIS)
    .sound(SoundType.ANCIENT_DEBRIS)
) {
}