package com.shnupbups.quicksand.mixin.client;

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
	@Inject(method = "isShaking(Lnet/minecraft/entity/mob/ZombieEntity;)Z", at = @At("RETURN"), cancellable = true)
	private void injectIsShaking(T zombie, CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(cir.getReturnValueZ() || ((ZombieEntityInterface)zombie).isConvertingInQuicksand());
	}
}
