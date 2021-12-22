package com.shnupbups.quicksand;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.tag.TagFactory;

import com.shnupbups.quicksand.registry.ModBlocks;
import com.shnupbups.quicksand.registry.ModCauldronBehavior;
import com.shnupbups.quicksand.registry.ModFeatures;

public class Quicksand implements ModInitializer {
	public static final String MOD_ID = "quicksand";

	public static final Tag.Identified<EntityType<?>> QUICKSAND_WALKABLE_MOBS = TagFactory.ENTITY_TYPE.create(id("quicksand_walkable_mobs"));

	public static final DamageSource QUICKSAND_DAMAGE = new DamageSource("quicksand") {
		@Override
		public boolean bypassesArmor() {
			return true;
		}
	};

	@Override
	public void onInitialize() {
		ModBlocks.init();
		ModCauldronBehavior.init();
		ModFeatures.init();
	}

	public static Identifier id(String id) {
		return new Identifier(MOD_ID, id);
	}
}
