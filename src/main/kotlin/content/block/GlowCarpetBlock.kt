package com.dannbrown.remix.content.block

import net.minecraft.world.item.DyeColor
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.WoolCarpetBlock
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument
import net.minecraft.world.level.material.MapColor

class GlowCarpetBlock(mapColor: MapColor = MapColor.SNOW, dyeColor: DyeColor = DyeColor.WHITE) : WoolCarpetBlock(
  dyeColor,
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