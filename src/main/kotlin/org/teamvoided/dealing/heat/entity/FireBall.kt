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
import net.minecraft.world.phys.EntityHitResult
import org.teamvoided.dealing.heat.data.registry.DHDamageTypes
import org.teamvoided.dealing.heat.init.DHEntities.FIRE_BALL
import org.teamvoided.dealing.heat.util.customDamage

class FireBall : AbstractArrow {

    constructor(entityType: EntityType<out FireBall>, world: Level) : super(entityType, world)

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


    override fun tick() {
        if (this.lifespan > 0) lifespan--
        else {
            this.explode()
            this.discard()
        }
        if (this.getOwner() == null) this.discard()
        super.tick()
    }

    override fun onHitEntity(entityHitResult: EntityHitResult) {
        val entity = entityHitResult.entity
        if (entity is LivingEntity) {
            val damageType = if (isHot) DHDamageTypes.STRONG_FIREBALLED else DHDamageTypes.FIREBALLED
            entity.customDamage(damageType, directDamage, this.getOwner())
            explode()
            discard()
        }
    }

    override fun onHitBlock(blockHitResult: BlockHitResult) {
        super.onHitBlock(blockHitResult)
        explode()
        discard()
    }

    fun explode() {
        if (this.getOwner() is LivingEntity) {
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
                ).filter { it is LivingEntity && it != this.owner && it.distanceTo(this) <= explosionRadius }
            )
            for (entity in entities) {
                entity.customDamage(damagetype, indirectDamage)
            }
            if (hasBabies) {
                repeat(6) {
                    val baby = FireBallBaby(this.level(), this.getOwner() as LivingEntity)
                    baby.explosionRadius = this.explosionRadius / 2f
                    baby.indirectDamage = if (this.isHot) 10f else 5f
                    baby.isHot = this.isHot
                    baby.setPos(this.position())
                    baby.setDeltaMovement(
                        world.random.nextDouble().minus(0.5f),
                        world.random.nextDouble().minus(0.5f),
                        world.random.nextDouble().minus(0.5f)
                    )
                    world.addFreshEntity(baby)
                }
            }
        }
    }

    override fun isNoGravity(): Boolean = true

    override fun getDefaultPickupItem(): ItemStack = Items.AIR.defaultInstance
}