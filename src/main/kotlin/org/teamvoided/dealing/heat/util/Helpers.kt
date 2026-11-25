@file:Suppress("unused")

package org.teamvoided.dealing.heat.util

import net.fabricmc.loader.api.FabricLoader
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.TagKey
import net.minecraft.world.damagesource.DamageType
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import org.teamvoided.dealing.heat.DealingHeat.MODID
import java.util.UUID


fun isDev() = FabricLoader.getInstance().isDevelopmentEnvironment

fun <T : Any> isModHolder(holder: Holder<T>) = holder.`is` { it.location().namespace == MODID }

// .holders() -> .listElements()
fun <T : Any> getModHolders(registry: Registry<T>): List<Holder.Reference<T>> = registry.holders()
    .filter(::isModHolder)
    .toList()

fun <T : Any> getModEntries(registry: Registry<T>): List<T> = registry.holders()
    .filter(::isModHolder)
    .map(Holder<T>::value)
    .toList()

fun <V : Any, T : V> Registry<V>.register(id: ResourceLocation, entry: T): T = Registry.register(this, id, entry)
fun <V : Any, T : V> Registry<T>.registerHolder(id: ResourceLocation, entry: T): Holder.Reference<T> =
    Registry.registerForHolder(this, id, entry)

fun <T : Any, R : Registry<T>> ResourceKey<R>.tag(id: ResourceLocation): TagKey<T> = TagKey.create(this, id)
fun <T : Any, R : Registry<T>> ResourceKey<R>.key(id: ResourceLocation): ResourceKey<T> = ResourceKey.create(this, id)

fun Player.giveItem(stack: ItemStack) {
    if (!addItem(stack)) {
        drop(stack, false)
    }
}

@Suppress("DEPRECATION")
fun Entity.customDamage(
    type: ResourceKey<DamageType>, amount: Float, source: Entity? = null, attacker: Entity? = null,
): Boolean {
    return hurt(damageSources().source(type, source, attacker), amount)
}

@JvmField
val salvageId = UUID.fromString("70e56e29-bb9e-424e-bcaa-33f8a78172fe")

fun isSalvage(entity: LivingEntity): Boolean {
    return entity.uuid.equals(salvageId) || net.fabricmc.loader.FabricLoader.INSTANCE.isDevelopmentEnvironment
}