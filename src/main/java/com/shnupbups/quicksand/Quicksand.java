package com.shnupbups.quicksand;

import com.shnupbups.quicksand.registry.*;
import net.minecraft.util.Identifier;

import net.fabricmc.api.ModInitializer;

public class Quicksand implements ModInitializer {
	public static final String MOD_ID = "quicksand";

	@Override
	public void onInitialize() {
		QuicksandBlocks.init();
		QuicksandCauldronBehavior.init();
		QuicksandFeatures.init();
		QuicksandTags.init();
		QuicksandSoundEvents.init();
		QuicksandItemGroupStuff.init();
	}

	public static Identifier id(String id) {
		return new Identifier(MOD_ID, id);
	}
}
