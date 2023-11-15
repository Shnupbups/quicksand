package com.shnupbups.quicksand.registry;

import java.util.Map;

import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;

import com.shnupbups.cauldronlib.CauldronLib;

public interface QuicksandCauldronBehavior extends CauldronBehavior {
	Map<Item, CauldronBehavior> QUICKSAND_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
	Map<Item, CauldronBehavior> RED_QUICKSAND_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();

	static void init() {
		CauldronLib.registerFillFromBucketBehavior(QuicksandBlocks.QUICKSAND_BUCKET, QuicksandBlocks.QUICKSAND_CAULDRON, SoundEvents.BLOCK_SAND_PLACE);
		CauldronLib.registerFillFromBucketBehavior(QuicksandBlocks.RED_QUICKSAND_BUCKET, QuicksandBlocks.RED_QUICKSAND_CAULDRON, SoundEvents.BLOCK_SAND_PLACE);

		QUICKSAND_CAULDRON_BEHAVIOR.put(Items.BUCKET, CauldronLib.createEmptyBehavior(QuicksandBlocks.QUICKSAND_BUCKET, SoundEvents.BLOCK_SAND_BREAK));
		RED_QUICKSAND_CAULDRON_BEHAVIOR.put(Items.BUCKET, CauldronLib.createEmptyBehavior(QuicksandBlocks.RED_QUICKSAND_BUCKET, SoundEvents.BLOCK_SAND_BREAK));
	}
}
