package org.teamvoided.dealing.heat.data.registry

import net.minecraft.core.registries.Registries
import org.teamvoided.dealing.heat.DealingHeat.id
import org.teamvoided.dealing.heat.util.key


object DHDamageTypes {

    val GAY = key("gay")

    val FIREBALLED = key("fireballed")
    val STRONG_FIREBALLED = key("strong_fireballed")
    val STUNNED = key("stunned")
    val FIREWALL = key("firewall")
    val STRONG_FIREWALL = key("strong_firewall")

    val SPARK_BURNED = key("spark_burned")
    val SPARK_BURNED_HOT = key("spark_burned_hot")

    val SALVAGE_BURN = key("salvage_burn")

    fun key(id: String) = Registries.DAMAGE_TYPE.key(id(id))

}