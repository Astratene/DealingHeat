package org.teamvoided.dealing.heat.client.init

import com.mojang.blaze3d.platform.InputConstants
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper.registerKeyBinding
import net.minecraft.client.KeyMapping
import net.minecraft.client.KeyMapping.Category.MOVEMENT
import org.lwjgl.glfw.GLFW
import org.teamvoided.dealing.heat.DealingHeat.MODID

object DHKeyMappings {

    val killingKey = registerKeyBinding(KeyMapping("key.$MODID.killingKey", GLFW.GLFW_KEY_MENU, MOVEMENT))
    val killingKey2 = registerKeyBinding(KeyMapping("key.$MODID.killingKey2", InputConstants.KEY_K, MOVEMENT))
    val killingKey3 = registerKeyBinding(KeyMapping("key.$MODID.killingKey3", InputConstants.KEY_K, MOVEMENT))

    fun init() = Unit

}