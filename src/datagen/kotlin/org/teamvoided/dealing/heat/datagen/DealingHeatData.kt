package org.teamvoided.dealing.heat.datagen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.core.HolderLookup.RegistryLookup
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.data.recipes.RecipeOutput
import org.teamvoided.dealing.heat.DealingHeat.MODID
import org.teamvoided.dealing.heat.DealingHeat.log
import org.teamvoided.dealing.heat.datagen.assets.ModEnLangProvider
import org.teamvoided.dealing.heat.datagen.assets.ModModelProvider
import org.teamvoided.dealing.heat.datagen.data.ModRecipeProvider
import org.teamvoided.dealing.heat.datagen.data.registry.ModDamageTypes
import org.teamvoided.dealing.heat.datagen.data.tags.ModBlockTagsProvider
import org.teamvoided.dealing.heat.datagen.data.tags.ModItemTagsProvider
import java.util.concurrent.CompletableFuture

object DealingHeatData : DataGeneratorEntrypoint {

    override fun getEffectiveModId(): String = MODID

    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        val pack = gen.createPack()
        log.info("Running \"${gen.modContainer.metadata.name}\" Datagen!")

        // Assets
        pack.addProvider(::ModEnLangProvider)
        pack.addProvider(::ModModelProvider)
        // Data
        pack.addProvider(::RegistryProvider)
        val blockTags = pack.addProvider(::ModBlockTagsProvider)
        pack.addProvider { o, f -> ModItemTagsProvider(o, f, blockTags) }
        pack.addProvider { o, p -> // don't ask why recipes are like this now
            object : FabricRecipeProvider(o, p) {
                override fun getName(): String = MODID
                override fun createRecipeProvider(p: HolderLookup.Provider, e: RecipeOutput) = ModRecipeProvider(p, e)
            }
        }
    }

    override fun buildRegistry(gen: RegistrySetBuilder) {
        gen.add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap)
    }

    class RegistryProvider(o: FabricDataOutput, p: CompletableFuture<HolderLookup.Provider>) :
        FabricDynamicRegistryProvider(o, p) {

        override fun getName(): String = "Registry Gen"

        override fun configure(provider: HolderLookup.Provider, entries: Entries) {
            entries.addAll(provider.lookupOrThrow(Registries.DAMAGE_TYPE))
        }

        fun <T : Any> Entries.addEverything(registry: RegistryLookup<T>): MutableList<Holder<T>> {
            return registry.listElementIds().map { add(registry, it) }.toList().toMutableList()
        }

    }
}