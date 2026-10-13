package org.teamvoided.dealing.heat.util

import net.minecraft.world.entity.LivingEntity
import org.teamvoided.dealing.heat.init.DHDataAttachments.addHeat
import org.teamvoided.dealing.heat.init.DHDataAttachments.addHeatCooldown
import org.teamvoided.dealing.heat.init.DHDataAttachments.addStatic
import org.teamvoided.dealing.heat.init.DHDataAttachments.getHeatCooldown
import org.teamvoided.dealing.heat.init.DHDataAttachments.getHeat
import org.teamvoided.dealing.heat.init.DHDataAttachments.getStatic

fun tickHeat(entity: LivingEntity){
    val cooldown = entity.getHeatCooldown()
    val heat = entity.getHeat()
    val static = entity.getStatic()
    if (cooldown > 0){
        entity.addHeatCooldown(- 1)
    } else if (heat > 0) {
        entity.addHeat(-1)
        if (static < 1000){
            entity.addStatic(1)
        }
    }
}