package org.teamvoided.dealing.heat.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teamvoided.dealing.heat.init.DHAttributes;

import java.util.Collection;

import static org.teamvoided.dealing.heat.init.DHDataAttachments.addHeat;
import static org.teamvoided.dealing.heat.init.DHDataAttachments.getHeat;
import static org.teamvoided.dealing.heat.util.HelpersKt.isSalvage;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @Shadow
    public abstract int awardRecipes(Collection<RecipeHolder<?>> collection);

    @ModifyReturnValue(method = "createAttributes", at = @At(value = "RETURN"))
    private static AttributeSupplier.Builder addMaxHeat(AttributeSupplier.Builder original) {
        return original.add(DHAttributes.MAX_HEAT, 1000);
    }


    @Inject(method = "attack", at = @At(value = "HEAD"))
    void addHeatOnAttackForSalvage(Entity entity, CallbackInfo ci) {
        if (isSalvage((Player) (Object) this)) {
            addHeat((Player) (Object) this, 50);
        }
    }

}
