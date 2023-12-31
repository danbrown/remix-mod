package com.dannbrown.remix.datagen.provider

import com.dannbrown.remix.RemixMod
import com.google.common.collect.ImmutableMap
import com.google.common.collect.Maps
import net.minecraft.Util
import net.minecraft.core.Direction
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraftforge.client.model.generators.*
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.registries.ForgeRegistries
import java.util.*
import java.util.function.Consumer
import java.util.function.Supplier

abstract class DatagenBlockstateProvider(output: PackOutput?, helper: ExistingFileHelper?) :
  BlockStateProvider(output, RemixMod.MOD_ID, helper) {
  protected fun texture(name: String): ResourceLocation {
    return modLoc("block/$name")
  }

  protected fun name(block: Supplier<out Block?>): String {
    return ForgeRegistries.BLOCKS.getKey(block.get())!!.path
  }

  fun block(block: Supplier<out Block?>) {
    simpleBlock(block.get())
  }

  fun blockVanilla(block: Supplier<out Block?>, vanillaName: String) {
    simpleBlock(block.get(), models().cubeAll(name(block), ResourceLocation("minecraft", "block/$vanillaName")))
  }

  fun carpetVanilla(block: Supplier<out Block?>, vanillaName: String) {
    simpleBlock(block.get(), models().carpet(name(block), ResourceLocation("minecraft", "block/$vanillaName")))
  }


  fun blockLamp(block: Supplier<out Block?>) {
    getVariantBuilder(block.get()).forAllStates { state: BlockState ->
      val lit = state.getValue(BlockStateProperties.LIT)
      ConfiguredModel.builder()
        .modelFile(
          models().cubeAll(
            name(block) + if (lit) "_on" else "_off",
            texture(name(block) + if (lit) "_on" else "_off")
          )
        )
        .build()
    }
  }


  fun multifaceBlock(block: Supplier<out Block?>, model: ModelFile) {
    val builder = getMultipartBuilder(block.get())
      // north
      .part().modelFile(model)
      .addModel()
      .condition(BlockStateProperties.NORTH, true)
      .end()
      // east
      .part().modelFile(model)
      .addModel()
      .condition(BlockStateProperties.DOWN, false)
      .condition(BlockStateProperties.EAST, false)
      .condition(BlockStateProperties.NORTH, false)
      .condition(BlockStateProperties.SOUTH, false)
      .condition(BlockStateProperties.UP, false)
      .condition(BlockStateProperties.WEST, false)
      .end()
      // east
      .part().modelFile(model)
      .rotationY(90)
      .uvLock(true)
      .addModel()
      .condition(BlockStateProperties.EAST, true)
      .end()
      // east
      .part().modelFile(model)
      .rotationY(90)
      .uvLock(true)
      .addModel()
      .condition(BlockStateProperties.DOWN, false)
      .condition(BlockStateProperties.EAST, false)
      .condition(BlockStateProperties.NORTH, false)
      .condition(BlockStateProperties.SOUTH, false)
      .condition(BlockStateProperties.UP, false)
      .condition(BlockStateProperties.WEST, false)
      .end()
      // south
      .part().modelFile(model)
      .rotationY(180)
      .uvLock(true)
      .addModel()
      .condition(BlockStateProperties.SOUTH, true)
      .end()
      // south
      .part().modelFile(model)
      .rotationY(180)
      .uvLock(true)
      .addModel()
      .condition(BlockStateProperties.DOWN, false)
      .condition(BlockStateProperties.EAST, false)
      .condition(BlockStateProperties.NORTH, false)
      .condition(BlockStateProperties.SOUTH, false)
      .condition(BlockStateProperties.UP, false)
      .condition(BlockStateProperties.WEST, false)
      .end()
      // west
      .part().modelFile(model)
      .rotationY(270)
      .uvLock(true)
      .addModel()
      .condition(BlockStateProperties.WEST, true)
      .end()
      // west
      .part().modelFile(model)
      .rotationY(270)
      .uvLock(true)
      .addModel()
      .condition(BlockStateProperties.DOWN, false)
      .condition(BlockStateProperties.EAST, false)
      .condition(BlockStateProperties.NORTH, false)
      .condition(BlockStateProperties.SOUTH, false)
      .condition(BlockStateProperties.UP, false)
      .condition(BlockStateProperties.WEST, false)
      .end()
      // up
      .part().modelFile(model)
      .rotationX(270)
      .uvLock(true)
      .addModel()
      .condition(BlockStateProperties.UP, true)
      .end()
      // up
      .part().modelFile(model)
      .rotationX(270)
      .uvLock(true)
      .addModel()
      .condition(BlockStateProperties.DOWN, false)
      .condition(BlockStateProperties.EAST, false)
      .condition(BlockStateProperties.NORTH, false)
      .condition(BlockStateProperties.SOUTH, false)
      .condition(BlockStateProperties.UP, false)
      .condition(BlockStateProperties.WEST, false)
      .end()
      // down
      .part().modelFile(model)
      .rotationX(90)
      .uvLock(true)
      .addModel()
      .condition(BlockStateProperties.DOWN, true)
      .end()
      // down
      .part().modelFile(model)
      .rotationX(90)
      .uvLock(true)
      .addModel()
      .condition(BlockStateProperties.DOWN, false)
      .condition(BlockStateProperties.EAST, false)
      .condition(BlockStateProperties.NORTH, false)
      .condition(BlockStateProperties.SOUTH, false)
      .condition(BlockStateProperties.UP, false)
      .condition(BlockStateProperties.WEST, false)
      .end()
  }

  fun lichen(block: Supplier<out Block?>) {
    val lmodel: ModelFile = models().getBuilder(name(block))
      // texture
      .texture("particle", modLoc("block/${name(block)}"))
      .texture(name(block), modLoc("block/${name(block)}"))
      // texture elements
      .element()
      .from(0f, 0f, 0.1f)
      .to(16f, 16f, 0.1f)
      .face(Direction.NORTH).uvs(16f, 0f, 0f, 16f).texture("#${name(block)}").end()
      .face(Direction.SOUTH).uvs(0f, 0f, 16f, 16f).texture("#${name(block)}").end()
      .end()
      // transparent elements
      .renderType("translucent")

    multifaceBlock(block, lmodel)
  }


  fun blockTranslucent(block: Supplier<out Block?>) {
    simpleBlock(block.get(), models().cubeAll(name(block), blockTexture(block.get())).renderType("translucent"))
  }

  fun log(block: Supplier<out RotatedPillarBlock?>, name: String) {
    axisBlock(block.get(), texture(name))
  }

  private fun crossBlock(block: Supplier<out Block?>, model: ModelFile) {
    getVariantBuilder(block.get()).forAllStates { state: BlockState? ->
      ConfiguredModel.builder()
        .modelFile(model)
        .build()
    }
  }

  fun torchBlock(block: Supplier<out Block?>, wall: Supplier<out Block?>) {
    val torch: ModelFile = models().torch(name(block), texture(name(block))).renderType("cutout")
    val torchwall: ModelFile = models().torchWall(name(wall), texture(name(block))).renderType("cutout")
    simpleBlock(block.get(), torch)
    getVariantBuilder(wall.get()).forAllStates { state: BlockState ->
      ConfiguredModel.builder()
        .modelFile(torchwall)
        .rotationY(
          (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot()
            .toInt() + 90) % 360
        )
        .build()
    }
  }

  fun crossBlock(block: Supplier<out Block?>) {
    crossBlock(block, models().cross(name(block), texture(name(block))).renderType("cutout"))
  }

  fun tintedCrossBlock(block: Supplier<out Block?>) {
    crossBlock(
      block, models().withExistingParent(name(block), mcLoc("block/tinted_cross"))
        .texture("cross", texture(name(block))).renderType("cutout")
    )
  }

  fun stairs(block: Supplier<out StairBlock?>, fullBlock: Supplier<out Block?>) {
    stairsBlock(block.get(), texture(name(fullBlock)))
  }

  fun slab(block: Supplier<out SlabBlock?>, fullBlock: Supplier<out Block?>) {
    slabBlock(block.get(), texture(name(fullBlock)), texture(name(fullBlock)))
  }

  fun wall(wall: Supplier<out WallBlock?>, fullBlock: Supplier<out Block?>) {
    wallBlock(wall.get(), texture(name(fullBlock)))
  }

  fun fence(block: Supplier<out FenceBlock?>, fullBlock: Supplier<out Block?>) {
    fenceBlock(block.get(), texture(name(fullBlock)))
    fenceColumn(block, name(fullBlock))
  }

  private fun fenceColumn(block: Supplier<out FenceBlock?>, name: String) {
    val baseName = name(block)
    fourWayBlock(
      block.get(),
      models().fencePost(baseName + "_post", texture(name)),
      models().fenceSide(baseName + "_side", texture(name))
    )
  }

  fun fenceGate(block: Supplier<out FenceGateBlock?>, fullBlock: Supplier<out Block?>) {
    fenceGateBlock(block.get(), texture(name(fullBlock)))
  }

  fun door(block: Supplier<out DoorBlock?>, name: String) {
    doorBlockWithRenderType(
      block.get(), name(block), texture(name + "_door_bottom"), texture(name + "_door_top"),
      "cutout"
    )
  }

  fun trapdoor(block: Supplier<out TrapDoorBlock?>, name: String) {
    trapdoorBlockWithRenderType(block.get(), texture(name + "_trapdoor"), true, "cutout")
  }

  fun carpet(block: Supplier<out WoolCarpetBlock?>) {
    simpleBlock(block.get(), models().carpet(name(block), texture(name(block))))
  }

  fun button(block: Supplier<out ButtonBlock?>, fullBlock: Supplier<out Block?>) {
    buttonBlock(block.get(), texture(name(fullBlock)))
  }

  fun pressurePlate(block: Supplier<out PressurePlateBlock?>, fullBlock: Supplier<out Block?>) {
    pressurePlateBlock(block.get(), texture(name(fullBlock)))
  }

  fun sign(
    standingBlock: Supplier<out StandingSignBlock?>, wallBlock: Supplier<out WallSignBlock?>,
    name: String
  ) {
    signBlock(standingBlock.get(), wallBlock.get(), modLoc("block/$name"))
  }

  fun hangingSign(
    standingBlock: Supplier<out CeilingHangingSignBlock?>,
    wallBlock: Supplier<out WallHangingSignBlock?>, name: String
  ) {
    val model: ModelFile = models().getBuilder(name(standingBlock)).texture("particle", modLoc("block/$name"))
    simpleBlock(standingBlock.get(), model)
    simpleBlock(wallBlock.get(), model)
  }
}