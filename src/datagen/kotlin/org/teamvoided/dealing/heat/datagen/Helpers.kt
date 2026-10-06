package org.teamvoided.dealing.heat.datagen

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.world.damagesource.DamageType


fun ResourceKey<*>.toLangKey(): String = identifier().toLangKey()
fun Identifier.toLangKey(): String = toLanguageKey().replace("/", ".")

fun TranslationBuilder.damageType(
    key: ResourceKey<DamageType>, defaultMessage: String, attackerMessage: String, weaponMessage: Pair<String, String>,
) {
    val lang = key.toLangKey()
    add("death.attack.$lang", "%s $defaultMessage")
    add("death.attack.$lang.player", "%s $attackerMessage %s")
    add("death.attack.$lang.item", "%s ${weaponMessage.first} %s ${weaponMessage.second} %s")
}

