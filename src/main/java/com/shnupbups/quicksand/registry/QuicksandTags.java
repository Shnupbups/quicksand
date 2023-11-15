package com.shnupbups.quicksand.registry;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;

import com.shnupbups.quicksand.Quicksand;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

public class QuicksandTags {

	public static final TagKey<EntityType<?>> QUICKSAND_WALKABLE_MOBS = TagKey.of(RegistryKeys.ENTITY_TYPE, Quicksand.id("quicksand_walkable_mobs"));
	public static final TagKey<EntityType<?>> SURVIVES_IN_QUICKSAND = TagKey.of(RegistryKeys.ENTITY_TYPE, Quicksand.id("survives_in_quicksand"));
	public static final TagKey<Block> QUICKSAND = TagKey.of(RegistryKeys.BLOCK, Quicksand.id("quicksand"));
	public static final TagKey<Block> QUICKSAND_CAULDRONS = TagKey.of(RegistryKeys.BLOCK, Quicksand.id("quicksand_cauldrons"));
	public static final TagKey<Biome> HAS_QUICKSAND_LAKES = TagKey.of(RegistryKeys.BIOME, Quicksand.id("has_quicksand_lakes"));
	public static final TagKey<Biome> HAS_RED_QUICKSAND_LAKES = TagKey.of(RegistryKeys.BIOME, Quicksand.id("has_red_quicksand_lakes"));

	public static void init() {
		// NO-OP
	}
}
