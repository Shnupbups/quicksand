package com.shnupbups.quicksand.registry;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

import com.shnupbups.quicksand.Quicksand;

public class ModTags {

	public static final TagKey<EntityType<?>> QUICKSAND_WALKABLE_MOBS = TagKey.of(RegistryKeys.ENTITY_TYPE, Quicksand.id("quicksand_walkable_mobs"));
	public static final TagKey<EntityType<?>> SURVIVES_IN_QUICKSAND = TagKey.of(RegistryKeys.ENTITY_TYPE, Quicksand.id("survives_in_quicksand"));
	public static final TagKey<Block> QUICKSAND = TagKey.of(RegistryKeys.BLOCK, Quicksand.id("quicksand"));

	public static void init() {
		// NO-OP
	}
}
