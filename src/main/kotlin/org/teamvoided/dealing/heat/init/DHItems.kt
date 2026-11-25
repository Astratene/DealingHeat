package org.teamvoided.dealing.heat.init

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.world.food.Foods
import net.minecraft.world.item.Item
import net.minecraft.world.item.Item.Properties
import net.minecraft.world.item.Rarity
import net.minecraft.world.item.Tiers
import org.teamvoided.dealing.heat.DealingHeat.id
import org.teamvoided.dealing.heat.item.Sparks
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

    val SPARKS = register(
        "sparks", ::Sparks, Properties()
            .rarity(Rarity.EPIC)
            .attributes(Sparks.createAttributes(Tiers.NETHERITE, 1.0f, -2F))
    )

    fun init() = Unit

    fun register(name: String, item: Function<Properties, Item>, properties: Properties = Properties()): Item {
        val id = Registries.ITEM.key(id(name))
        return Registry.register(BuiltInRegistries.ITEM, id.registry(), item.apply(properties))
    }

}