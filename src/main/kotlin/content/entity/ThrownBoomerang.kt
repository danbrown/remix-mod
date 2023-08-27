package com.dannbrown.remix.content.entity

import com.dannbrown.remix.init.DatagenEntities
import com.dannbrown.remix.init.DatagenItems
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.projectile.AbstractArrow
import net.minecraft.world.entity.projectile.ItemSupplier
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.Vec3
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.registries.ForgeRegistries


class ThrownBoomerang(
  level: Level? = null,
  livingEntity: LivingEntity? = null,
  itemStack: ItemStack? = ItemStack(DatagenItems.BOOMERANG.get()),
) : AbstractArrow(
  DatagenEntities.BOOMERANG.get(),
//  EntityType.ARROW,
  livingEntity,
  level,
), ItemSupplier {

  companion object {
    private val ITEM_STACK: EntityDataAccessor<ItemStack> = SynchedEntityData.defineId(
      ThrownBoomerang::class.java, EntityDataSerializers.ITEM_STACK
    )
  }

  override fun defineSynchedData() {
    super.defineSynchedData()
    this.entityData.define(ITEM_STACK, ItemStack.EMPTY)
  }

  private var boomerangItem: ItemStack = ItemStack(DatagenItems.BOOMERANG.get())
  private var hitEntity: Boolean = false
  private var returnSpeed: Int = 3
  private var clientSideReturnTickCount: Int = 0

  init {
    if (itemStack != null) {
      boomerangItem = itemStack.copy()
    }
  }

  @OnlyIn(Dist.CLIENT)
  override fun getItem(): ItemStack {
    return ItemStack(DatagenItems.BOOMERANG.get())
  }

  override fun getPickupItem(): ItemStack {
    return boomerangItem.copy()
  }


  override fun tick() {
    if (inGroundTime > 4) {
      hitEntity = true
    }

    val owner = this.owner

    if ((hitEntity || this.isNoGravity) && owner != null && this != null) {
      if (!this.isAcceptibleReturnOwner()) {

        // drop if can't pickup as item
        if (this.level().isClientSide && this.pickup == AbstractArrow.Pickup.ALLOWED) {
          this.spawnAtLocation(this.getPickupItem(), 0.1f)
        }
        // discard if can't drop
        this.discard()
      } else {
        this.isNoPhysics = true
        val vec3: Vec3 = owner.getEyePosition().subtract(position())

        setPosRaw(this.x, this.y + vec3.y * 0.015 * returnSpeed.toDouble(), this.z)
        if (level().isClientSide) {
          yOld = this.y
        }

        val d0: Double = 0.05 * returnSpeed.toDouble()
        deltaMovement = deltaMovement.scale(0.95).add(vec3.normalize().scale(d0))
        if (this.clientSideReturnTickCount == 0) {
          this.playSound(SoundEvents.TRIDENT_RETURN, 10.0f, 1.0f)
        }

        ++this.clientSideReturnTickCount
      }
    }

    super.tick()
  }


  private fun isAcceptibleReturnOwner(): Boolean {
    val owner = this.owner
    return if (owner != null && owner.isAlive) {
      owner !is ServerPlayer || !owner.isSpectator()
    } else {
      false
    }
  }

  override fun onHitEntity(hitResult: EntityHitResult) {
    super.onHitEntity(hitResult)

    var target: Entity = hitResult.entity
    var owner: Entity? = this.owner

    println(target)

    if (owner != null) {
      // if hits a dropped item, bring it back to the player
      if (target is ItemEntity) {

        // log the item
        println("hit item entity")
        println(target)

        if (!level().isClientSide) {
          //            target.remove(Entity.RemovalReason.DISCARDED)
        }
        target.setDeltaMovement(Vec3.ZERO)
        target.setPosRaw(owner.x, owner.y, owner.z)
        target.yRot = owner.yRot

      }
    }
  }
}