package org.teamvoided.dealing.heat.net

import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import org.teamvoided.dealing.heat.DealingHeat.id

class ActionPayload(val type: ActionType) : CustomPacketPayload {

    override fun type(): CustomPacketPayload.Type<ActionPayload> = ID

    companion object {

        val ID = CustomPacketPayload.Type<ActionPayload>(id("action"))
        val CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, { it.type.ordinal }, { ActionPayload(ActionType.entries[it]) }
        )

        enum class ActionType { STEP, FLY, TELEPORT }

        val STEP = ActionPayload(ActionType.STEP)
        val FLY = ActionPayload(ActionType.FLY)
        val TELEPORT = ActionPayload(ActionType.FLY)

    }
}