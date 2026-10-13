package org.teamvoided.dealing.heat.item

import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import org.teamvoided.dealing.heat.data.registry.DHDamageTypes
import org.teamvoided.dealing.heat.init.DHDataAttachments.getHeat
import org.teamvoided.dealing.heat.util.customDamage

class Sparks(settings: Properties) : Item(settings) {

    override fun postHurtEnemy(itemStack: ItemStack, target: LivingEntity, user: LivingEntity) {
        super.postHurtEnemy(itemStack, target, user)
        val userHeat = user.getHeat()
        //first applying the extra effects you get from heat using these weapons
        var extraDamage = 0f //extra damage stuff first
        when {
            userHeat in 800..<1000 -> extraDamage = 1f
            userHeat in 1000..<1250 -> extraDamage = 2f
            userHeat in 1250..<2000 -> extraDamage = 4f
            userHeat >= 2000 -> extraDamage = 8f
        }
        val damageType = if (userHeat > 1000) DHDamageTypes.SPARK_BURNED_HOT else DHDamageTypes.SPARK_BURNED
        target.customDamage(damageType, extraDamage, user, user)
        if (userHeat >= 500) {
            target.igniteForSeconds(2f)
        }
        if(userHeat >= 1500) {
            //todo add the short range flame projectile, and move this to an "on swing" function
        }
        if(userHeat >= 1750) {
            //todo add the small flame explosion
        }
        //And then adding heat to the user.

    }

}