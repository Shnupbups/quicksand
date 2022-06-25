package com.shnupbups.quicksand.mixin;

import net.minecraft.entity.mob.HuskEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;

import com.shnupbups.quicksand.util.ZombieEntityInterface;

@Mixin(HuskEntity.class)
public abstract class HuskEntityMixin extends ZombieEntity implements ZombieEntityInterface {
	private HuskEntityMixin(World world) {
		super(world);
	}

	@Override
	public boolean canConvertInQuicksand() {
		return false;
	}
}
