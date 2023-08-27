package com.dannbrown.remix.content.block

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument
import net.minecraft.world.level.material.MapColor

class GlowWoolBlock(mapColor: MapColor = MapColor.SNOW) : Block(
  Properties.of()
    .mapColor(mapColor)
    .instrument(NoteBlockInstrument.GUITAR)
    .strength(0.8F)
    .sound(SoundType.WOOL)
    .ignitedByLava()
    .lightLevel { 1 }
    .hasPostProcess { _, _, _ -> true }
    .emissiveRendering { _, _, _ -> true }
) {
}