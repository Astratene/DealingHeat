package org.teamvoided.dealing.heat.init

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes.simple
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.core.registries.BuiltInRegistries
import org.teamvoided.dealing.heat.DealingHeat.id
import org.teamvoided.dealing.heat.util.register

object DHParticleTypes {

    val NOT_SPARKS: SimpleParticleType = register("not_sparks", simple())

    fun init() = Unit

    fun <O : ParticleOptions, T : ParticleType<O>> register(id: String, particleType: T): T {
        return BuiltInRegistries.PARTICLE_TYPE.register(id(id), particleType)
    }

}