package com.dannbrown.remix.init

import com.dannbrown.remix.RemixMod
import com.example.examplemod.content.carver.CustomCaveWorldCarver
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration
import net.minecraft.world.level.levelgen.carver.WorldCarver
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import java.util.function.Supplier

object DatagenCarvers {
  val CARVERS = DeferredRegister.create<WorldCarver<*>>(
    ForgeRegistries.WORLD_CARVERS,
    RemixMod.MOD_ID
  )
  val SAMPLE_CAVE = CARVERS.register<WorldCarver<CaveCarverConfiguration>>(
    "sample_cave",
    Supplier<WorldCarver<CaveCarverConfiguration>> { CustomCaveWorldCarver(CaveCarverConfiguration.CODEC) })

  fun register(eventBus: IEventBus?) {
    CARVERS.register(eventBus)
  }
}
