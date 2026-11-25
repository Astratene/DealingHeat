package org.teamvoided.dealing.heat.init

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import org.teamvoided.dealing.heat.DealingHeat.id
import org.teamvoided.dealing.heat.entity.FireBall
import org.teamvoided.dealing.heat.util.key
import org.teamvoided.dealing.heat.util.register

object DHEntities {

    val FIRE_BALL = register(
        "fire_ball",
        EntityType.Builder.of(EntityType.EntityFactory(::FireBall), MobCategory.MISC)
            .sized(0.5f, 0.5f).clientTrackingRange(4).build()
    )

    val FIRE_BALL_BABY = register(
        "fire_ball_baby",
        EntityType.Builder.of(EntityType.EntityFactory(::FireBall), MobCategory.MISC)
            .sized(0.5f, 0.5f).clientTrackingRange(4).build()
    )

    fun init() = Unit

    private fun <T : Entity> register(path: String, entry: EntityType<T>): EntityType<T> {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, id(path), entry)
    }
}
