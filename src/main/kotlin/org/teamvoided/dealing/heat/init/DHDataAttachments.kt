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

    val HEAT_TYPE_AMOUNT_VALUE = register("heat_type_amount_value") {
        it
            .persistent(Codec.INT)
            .syncWith(ByteBufCodecs.INT, targetOnly())
            .copyOnDeath()
    }

    // region Helpers
    fun LivingEntity.getHeatTypeAmountValue(): Int = getAttachedOrElse(HEAT_TYPE_AMOUNT_VALUE, 0)
    fun LivingEntity.setHeatTypeAmountValue(value: Int) = setAttached(HEAT_TYPE_AMOUNT_VALUE, value)
    fun LivingEntity.clearHeatTypeAmountValue() = removeAttached(HEAT_TYPE_AMOUNT_VALUE)
    // endregion

    fun init() = Unit

    fun <T : Any> register(id: String, builder: Consumer<AttachmentRegistry.Builder<T>>): AttachmentType<T> {
        return AttachmentRegistry.create(id(id), builder)
    }

}