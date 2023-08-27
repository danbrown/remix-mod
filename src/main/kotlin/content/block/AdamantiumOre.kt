package com.dannbrown.remix.content.block

import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.DropExperienceBlock
import net.minecraft.world.level.block.SoundType

class AdamantiumOre : DropExperienceBlock(
  Properties.copy(Blocks.DIAMOND_ORE)
    .sound(SoundType.BASALT)
) {
}