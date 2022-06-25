package com.shnupbups.quicksand.registry;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import com.shnupbups.quicksand.Quicksand;

public class ModSoundEvents {
	public static final Identifier ZOMBIE_CONVERTS_TO_HUSK_ID = Quicksand.id("entity.zombie.converted_to_husk");

	public static final SoundEvent ZOMBIE_CONVERTS_TO_HUSK = new SoundEvent(ZOMBIE_CONVERTS_TO_HUSK_ID);

	public static void init() {
		Registry.register(Registry.SOUND_EVENT, ZOMBIE_CONVERTS_TO_HUSK_ID, ZOMBIE_CONVERTS_TO_HUSK);
	}
}
