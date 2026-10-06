package org.teamvoided.dealing.heat.entity

import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.arrow.AbstractArrow
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.BlockHitResult
import org.teamvoided.dealing.heat.data.registry.DHDamageTypes
import org.teamvoided.dealing.heat.init.DHEntities.FIRE_BALL_BABY
import org.teamvoided.dealing.heat.util.customDamage

class FireBallBaby : AbstractArrow {

    constructor(entityType: EntityType<out FireBallBaby>, world: Level) : super(entityType, world)

    constructor(world: Level, owner: LivingEntity) : super(
        FIRE_BALL_BABY, owner, world, Items.ARROW.defaultInstance, Items.STONE.defaultInstance
    ) {
        this.ownerr = owner
    }

    constructor(x: Double, y: Double, z: Double, world: Level) : super(
        FIRE_BALL_BABY, x, y, z, world, Items.ARROW.defaultInstance, Items.STONE.defaultInstance
    )

    var indirectDamage = 0f
    var explosionRadius = 0f
    var isHot = false
    lateinit var ownerr: LivingEntity

    override fun tick() {
        if (this.ownerr == null) this.discard()
        super.tick()
    }

    override fun onHitBlock(blockHitResult: BlockHitResult) {
        super.onHitBlock(blockHitResult)
        explode()
        discard()
    }

    fun explode() {
        val damagetype = if (isHot) DHDamageTypes.STRONG_FIREBALLED else DHDamageTypes.FIREBALLED
        val world = this.level()
        val entities = mutableListOf<Entity>()
        entities.addAll(
            world.getEntities(
                this, AABB(
                    this.position().x + explosionRadius,
                    this.position().y + explosionRadius,
                    this.position().z + explosionRadius,
                    this.position().x - explosionRadius,
                    this.position().y - explosionRadius,
                    this.position().z - explosionRadius
                )
            ).filter { it is LivingEntity && it != this.owner && it.distanceTo(this) <= explosionRadius}
        )
        for (entity in entities) {
            entity.customDamage(damagetype, indirectDamage, this.ownerr)
        }
    }

    override fun getDefaultPickupItem(): ItemStack = Items.AIR.defaultInstance
}