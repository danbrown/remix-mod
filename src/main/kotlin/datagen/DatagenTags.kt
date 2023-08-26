package com.dannbrown.remix.datagen

import com.dannbrown.remix.lib.LibUtils.forgeResourceLocation
import com.dannbrown.remix.lib.LibUtils.resourceLocation
import net.minecraft.core.Registry
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.Item
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.material.Fluid

object DatagenTags {
  private fun <T> createTagKey(registry: ResourceKey<out Registry<T>?>, key: ResourceLocation): TagKey<T> {
    return TagKey.create(registry, key)
  }

  object Items {
    val RAW_MATERIALS_ADADMANTIUM = forgeTag("raw_materials/adamantium")
    val INGOTS_ADAMANTIUM = forgeTag("ingots/adamantium")
    val ORES_ADAMANTIUM = forgeTag("ores/adamantium")
    val STORAGE_BLOCKS_ADAMANTIUM = forgeTag("storage_blocks/adamantium")
    val STORAGE_BLOCKS_ADAMANTIUM_DEBRIS = forgeTag("storage_blocks/raw_adamantium")
    private fun tag(name: String): TagKey<Item> {
      return ItemTags.create(resourceLocation(name))
    }

    private fun forgeTag(name: String): TagKey<Item> {
      return ItemTags.create(forgeResourceLocation(name))
    }
  }

  object Blocks {
    val BASE_STONE_SAMPLE = tag("base_stone_sample")
    val DEPTHROCK_ORE_REPLACEABLES = tag("depthrock_ore_replaceables")
    val SHIVERSTONE_ORE_REPLACEABLES = tag("shiverstone_ore_replaceables")
    val TREMBLECRUST_ORE_REPLACEABLES = tag("tremblecrust_ore_replaceables")
    val BASALT_ORE_REPLACEABLES = tag("basalt_ore_replaceables")
    val DATABOX_CARVER_REPLACEABLES = tag("databox_carver_replaceables")

    // @ ORES
    val ORES_ADAMANTIUM = forgeTag("ores/adamantium")

    // @ Storage blocks
    val STORAGE_BLOCKS_ADAMANTIUM = forgeTag("storage_blocks/adamantium")
    val STORAGE_BLOCKS_ADAMANTIUM_DEBRIS = forgeTag("storage_blocks/adamantium_debris")
    private fun tag(name: String): TagKey<Block> {
      return BlockTags.create(resourceLocation(name))
    }

    private fun forgeTag(name: String): TagKey<Block> {
      return createTagKey(Registries.BLOCK, forgeResourceLocation(name))
    }
  }

  object Entities {
    val ROTSPAWN = tag("rotspawn")
    val CAVERN_CREATURE = tag("cavern_creature")
    val IMMUNE_TO_VIRULENT_MIX = tag("immune_to_virulent_mix")
    val IMMUNE_TO_SCINTLING_GOO = tag("immune_to_scintling_goo")
    private fun tag(name: String): TagKey<EntityType<*>> {
      return createTagKey(Registries.ENTITY_TYPE, resourceLocation(name))
    }
  }

  object Fluids {
    val VIRULENT = tag("virulent")
    private fun tag(name: String): TagKey<Fluid> {
      return createTagKey(Registries.FLUID, resourceLocation(name))
    }
  }

  object Biomes {
    val IS_DATABOX = tag("is_databox")
    val HAS_CATACOMBS = tag("has_structure/catacombs")
    private fun tag(name: String): TagKey<Biome> {
      return createTagKey(Registries.BIOME, resourceLocation(name))
    }

    private fun forgeTag(name: String): TagKey<Biome> {
      return createTagKey(Registries.BIOME, forgeResourceLocation(name))
    }
  }
}
