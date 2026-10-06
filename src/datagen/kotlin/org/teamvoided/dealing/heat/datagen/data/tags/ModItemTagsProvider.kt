package org.teamvoided.dealing.heat.datagen.data.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.ItemTagProvider
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

class ModItemTagsProvider(o: FabricDataOutput, p: CompletableFuture<HolderLookup.Provider>, blockTag: BlockTagProvider) :
    ItemTagProvider(o, p, blockTag) {

    override fun addTags(lookup: HolderLookup.Provider) {
//        copy(SIBlockTags.APPLE_LIKE, SIItemTags.APPLE_LIKE)
//        copy(ConventionalBlockTags.STORAGE_BLOCKS, ConventionalItemTags.STORAGE_BLOCKS)

//        valueLookupBuilder(ItemTags.SWORDS)
//            .add(SIBlocks.APPLE_BLOCK.asItem())
//        valueLookupBuilder(SIItemTags.APPLE_LIKE)
//            .add(SIItems.APPLE_2)
//        valueLookupBuilder(ConventionalItemTags.HIDDEN_FROM_RECIPE_VIEWERS)
//            .add(SIItems.APPLE_2)
    }

}