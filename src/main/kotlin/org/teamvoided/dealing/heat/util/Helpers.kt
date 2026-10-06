@file:Suppress("unused")

package org.teamvoided.dealing.heat.util

import net.fabricmc.loader.api.FabricLoader
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.TagKey
import net.minecraft.world.damagesource.DamageType
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import org.teamvoided.dealing.heat.DealingHeat.MODID


fun isDev() = FabricLoader.getInstance().isDevelopmentEnvironment

fun <T : Any> isModHolder(holder: Holder<T>) = holder.`is` { it.identifier().namespace == MODID }

fun <T : Any> getModHolders(registry: Registry<T>): List<Holder.Reference<T>> = registry.listElements()
    .filter(::isModHolder)
    .toList()

fun <T : Any> getModEntries(registry: Registry<T>): List<T> = registry.listElements()
    .filter(::isModHolder)
    .map(Holder<T>::value)
    .toList()

fun <V : Any, T : V> Registry<V>.register(id: Identifier, entry: T): T = Registry.register(this, id, entry)
fun <V : Any, T : V> Registry<T>.registerHolder(id: Identifier, entry: T): Holder.Reference<T> =
    Registry.registerForHolder(this, id, entry)

fun <T : Any, R : Registry<T>> ResourceKey<R>.tag(id: Identifier): TagKey<T> = TagKey.create(this, id)
fun <T : Any, R : Registry<T>> ResourceKey<R>.key(id: Identifier): ResourceKey<T> = ResourceKey.create(this, id)

fun Player.giveItem(stack: ItemStack) {
    if (!addItem(stack)) {
        drop(stack, false)
    }
}

@Suppress("DEPRECATION")
fun Entity.customDamage(
    type: ResourceKey<DamageType>, amount: Float, source: Entity? = null, attacker: Entity? = null,
): Boolean {
    return hurtOrSimulate(damageSources().source(type, source, attacker), amount)
}

