package org.teamvoided.dealing.heat.data.tags

import net.minecraft.core.registries.Registries
import org.teamvoided.dealing.heat.DealingHeat.id
import org.teamvoided.dealing.heat.util.tag

object DHItemTags {

    val APPLE_LIKE = create("apple_like")

    fun create(id: String) = Registries.ITEM.tag(id(id))

}