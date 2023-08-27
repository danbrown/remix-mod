package com.dannbrown.remix.init

import com.dannbrown.remix.RemixMod
import com.dannbrown.remix.content.entity.ThrownBoomerang
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.ThrownItemRenderer
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject


class DatagenEntities {
  companion object {
    // @ Registering
    val ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RemixMod.MOD_ID)

    fun <T : Entity?> registerEntity(name: String, builder: EntityType.Builder<T>): RegistryObject<EntityType<T>> {
      return ENTITY_TYPES.register(name) { builder.build(RemixMod.MOD_ID + ":" + name) }
    }

    fun register(bus: IEventBus) {
      ENTITY_TYPES.register(bus)
    }

    val BOOMERANG = ENTITY_TYPES.register("boomerang") {
      EntityType.Builder.of({ _, _ -> ThrownBoomerang() }, MobCategory.MISC)
        .sized(0.5F, 0.5F)
        .clientTrackingRange(4)
        .updateInterval(20)
        .build("boomerang")
    }


    // @ Event Renderer
    fun registerEntityRenderers(event: RegisterRenderers) {
      event.registerEntityRenderer(BOOMERANG.get()) { c: EntityRendererProvider.Context? -> ThrownItemRenderer(c) }
    }
  }
}

