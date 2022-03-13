package com.shnupbups.quicksand.registry;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

import com.shnupbups.quicksand.Quicksand;

public class ModTags {

	public static final TagKey<EntityType<?>> QUICKSAND_WALKABLE_MOBS = TagKey.of(Registry.ENTITY_TYPE_KEY, Quicksand.id("quicksand_walkable_mobs"));
	public static final TagKey<Block> QUICKSAND = TagKey.of(Registry.BLOCK_KEY, Quicksand.id("quicksand"));

	public static void init() {
		// NO-OP
	}
}
