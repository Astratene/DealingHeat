package org.teamvoided.dealing.heat.entity

import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.arrow.AbstractArrow
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult
import org.teamvoided.dealing.heat.init.DHEntities.FIRE_BALL
import kotlin.Any

class FireBallEntity : AbstractArrow {

    constructor(entityType: EntityType<out FireBallEntity>, world: Level) : super(entityType, world)

    constructor(world: Level, owner: LivingEntity) : super(
        FIRE_BALL, owner, world, Items.ARROW.defaultInstance, Items.STONE.defaultInstance
    )

    constructor(x: Double, y: Double, z: Double, world: Level) : super(
        FIRE_BALL, x, y, z, world, Items.ARROW.defaultInstance, Items.STONE.defaultInstance
    )

    var directDamage = 0f
    var indirectDamage = 0f
    var explosionRadius = 0f
    var isHot = false
    var hasBabies = false
    var lifespan = 0

    override fun onHitEntity(entityHitResult: EntityHitResult) {
        super.onHitEntity(entityHitResult)
        val entity = entityHitResult.entity

        explode()
        discard()
    }

    override fun onHitBlock(blockHitResult: BlockHitResult) {
        super.onHitBlock(blockHitResult)
        explode()
        discard()
    }

    fun explode() {
        // TODO: explode
    }

    override fun isNoGravity(): Boolean {
        return true
    }

    override fun getDefaultPickupItem(): ItemStack = Items.AIR.defaultInstance
}