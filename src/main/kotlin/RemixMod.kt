package com.dannbrown.remix

import com.dannbrown.remix.datagen.DatagenRegistries
import com.dannbrown.remix.init.*
import net.minecraftforge.event.server.ServerStartingEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.MOD_BUS


@Mod(RemixMod.MOD_ID)
class RemixMod {
  companion object {
    const val MOD_ID = "remix"
    private val LOGGER: Logger = LogManager.getLogger(MOD_ID)

    init {
      LOGGER.log(Level.INFO, "$MOD_ID has started!")

      MOD_BUS.addListener(this::commonSetup)

      // run register methods
      DatagenCreativeTabs.register(MOD_BUS)
      DatagenBlocks.register(MOD_BUS)
      DatagenItems.register(MOD_BUS)
      DatagenEnchantments.register(MOD_BUS)
      DatagenSoundEvents.register(MOD_BUS)
      DatagenCarvers.register(MOD_BUS)
      DatagenEntities.register(MOD_BUS)

      // run entity renderer registration
      MOD_BUS.addListener(DatagenEntities::registerEntityRenderers)

      // add items to vanilla creative tabs
      MOD_BUS.addListener(DatagenCreativeTabs::addVanillaCreativeTabs)

      // gather datapack data
      MOD_BUS.addListener(DatagenRegistries::registerDataProviders)
    }

    private fun commonSetup(event: FMLCommonSetupEvent) {}


    // use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    fun onServerStarting(event: ServerStartingEvent) {
    }

  }
}
