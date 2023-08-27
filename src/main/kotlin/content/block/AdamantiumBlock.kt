package com.dannbrown.remix.content.block

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SoundType

class AdamantiumBlock : Block(
  Properties.copy(Blocks.NETHERITE_BLOCK)
    .sound(SoundType.NETHERITE_BLOCK)
) {
}