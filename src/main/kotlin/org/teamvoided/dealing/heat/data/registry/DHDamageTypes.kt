package org.teamvoided.dealing.heat.data.registry

import net.minecraft.core.registries.Registries
import org.teamvoided.dealing.heat.DealingHeat.id
import org.teamvoided.dealing.heat.util.key


object DHDamageTypes {

    val GAY = key("gay")

    fun key(id: String) = Registries.DAMAGE_TYPE.key(id(id))

}