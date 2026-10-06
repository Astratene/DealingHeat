package org.teamvoided.dealing.heat.client

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.minecraft.client.particle.FlameParticle
import net.minecraft.client.player.LocalPlayer
import org.teamvoided.dealing.heat.client.init.DHKeyMappings
import org.teamvoided.dealing.heat.init.DHParticleTypes
import org.teamvoided.dealing.heat.net.ActionPayload

object DealingHeatClient {

    fun init() {
        DHKeyMappings.init()
        ParticleFactoryRegistry.getInstance().register(DHParticleTypes.NOT_SPARKS, FlameParticle::SmallFlameProvider)
        ClientTickEvents.END_CLIENT_TICK.register { localPlayerTick(it.player ?: return@register) }
    }

    fun localPlayerTick(player: LocalPlayer) {
        if (DHKeyMappings.killingKey.consumeClick()) {
            println(DHKeyMappings.killingKey.defaultKey.value)
            ClientPlayNetworking.send(ActionPayload.STEP)
            TODO("handle local Input")
        }

        if (DHKeyMappings.killingKey2.consumeClick()) {
            ClientPlayNetworking.send(ActionPayload.FLY)
            TODO("handle local Input")
        }
        if (DHKeyMappings.killingKey3.consumeClick()) {
            ClientPlayNetworking.send(ActionPayload.TELEPORT)
            TODO("handle local Input")
        }
    }

}