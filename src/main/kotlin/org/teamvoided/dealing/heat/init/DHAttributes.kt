package org.teamvoided.dealing.heat.init

import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.RangedAttribute
import org.teamvoided.dealing.heat.DealingHeat.id
import org.teamvoided.dealing.heat.util.registerHolder
import java.util.function.Function

object DHAttributes {

    val MAX_HEAT = register("max_heat") { RangedAttribute(it, 0.0, 0.0, 256.0).setSyncable(true) }

    fun init() = Unit

    fun register(name: String, item: Function<String, Attribute>): Holder.Reference<Attribute> {
        return BuiltInRegistries.ATTRIBUTE.registerHolder(id(name), item.apply("attribute.name.$name"))
    }

}