package com.shnupbups.quicksand.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;

import com.shnupbups.quicksand.Quicksand;

@Mixin(Entity.class)
public class EntityMixin {
	@Redirect(method = "move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
	public boolean move_redirect(BlockState state, Block block) {
		return state.isOf(block) || state.isOf(Quicksand.QUICKSAND);
	}
}
