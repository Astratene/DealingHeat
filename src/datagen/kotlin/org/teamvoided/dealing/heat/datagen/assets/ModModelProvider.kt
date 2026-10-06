package org.teamvoided.dealing.heat.datagen.assets


import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.client.data.models.BlockModelGenerators
import net.minecraft.client.data.models.ItemModelGenerators
import net.minecraft.client.data.models.model.ModelTemplates
import net.minecraft.client.data.models.model.TexturedModel
import org.teamvoided.dealing.heat.init.DHBlocks
import org.teamvoided.dealing.heat.init.DHItems

class ModModelProvider(o: FabricDataOutput) : FabricModelProvider(o) {

    override fun generateBlockStateModels(gen: BlockModelGenerators) {
        gen.createTrivialCube(DHBlocks.APPLE_BLOCK)
        gen.createTrivialBlock(DHBlocks.SPECIAL_APPLE_BLOCK, TexturedModel.LEAVES)
    }

    val single = listOf(DHItems.APPLE_2)

    override fun generateItemModels(gen: ItemModelGenerators) {
        single.forEach { gen.generateFlatItem(it, ModelTemplates.FLAT_ITEM) }
    }

}