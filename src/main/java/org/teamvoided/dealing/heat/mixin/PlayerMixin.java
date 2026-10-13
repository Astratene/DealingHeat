package org.teamvoided.dealing.heat.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dealing.heat.init.DHAttributes;

@Mixin(Player.class)
public class PlayerMixin {

    @ModifyReturnValue(method = "createAttributes", at = @At(value = "RETURN"))
    private static AttributeSupplier.Builder addHeat(AttributeSupplier.Builder original) {

        return original.add(DHAttributes.MAX_HEAT, 1000);
    }

}
