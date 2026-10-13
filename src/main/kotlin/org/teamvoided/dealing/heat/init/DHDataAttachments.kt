package org.teamvoided.dealing.heat.init

import com.mojang.serialization.Codec
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate.targetOnly
import net.fabricmc.fabric.api.attachment.v1.AttachmentType
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.world.entity.LivingEntity
import org.teamvoided.dealing.heat.DealingHeat.id
import java.util.function.Consumer

@Suppress("UnstableApiUsage")
object DHDataAttachments {

    val HEAT = register("heat") {
        it
            .persistent(Codec.INT)
            .syncWith(ByteBufCodecs.INT, targetOnly())
            .copyOnDeath()
    }

    // buffer for reducing heat
    val HEAT_COOLDOWN = register("heat_cooldown_value") {
        it
            .persistent(Codec.INT)
            .syncWith(ByteBufCodecs.INT, targetOnly())
            .copyOnDeath()
    }

    val STATIC = register("static_type_amount_value") {
        it
            .persistent(Codec.INT)
            .syncWith(ByteBufCodecs.INT, targetOnly())
            .copyOnDeath()
    }

    // region Helpers
    fun LivingEntity.getHeat(): Int = getAttachedOrElse(HEAT, 0)
    fun LivingEntity.setHeat(value: Int) = setAttached(HEAT, value)
    fun LivingEntity.addHeat(value: Int) {
        if (getHeat() + value <= this.attributes.getValue(DHAttributes.MAX_HEAT)) {
            this.setHeat(this.getHeat() + value)
        }
        else {
            this.setHeat(this.attributes.getValue(DHAttributes.MAX_HEAT).toInt())
            // todo salvage take damage here if thingy
        }
        if (value > 0) {
            this.setHeatCooldown(60)
        }
    }

    fun LivingEntity.clearHeat() = removeAttached(HEAT)

    fun LivingEntity.getHeatCooldown(): Int = getAttachedOrElse(HEAT_COOLDOWN, 0)
    fun LivingEntity.setHeatCooldown(value: Int) = setAttached(HEAT_COOLDOWN, value)
    fun LivingEntity.addHeatCooldown(value: Int) {
        this.setHeatCooldown(this.getHeatCooldown() + value)
    }

    fun LivingEntity.clearHeatCooldown() = removeAttached(HEAT_COOLDOWN)

    fun LivingEntity.getStatic(): Int = getAttachedOrElse(STATIC, 0)
    fun LivingEntity.setStatic(value: Int) = setAttached(STATIC, value)
    fun LivingEntity.addStatic(value: Int) {
        this.setStatic(this.getStatic() + value)
    }

    fun LivingEntity.clearStatic() = removeAttached(STATIC)
    // endregion

    fun init() = Unit

    fun <T : Any> register(id: String, builder: Consumer<AttachmentRegistry.Builder<T>>): AttachmentType<T> {
        return AttachmentRegistry.create(id(id), builder)
    }

}