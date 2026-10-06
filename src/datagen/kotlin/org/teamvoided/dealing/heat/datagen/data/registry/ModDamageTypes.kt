package org.teamvoided.dealing.heat.datagen.data.registry

import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.world.damagesource.DamageEffects
import net.minecraft.world.damagesource.DamageScaling
import net.minecraft.world.damagesource.DamageType
import org.teamvoided.dealing.heat.data.registry.DHDamageTypes
import org.teamvoided.dealing.heat.datagen.toLangKey

object ModDamageTypes : RegistryBootstrapper<DamageType> {

    override fun BootstrapContext<DamageType>.init() {
        damage(DHDamageTypes.FIREBALLED)
        damage(DHDamageTypes.STRONG_FIREBALLED)
    }

    fun BootstrapContext<DamageType>.damage(
        key: ResourceKey<DamageType>,
        exhaustion: Float = 0f, effect: DamageEffects = DamageEffects.HURT,
    ) {
        register(key, DamageType(key.toLangKey(), DamageScaling.NEVER, exhaustion, effect))
    }

}