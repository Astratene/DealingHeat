package org.teamvoided.dealing.heat.datagen.assets

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.Identifier
import org.teamvoided.dealing.heat.data.registry.DHDamageTypes
import org.teamvoided.dealing.heat.datagen.damageType
import org.teamvoided.dealing.heat.util.getModHolders
import java.util.concurrent.CompletableFuture

class ModEnLangProvider(var output: FabricDataOutput, p: CompletableFuture<HolderLookup.Provider>) :
    FabricLanguageProvider(output, p) {

    override fun generateTranslations(lookup: HolderLookup.Provider, gen: TranslationBuilder) {
        getModHolders(BuiltInRegistries.ITEM).forEach {
            gen.add(it.value(), genLang(it.key().identifier()))
        }
        getModHolders(BuiltInRegistries.BLOCK).forEach {
            trySafe { gen.add(it.value(), genLang(it.key().identifier())) }
        }
        getModHolders(BuiltInRegistries.ENTITY_TYPE).forEach {
            trySafe { gen.add(it.value(), genLang(it.key().identifier())) }
        }
        getModHolders(BuiltInRegistries.ATTRIBUTE).forEach {
            trySafe { gen.add(it.value().descriptionId, genLang(it.key().identifier())) }
        }

        gen.damageType(
            DHDamageTypes.GAY,
            "contracted gay",
            "contracted gay from",
            "contracted gay from" to "via means of"
        )
    }

    fun genLang(id: Identifier): String {
        return id.path.split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }
    }

    fun trySafe(fn: () -> Unit) {
        try {
            fn()
        } catch (e: Exception) {
            if (output.isStrictValidationEnabled) {
                LOGGER.warn("Exception found when lang gen: ", e)
            }
        }
    }

}