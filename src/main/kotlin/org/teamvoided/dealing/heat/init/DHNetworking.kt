package org.teamvoided.dealing.heat.init

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import org.teamvoided.dealing.heat.net.ActionPayload

object DHNetworking {

    fun init() {
        PayloadTypeRegistry.playC2S().register(ActionPayload.ID, ActionPayload.CODEC)
        ServerPlayNetworking.registerGlobalReceiver(ActionPayload.ID, ::handleAction)
    }

    fun handleAction(payload: ActionPayload, ctx: ServerPlayNetworking.Context) {
        val player = ctx.player()
        when (payload.type) {
            else -> TODO("Handle Action")
        }
    }

}