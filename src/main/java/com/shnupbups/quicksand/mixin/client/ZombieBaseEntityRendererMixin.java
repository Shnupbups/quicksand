package com.shnupbups.quicksand.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.entity.mob.ZombieEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.shnupbups.quicksand.util.ZombieEntityInterface;

@Mixin(ZombieBaseEntityRenderer.class)
public abstract class ZombieBaseEntityRendererMixin<T extends ZombieEntity, M extends ZombieEntityModel<T>> {
	@ModifyReturnValue(method = "isShaking(Lnet/minecraft/entity/mob/ZombieEntity;)Z", at = @At("RETURN"))
	private boolean quicksand_isShaking(boolean original, T zombie) {
		return original || ((ZombieEntityInterface)zombie).quicksand_isConvertingInQuicksand();
	}
}
