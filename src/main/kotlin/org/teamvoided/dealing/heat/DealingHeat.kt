package org.teamvoided.dealing.heat

import me.fzzyhmstrs.fzzy_config.api.ConfigApi
import net.minecraft.resources.ResourceLocation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.dealing.heat.config.DealingHeatConfig
import org.teamvoided.dealing.heat.init.DHBlocks
import org.teamvoided.dealing.heat.init.DHDataAttachments
import org.teamvoided.dealing.heat.init.DHItems
import org.teamvoided.dealing.heat.init.DHNetworking
import org.teamvoided.dealing.heat.init.DHParticleTypes

object DealingHeat {

    const val MODID = "dealing_heat"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(DealingHeat::class.simpleName)

    @JvmField
    var config = ConfigApi.registerAndLoadConfig(::DealingHeatConfig)

    fun init() {
        log.info("Punching things and causing lighting or something idk... i cant read")
        DHItems.init()
        DHBlocks.init()
        DHDataAttachments.init()
        DHParticleTypes.init()
        DHNetworking.init()
    }

    fun id(namespace: String, path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(namespace, path)
    fun mc(path: String): ResourceLocation = ResourceLocation.withDefaultNamespace(path)
    fun id(path: String) = id(MODID, path)

}