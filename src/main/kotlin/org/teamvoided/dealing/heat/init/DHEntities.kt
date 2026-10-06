package org.teamvoided.dealing.heat.init

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import org.teamvoided.dealing.heat.DealingHeat.id
import org.teamvoided.dealing.heat.entity.FireBallEntity

object DHEntities {
    fun init() = Unit

    // example
//    val ENTITY = register(
//        "entity",
//        EntityType.Builder.create(EntityType.EntityFactory(::Entity), SpawnGroup.MISC)
//            .setDimensions(0.5f, 0.5f).maxTrackingRange(4)
//            .build()
//    )

    val FIRE_BALL = register(
        "fire_ball",
        EntityType.Builder.of(EntityType.EntityFactory(::FireBallEntity), MobCategory.MISC)
            .sized(0.5f, 0.5f).clientTrackingRange(4)
            .build()
    )

    private fun <T : Entity> register(path: String, entry: EntityType<T>): EntityType<T> {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, id(path), entry)
    }
}
