package com.shnupbups.quicksand.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.shnupbups.quicksand.registry.QuicksandSoundEvents;
import com.shnupbups.quicksand.registry.QuicksandTags;
import com.shnupbups.quicksand.util.ZombieEntityInterface;

@Mixin(ZombieEntity.class)
public abstract class ZombieEntityMixin extends HostileEntity implements ZombieEntityInterface {
	@Unique
	private static final TrackedData<Boolean> CONVERTING_IN_QUICKSAND = DataTracker.registerData(ZombieEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

	@Unique
	private boolean submergedInQuicksand;
	@Unique
	private int inQuicksandTime;
	@Unique
	private int ticksUntilQuicksandConversion;

	private ZombieEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "initDataTracker()V", at = @At("RETURN"))
	private void quicksand_initDataTracker(CallbackInfo ci) {
		this.getDataTracker().startTracking(CONVERTING_IN_QUICKSAND, false);
	}

	@Override
	public boolean quicksand_isConvertingInQuicksand() {
		return this.getDataTracker().get(CONVERTING_IN_QUICKSAND);
	}

	@Override
	public boolean quicksand_canConvertInQuicksand() {
		return this.canConvertInWater();
	}

	@Override
	public void quicksand_setTicksUntilQuicksandConversion(int ticksUntilQuicksandConversion) {
		this.ticksUntilQuicksandConversion = ticksUntilQuicksandConversion;
		this.getDataTracker().set(CONVERTING_IN_QUICKSAND, true);
	}

	@Override
	public void quicksand_convertInQuicksand() {
		this.convertTo(EntityType.HUSK);
		if (!this.isSilent()) {
			this.getWorld().playSound(null, this.getBlockPos(), QuicksandSoundEvents.ZOMBIE_CONVERTS_TO_HUSK, SoundCategory.HOSTILE, 2.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f);
		}
	}

	@Override
	public boolean quicksand_isSubmergedInQuicksand() {
		return this.submergedInQuicksand;
	}

	@Override
	public void quicksand_updateSubmergedInQuicksand() {
		this.submergedInQuicksand = this.getWorld().getBlockState(new BlockPos(this.getBlockX(), (int) (this.getEyeY() - 0.11), this.getBlockZ())).isIn(QuicksandTags.QUICKSAND);
	}

	@Inject(method = "writeCustomDataToNbt(Lnet/minecraft/nbt/NbtCompound;)V", at = @At("RETURN"))
	private void quicksand_writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
		nbt.putInt("InQuicksandTime", this.quicksand_isSubmergedInQuicksand() ? this.inQuicksandTime : -1);
		nbt.putInt("QuicksandConversionTime", this.quicksand_isConvertingInQuicksand() ? this.ticksUntilQuicksandConversion : -1);
	}

	@Inject(method = "readCustomDataFromNbt(Lnet/minecraft/nbt/NbtCompound;)V", at = @At("RETURN"))
	private void quicksand_readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
		this.inQuicksandTime = nbt.getInt("InQuicksandTime");
		if (nbt.contains("QuicksandConversionTime", NbtElement.NUMBER_TYPE) && nbt.getInt("QuicksandConversionTime") > -1) {
			this.quicksand_setTicksUntilQuicksandConversion(nbt.getInt("QuicksandConversionTime"));
		}
	}

	@Inject(method = "tick()V", at = @At("HEAD"))
	private void quicksand_tick(CallbackInfo ci) {
		if (!this.getWorld().isClient && this.isAlive() && !this.isAiDisabled() && this.quicksand_canConvertInQuicksand()) {
			this.quicksand_updateSubmergedInQuicksand();

			if (this.quicksand_isConvertingInQuicksand()) {
				--this.ticksUntilQuicksandConversion;
				if (this.ticksUntilQuicksandConversion < 0) {
					this.quicksand_convertInQuicksand();
				}
			} else if (this.quicksand_isSubmergedInQuicksand()) {
				++this.inQuicksandTime;
				if (this.inQuicksandTime >= 600) {
					this.quicksand_setTicksUntilQuicksandConversion(300);
				}
			} else {
				this.inQuicksandTime = -1;
			}
		}
	}

	@Shadow
	protected abstract void convertTo(EntityType<? extends ZombieEntity> entityType);

	@Shadow
	protected abstract boolean canConvertInWater();
}
