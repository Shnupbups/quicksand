package com.shnupbups.quicksand.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.shnupbups.quicksand.registry.QuicksandTags;

@Mixin(Entity.class)
public abstract class EntityMixin {
	@ModifyReturnValue(method = "canClimb(Lnet/minecraft/block/BlockState;)Z", at = @At("RETURN"))
	public boolean quicksand_canClimb(boolean original, BlockState state) {
		return original || state.isIn(QuicksandTags.QUICKSAND);
	}
}
