package org.teamvoided.dealing.heat.init

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.BlockItem
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour.Properties
import net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy
import org.teamvoided.dealing.heat.DealingHeat.id
import org.teamvoided.dealing.heat.util.getModEntries
import org.teamvoided.dealing.heat.util.key
import org.teamvoided.dealing.heat.util.register
import java.util.function.Function

object DHBlocks {

    val BLOCKS get() = getModEntries(BuiltInRegistries.BLOCK)

    val APPLE_BLOCK = register("apple_block", ::Block, ofFullCopy(Blocks.RED_TERRACOTTA))
    val SPECIAL_APPLE_BLOCK = registerNoItem("special_apple_block", ::Block, ofFullCopy(Blocks.NETHERITE_BLOCK))

    fun init() = Unit

    fun <T : Block> register(id: String, block: Function<Properties, T>, properties: Properties): T {
        val regBlock = registerNoItem(id, block, properties)
        DHItems.register(id, { BlockItem(regBlock, it) })
        return regBlock
    }

    fun <T : Block> registerNoItem(name: String, block: Function<Properties, T>, properties: Properties): T {
        val id = Registries.BLOCK.key(id(name))
        properties.setId(id) // 1.21.11 code
        return BuiltInRegistries.BLOCK.register(id.identifier(), block.apply(properties))
    }

}