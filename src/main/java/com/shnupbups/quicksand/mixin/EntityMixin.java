package com.shnupbups.quicksand.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.shnupbups.quicksand.Quicksand;
import com.shnupbups.quicksand.registry.ModBlocks;
import com.shnupbups.quicksand.registry.ModTags;

@Mixin(Entity.class)
public class EntityMixin {
	@Redirect(method = "move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", ordinal = 0))
	public boolean move_redirect(BlockState state, Block block) {
		return state.isOf(block) || state.isIn(ModTags.QUICKSAND);
	}
}
