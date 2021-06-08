package com.shnupbups.quicksand.registry;

import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;

import java.util.Map;

public interface ModCauldronBehavior extends CauldronBehavior {
	Map<Item, CauldronBehavior> QUICKSAND_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();

	CauldronBehavior FILL_WITH_QUICKSAND = (state, world, pos, player, hand, stack) ->
			CauldronBehavior.fillCauldron(world, pos, player, hand, stack, ModBlocksAndItems.QUICKSAND_CAULDRON.getDefaultState(), SoundEvents.BLOCK_SAND_PLACE);

	static void init() {
		EMPTY_CAULDRON_BEHAVIOR.put(ModBlocksAndItems.QUICKSAND_BUCKET, FILL_WITH_QUICKSAND);
		WATER_CAULDRON_BEHAVIOR.put(ModBlocksAndItems.QUICKSAND_BUCKET, FILL_WITH_QUICKSAND);
		LAVA_CAULDRON_BEHAVIOR.put(ModBlocksAndItems.QUICKSAND_BUCKET, FILL_WITH_QUICKSAND);
		POWDER_SNOW_CAULDRON_BEHAVIOR.put(ModBlocksAndItems.QUICKSAND_BUCKET, FILL_WITH_QUICKSAND);

		QUICKSAND_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) ->
				CauldronBehavior.emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(ModBlocksAndItems.QUICKSAND_BUCKET), (statex) -> true, SoundEvents.BLOCK_SAND_BREAK));
		QUICKSAND_CAULDRON_BEHAVIOR.put(Items.WATER_BUCKET, FILL_WITH_WATER);
		QUICKSAND_CAULDRON_BEHAVIOR.put(Items.LAVA_BUCKET, FILL_WITH_LAVA);
		QUICKSAND_CAULDRON_BEHAVIOR.put(Items.POWDER_SNOW_BUCKET, FILL_WITH_POWDER_SNOW);
	}
}
