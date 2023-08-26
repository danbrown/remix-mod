package com.dannbrown.remix.datagen.provider

import com.dannbrown.remix.RemixMod
import com.dannbrown.remix.init.DatagenBlocks
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.*
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.CeilingHangingSignBlock
import net.minecraft.world.level.block.SignBlock
import net.minecraftforge.common.Tags
import net.minecraftforge.registries.ForgeRegistries
import java.util.function.Consumer
import java.util.function.Supplier

abstract class DatagenRecipeProvider(output: PackOutput?) : RecipeProvider(output) {
  fun makePlanks(plankOut: Supplier<out Block?>, logIn: TagKey<Item?>?): ShapelessRecipeBuilder {
    return ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, plankOut.get(), 4)
      .requires(logIn)
      .group("planks")
      .unlockedBy("has_log", has(logIn))
  }

  fun makeDoor(doorOut: Supplier<out Block?>, plankIn: Supplier<out Block?>): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, doorOut.get(), 3)
      .pattern("PP")
      .pattern("PP")
      .pattern("PP")
      .define('P', plankIn.get())
      .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(plankIn.get())!!.path, has(plankIn.get()))
  }

  fun makeTrapdoor(trapdoorOut: Supplier<out Block?>, plankIn: Supplier<out Block?>): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, trapdoorOut.get(), 2)
      .pattern("PPP")
      .pattern("PPP")
      .define('P', plankIn.get())
      .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(plankIn.get())!!.path, has(plankIn.get()))
  }

  fun makeButton(buttonOut: Supplier<out Block?>, blockIn: Supplier<out Block?>): ShapelessRecipeBuilder {
    return ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, buttonOut.get())
      .requires(blockIn.get())
      .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(blockIn.get())!!.path, has(blockIn.get()))
  }

  fun makePressurePlate(
    pressurePlateOut: Supplier<out Block?>,
    blockIn: Supplier<out Block?>
  ): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, pressurePlateOut.get())
      .pattern("BB")
      .define('B', blockIn.get())
      .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(blockIn.get())!!.path, has(blockIn.get()))
  }

  fun makeStairs(stairsOut: Supplier<out Block?>, blockIn: Supplier<out Block?>): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stairsOut.get(), 4)
      .pattern("M  ")
      .pattern("MM ")
      .pattern("MMM")
      .define('M', blockIn.get())
      .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(blockIn.get())!!.path, has(blockIn.get()))
  }

  fun makeSlab(slabOut: Supplier<out Block?>, blockIn: Supplier<out Block?>): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, slabOut.get(), 6)
      .pattern("MMM")
      .define('M', blockIn.get())
      .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(blockIn.get())!!.path, has(blockIn.get()))
  }

  fun makeWall(wallOut: Supplier<out Block?>, blockIn: Supplier<out Block?>): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, wallOut.get(), 6)
      .pattern("MMM")
      .pattern("MMM")
      .define('M', blockIn.get())
      .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(blockIn.get())!!.path, has(blockIn.get()))
  }

  fun makeFence(fenceOut: Supplier<out Block?>, blockIn: Supplier<out Block?>): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, fenceOut.get(), 6)
      .pattern("M/M")
      .pattern("M/M")
      .define('M', blockIn.get())
      .define('/', Tags.Items.RODS_WOODEN)
      .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(blockIn.get())!!.path, has(blockIn.get()))
  }

  fun makeFenceGate(fenceGateOut: Supplier<out Block?>, blockIn: Supplier<out Block?>): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, fenceGateOut.get())
      .pattern("/M/")
      .pattern("/M/")
      .define('M', blockIn.get())
      .define('/', Tags.Items.RODS_WOODEN)
      .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(blockIn.get())!!.path, has(blockIn.get()))
  }

  fun makeBricks(bricksOut: Supplier<out Block?>, blockIn: Supplier<out Block?>): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, bricksOut.get(), 4)
      .pattern("MM")
      .pattern("MM")
      .define('M', blockIn.get())
      .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(blockIn.get())!!.path, has(blockIn.get()))
  }

  fun makeChiseledBricks(
    bricksOut: Supplier<out Block?>,
    blockIn: Supplier<out Block?>
  ): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, bricksOut.get())
      .pattern("M")
      .pattern("M")
      .define('M', blockIn.get())
      .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(blockIn.get())!!.path, has(blockIn.get()))
  }

  fun makeWood(woodOut: Supplier<out Block?>, logIn: Supplier<out Block?>): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, woodOut.get(), 3)
      .pattern("MM")
      .pattern("MM")
      .define('M', logIn.get())
      .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(logIn.get())!!.path, has(logIn.get()))
  }

  fun makeIngotToBlock(blockOut: Supplier<out Block?>, ingotIn: Supplier<out Item?>): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockOut.get())
      .pattern("###")
      .pattern("###")
      .pattern("###")
      .define('#', ingotIn.get())
      .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(ingotIn.get())!!.path, has(ingotIn.get()))
  }

  fun makeBlockToIngot(ingotOut: Supplier<out Item?>, blockIn: Supplier<out Block?>): ShapelessRecipeBuilder {
    return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingotOut.get(), 9)
      .requires(blockIn.get())
      .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(blockIn.get())!!.path, has(blockIn.get()))
  }

  fun makeNuggetToIngot(ingotOut: Supplier<out Item?>, nuggetIn: Supplier<out Item?>): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ingotOut.get(), 1)
      .pattern("NNN")
      .pattern("NNN")
      .pattern("NNN")
      .define('N', nuggetIn.get())
      .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(nuggetIn.get())!!.path, has(nuggetIn.get()))
  }

  fun makeIngotToNugget(
    nuggetOut: Supplier<out Item?>,
    ingotIn: Supplier<out Item?>
  ): ShapelessRecipeBuilder {
    return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, nuggetOut.get(), 9)
      .requires(ingotIn.get())
      .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(ingotIn.get())!!.path, has(ingotIn.get()))
  }

  fun makeSword(swordOut: Supplier<out Item?>, materialIn: Supplier<out Item?>): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, swordOut.get())
      .pattern("#")
      .pattern("#")
      .pattern("/")
      .define('#', materialIn.get())
      .define('/', Tags.Items.RODS_WOODEN)
      .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(materialIn.get())!!.path, has(materialIn.get()))
  }

  fun makePickaxe(pickaxeOut: Supplier<out Item?>, materialIn: Supplier<out Item?>): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pickaxeOut.get())
      .pattern("###")
      .pattern(" / ")
      .pattern(" / ")
      .define('#', materialIn.get())
      .define('/', Tags.Items.RODS_WOODEN)
      .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(materialIn.get())!!.path, has(materialIn.get()))
  }

  fun makeAxe(axeOut: Supplier<out Item?>, materialIn: Supplier<out Item?>): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axeOut.get())
      .pattern("##")
      .pattern("#/")
      .pattern(" /")
      .define('#', materialIn.get())
      .define('/', Tags.Items.RODS_WOODEN)
      .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(materialIn.get())!!.path, has(materialIn.get()))
  }

  fun makeShovel(shovelOut: Supplier<out Item?>, materialIn: Supplier<out Item?>): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovelOut.get())
      .pattern("#")
      .pattern("/")
      .pattern("/")
      .define('#', materialIn.get())
      .define('/', Tags.Items.RODS_WOODEN)
      .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(materialIn.get())!!.path, has(materialIn.get()))
  }

  fun makeHoe(hoeOut: Supplier<out Item?>, materialIn: Supplier<out Item?>): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoeOut.get())
      .pattern("##")
      .pattern(" /")
      .pattern(" /")
      .define('#', materialIn.get())
      .define('/', Tags.Items.RODS_WOODEN)
      .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(materialIn.get())!!.path, has(materialIn.get()))
  }

  fun makeHelmet(helmetOut: Supplier<out Item?>, materialIn: Supplier<out Item?>): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmetOut.get())
      .pattern("MMM")
      .pattern("M M")
      .define('M', materialIn.get())
      .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(materialIn.get())!!.path, has(materialIn.get()))
  }

  fun makeChestplate(helmetOut: Supplier<out Item?>, materialIn: Supplier<out Item?>): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmetOut.get())
      .pattern("M M")
      .pattern("MMM")
      .pattern("MMM")
      .define('M', materialIn.get())
      .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(materialIn.get())!!.path, has(materialIn.get()))
  }

  fun makeLeggings(helmetOut: Supplier<out Item?>, materialIn: Supplier<out Item?>): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmetOut.get())
      .pattern("MMM")
      .pattern("M M")
      .pattern("M M")
      .define('M', materialIn.get())
      .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(materialIn.get())!!.path, has(materialIn.get()))
  }

  fun makeBoots(helmetOut: Supplier<out Item?>, materialIn: Supplier<out Item?>): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmetOut.get())
      .pattern("M M")
      .pattern("M M")
      .define('M', materialIn.get())
      .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(materialIn.get())!!.path, has(materialIn.get()))
  }

  fun makeStew(stewOut: Supplier<out Item?>, mushroomIn: Supplier<out Block?>): ShapelessRecipeBuilder {
    return ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, stewOut.get())
      .requires(Items.BOWL)
      .requires(mushroomIn.get(), 3)
      .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(mushroomIn.get())!!.path, has(mushroomIn.get()))
  }

  fun makeBoat(boatOut: Supplier<out Item?>, planksIn: Supplier<out Block?>): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, boatOut.get())
      .pattern("P P")
      .pattern("PPP")
      .define('P', planksIn.get())
      .group("boat")
      .unlockedBy("in_water", insideOf(Blocks.WATER))
  }

  fun makeChestBoat(chestBoatOut: Supplier<out Item?>, boatIn: Supplier<out Block?>): ShapelessRecipeBuilder {
    return ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, chestBoatOut.get())
      .requires(boatIn.get())
      .requires(Tags.Items.CHESTS_WOODEN)
      .group("chest_boat")
      .unlockedBy("has_boat", has(ItemTags.BOATS))
  }

  fun makeSign(signOut: Supplier<out SignBlock?>, planksIn: Supplier<out Block?>): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, signOut.get(), 3)
      .pattern("PPP")
      .pattern("PPP")
      .pattern(" / ")
      .define('P', planksIn.get())
      .define('/', Tags.Items.RODS_WOODEN)
      .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(planksIn.get())!!.path, has(planksIn.get()))
  }

  protected fun makeHangingSign(
    result: Supplier<out CeilingHangingSignBlock?>,
    log: Supplier<out Block?>
  ): ShapedRecipeBuilder {
    return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result.get(), 6)
      .pattern("| |")
      .pattern("###")
      .pattern("###")
      .define('#', log.get())
      .define('|', Items.CHAIN)
      .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(log.get())!!.path, has(log.get()))
  }

  fun ore(
    result: ItemLike, ingredients: List<ItemLike>, xp: Float, group: String,
    consumer: Consumer<FinishedRecipe>
  ) {
    oreSmeltingRecipe(result, ingredients, xp, group, consumer)
    oreBlastingRecipe(result, ingredients, xp, group, consumer)
  }

  private fun oreSmeltingRecipe(
    result: ItemLike, ingredients: List<ItemLike>, xp: Float, group: String,
    consumer: Consumer<FinishedRecipe>
  ) {
    for (ingredient in ingredients) {
      smeltingRecipe(result, ingredient, xp, 1).group(group).save(
        consumer, ResourceLocation(
          RemixMod.MOD_ID,
          "smelt_" + ForgeRegistries.ITEMS.getKey(ingredient.asItem())!!.path
        )
      )
    }
  }

  @JvmOverloads
  fun smeltingRecipe(result: ItemLike?, ingredient: ItemLike, exp: Float, count: Int = 1): SimpleCookingRecipeBuilder {
    return SimpleCookingRecipeBuilder
      .smelting(Ingredient.of(ItemStack(ingredient, count)), RecipeCategory.MISC, result, exp, 200)
      .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(ingredient.asItem()), has(ingredient))
  }

  @JvmOverloads
  fun smeltingRecipeTag(
    result: ItemLike?,
    ingredient: TagKey<Item?>,
    exp: Float,
    count: Int = 1
  ): SimpleCookingRecipeBuilder {
    return SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.MISC, result, exp, 200)
      .unlockedBy("has_$ingredient", has(ingredient))
  }

  private fun oreBlastingRecipe(
    result: ItemLike, ingredients: List<ItemLike>, xp: Float, group: String,
    consumer: Consumer<FinishedRecipe>
  ) {
    for (ingredient in ingredients) {
      blastingRecipe(result, ingredient, xp, 1).group(group).save(
        consumer, ResourceLocation(
          RemixMod.MOD_ID,
          "blast_" + ForgeRegistries.ITEMS.getKey(ingredient.asItem())!!.path
        )
      )
    }
  }

  @JvmOverloads
  fun blastingRecipe(result: ItemLike?, ingredient: ItemLike, exp: Float, count: Int = 1): SimpleCookingRecipeBuilder {
    return SimpleCookingRecipeBuilder
      .blasting(Ingredient.of(ItemStack(ingredient, count)), RecipeCategory.MISC, result, exp, 100)
      .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(ingredient.asItem()), has(ingredient))
  }

  @JvmOverloads
  fun blastingRecipeTag(
    result: ItemLike?,
    ingredient: TagKey<Item?>,
    exp: Float,
    count: Int = 1
  ): SimpleCookingRecipeBuilder {
    return SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient), RecipeCategory.MISC, result, exp, 100)
      .unlockedBy("has_$ingredient", has(ingredient))
  }

  @JvmOverloads
  fun smokingRecipe(result: ItemLike?, ingredient: ItemLike, exp: Float, count: Int = 1): SimpleCookingRecipeBuilder {
    return SimpleCookingRecipeBuilder
      .smoking(Ingredient.of(ItemStack(ingredient, count)), RecipeCategory.MISC, result, exp, 100)
      .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(ingredient.asItem()), has(ingredient))
  }

  fun smithingRecipe(
    input: Supplier<Item?>, upgradeItem: Supplier<Item?>,
    templateItem: Supplier<Item?>, result: Supplier<Item?>
  ): SmithingTransformRecipeBuilder {
    return SmithingTransformRecipeBuilder
      .smithing(
        Ingredient.of(templateItem.get()), Ingredient.of(input.get()), Ingredient.of(upgradeItem.get()),
        RecipeCategory.MISC, result.get()
      )
      .unlocks("has_" + ForgeRegistries.ITEMS.getKey(upgradeItem.get()), has(upgradeItem.get()))
  }

  fun stonecutting(input: Supplier<Block?>, result: ItemLike?): SingleItemRecipeBuilder {
    return SingleItemRecipeBuilder.stonecutting(Ingredient.of(input.get()), RecipeCategory.BUILDING_BLOCKS, result)
      .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(input.get()), has(input.get()))
  }

  fun stonecutting(input: Supplier<Block?>, result: ItemLike?, resultAmount: Int): SingleItemRecipeBuilder {
    return SingleItemRecipeBuilder
      .stonecutting(Ingredient.of(input.get()), RecipeCategory.BUILDING_BLOCKS, result, resultAmount)
      .unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(input.get()), has(input.get()))
  }

  fun adamantiumStonecutting(result: ItemLike?): SingleItemRecipeBuilder {
    return stonecutting(DatagenBlocks.ADAMANTIUM_BLOCK, result)
  }
}