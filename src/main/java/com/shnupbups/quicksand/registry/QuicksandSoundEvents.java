package com.shnupbups.quicksand.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import com.shnupbups.quicksand.Quicksand;

public class QuicksandSoundEvents {
	public static final Identifier ZOMBIE_CONVERTS_TO_HUSK_ID = Quicksand.id("entity.zombie.converted_to_husk");

	public static final SoundEvent ZOMBIE_CONVERTS_TO_HUSK = SoundEvent.of(ZOMBIE_CONVERTS_TO_HUSK_ID);

	public static void init() {
		Registry.register(Registries.SOUND_EVENT, ZOMBIE_CONVERTS_TO_HUSK_ID, ZOMBIE_CONVERTS_TO_HUSK);
	}
}
