package org.teamvoided.dealing.heat.datagen.data

import net.minecraft.core.HolderLookup
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeProvider

class ModRecipeProvider(p: HolderLookup.Provider, o: RecipeOutput) : RecipeProvider(p, o) {

    override fun buildRecipes() {
       /* shaped(RecipeCategory.TOOLS, DFItems.RESONANT_PICKAXE)
            .pattern("AAC")
            .pattern(" DA")
            .pattern("S A")
            .define('A', DFItems.RESONANT_ALLOY_INGOT)
            .define('D', DFItemTags.DIAMOND_LEVEL_PICKAXE)
            .define('S', Items.STICK)
            .define('C', Items.CALIBRATED_SCULK_SENSOR)
            .unlockedBy("has_resonant_alloy", has(DFItems.RESONANT_ALLOY_INGOT))
            .save(output)*/
    }

}