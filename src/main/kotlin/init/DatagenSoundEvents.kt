package com.dannbrown.remix.init

import com.dannbrown.remix.lib.LibUtils.resourceLocation
import com.dannbrown.remix.RemixMod
import net.minecraft.sounds.SoundEvent
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject

object DatagenSoundEvents {
  // @ Register
  val SOUNDS = DeferredRegister.create<SoundEvent>(
    ForgeRegistries.SOUND_EVENTS,
    RemixMod.MOD_ID
  )

  fun register(bus: IEventBus?) {
    SOUNDS.register(bus)
  }

  // @ Sounds
  val CORE_BLOCK_BREAK = registerSound("block.core_block.break")

  // @ Utils
  private fun registerSound(name: String): RegistryObject<SoundEvent> {
    return SOUNDS.register(name) {
      SoundEvent.createVariableRangeEvent(
        resourceLocation(name)
      )
    }
  }
}
