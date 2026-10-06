package org.teamvoided.dealing.heat.init

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.world.food.Foods
import net.minecraft.world.item.Item
import net.minecraft.world.item.Item.Properties
import net.minecraft.world.item.Rarity
import org.teamvoided.dealing.heat.DealingHeat.id
import org.teamvoided.dealing.heat.util.getModEntries
import org.teamvoided.dealing.heat.util.key
import java.util.function.Function

object DHItems {

    val ITEMS get() = getModEntries(BuiltInRegistries.ITEM)

    val APPLE_2 = register(
        "apple_2", ::Item, Properties()
            .rarity(Rarity.RARE)
            .food(Foods.APPLE)
    )

    fun init() = Unit

    fun register(name: String, item: Function<Properties, Item>, properties: Properties = Properties()): Item {
        val id = Registries.ITEM.key(id(name))
        properties.setId(id) // 1.21.11 code
        return Registry.register(BuiltInRegistries.ITEM, id.identifier(), item.apply(properties))
    }

}