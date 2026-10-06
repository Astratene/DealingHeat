package org.teamvoided.dealing.heat.datagen.data.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.BlockTagProvider
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

class ModBlockTagsProvider(o: FabricDataOutput, p: CompletableFuture<HolderLookup.Provider>) : BlockTagProvider(o, p) {

    override fun addTags(lookup: HolderLookup.Provider) {
//        valueLookupBuilder(BlockTags.LOGS)
//            .add(SIBlocks.SPECIAL_APPLE_BLOCK)

//        valueLookupBuilder(SIBlockTags.APPLE_LIKE)
//            .add(SIBlocks.APPLE_BLOCK, Blocks.RED_TERRACOTTA)

//        valueLookupBuilder(ConventionalBlockTags.STORAGE_BLOCKS)
//            .forceAddTag(SIBlockTags.APPLE_LIKE)
    }

}