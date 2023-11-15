package com.shnupbups.quicksand.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;

import com.shnupbups.quicksand.block.QuicksandBlock;
import com.shnupbups.quicksand.registry.QuicksandTags;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	@Shadow
	protected boolean jumping;

	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@ModifyReturnValue(method = "applyMovementInput(Lnet/minecraft/util/math/Vec3d;F)Lnet/minecraft/util/math/Vec3d;", at = @At("RETURN"))
	public Vec3d quicksand_applyMovementInput(Vec3d original) {
		if ((this.horizontalCollision || this.jumping)
				&& (this.getBlockStateAtPos().isIn(QuicksandTags.QUICKSAND) && QuicksandBlock.canWalkOnQuicksand(this))) {
			return new Vec3d(original.x, 0.2, original.z);
		}
		return original;
	}
}
