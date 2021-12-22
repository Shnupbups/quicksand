package com.shnupbups.quicksand.registry;

import java.util.Map;

import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;

import com.shnupbups.cauldronlib.CauldronLib;

public interface ModCauldronBehavior extends CauldronBehavior {
	Map<Item, CauldronBehavior> QUICKSAND_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();

	static void init() {
		CauldronLib.registerFillFromBucketBehavior(ModBlocks.QUICKSAND_BUCKET, ModBlocks.QUICKSAND_CAULDRON, SoundEvents.BLOCK_SAND_PLACE);

		QUICKSAND_CAULDRON_BEHAVIOR.put(Items.BUCKET, CauldronLib.createEmptyBehavior(ModBlocks.QUICKSAND_BUCKET, SoundEvents.BLOCK_SAND_BREAK));
	}
}
