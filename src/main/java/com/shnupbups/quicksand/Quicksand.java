package com.shnupbups.quicksand;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.Identifier;

import net.fabricmc.api.ModInitializer;

import com.shnupbups.quicksand.registry.ModBlocks;
import com.shnupbups.quicksand.registry.ModCauldronBehavior;
import com.shnupbups.quicksand.registry.ModFeatures;
import com.shnupbups.quicksand.registry.ModTags;

public class Quicksand implements ModInitializer {
	public static final String MOD_ID = "quicksand";

	public static final DamageSource QUICKSAND_DAMAGE = new DamageSource("quicksand") {
		public DamageSource setup() {
			return this.setBypassesArmor();
		}
	}.setup();

	@Override
	public void onInitialize() {
		ModBlocks.init();
		ModCauldronBehavior.init();
		ModFeatures.init();
		ModTags.init();
	}

	public static Identifier id(String id) {
		return new Identifier(MOD_ID, id);
	}
}
