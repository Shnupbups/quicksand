package com.shnupbups.quicksand.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import com.shnupbups.quicksand.Quicksand;
import com.shnupbups.quicksand.QuicksandBlock;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	@Shadow
	protected boolean jumping;

	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "method_26318(Lnet/minecraft/util/math/Vec3d;F)Lnet/minecraft/util/math/Vec3d;", at = @At("RETURN"), cancellable = true)
	public void inject_26318(Vec3d vec3d, float f, CallbackInfoReturnable<Vec3d> cir) {
		Vec3d vec3d2 = this.getVelocity();
		if ((this.horizontalCollision || this.jumping) && (this.getBlockState().isOf(Quicksand.QUICKSAND) || this.getBlockState().isOf(Quicksand.QUICKSAND_CAULDRON))) {
			cir.setReturnValue(new Vec3d(vec3d2.x, QuicksandBlock.canWalkOnQuicksand(this) ? 0.4D : 0.1D, vec3d2.z));
		}
	}

	@Shadow
	public abstract BlockState getBlockState();
}
