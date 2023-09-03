package com.shnupbups.quicksand.registry;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.PowderSnowBucketItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

import com.shnupbups.cauldronlib.block.FullCauldronBlock;
import com.shnupbups.quicksand.Quicksand;
import com.shnupbups.quicksand.block.QuicksandBlock;

public class ModBlocks {
	public static Block QUICKSAND;
	public static BlockItem QUICKSAND_BUCKET;
	public static Block QUICKSAND_CAULDRON;
	public static Block RED_QUICKSAND;
	public static BlockItem RED_QUICKSAND_BUCKET;
	public static Block RED_QUICKSAND_CAULDRON;

	public static void init() {
		QUICKSAND = new QuicksandBlock(FabricBlockSettings.copyOf(Blocks.SAND), 14406560, () -> QUICKSAND_BUCKET);
		QUICKSAND_BUCKET = new PowderSnowBucketItem(QUICKSAND, SoundEvents.BLOCK_SAND_PLACE, new FabricItemSettings().maxCount(1));
		QUICKSAND_CAULDRON =  new FullCauldronBlock(FabricBlockSettings.copyOf(Blocks.CAULDRON), ModCauldronBehavior.QUICKSAND_CAULDRON_BEHAVIOR);

		RED_QUICKSAND = new QuicksandBlock(FabricBlockSettings.copyOf(Blocks.RED_SAND), 11098145, () -> RED_QUICKSAND_BUCKET);
		RED_QUICKSAND_BUCKET = new PowderSnowBucketItem(RED_QUICKSAND, SoundEvents.BLOCK_SAND_PLACE, new FabricItemSettings().maxCount(1));
		RED_QUICKSAND_CAULDRON =  new FullCauldronBlock(FabricBlockSettings.copyOf(Blocks.CAULDRON), ModCauldronBehavior.RED_QUICKSAND_CAULDRON_BEHAVIOR);

		register();

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
			entries.addBefore(Items.POWDER_SNOW_BUCKET, QUICKSAND_BUCKET, RED_QUICKSAND_BUCKET);
		});
	}

	private static void register() {
		Registry.register(Registries.BLOCK, Quicksand.id("quicksand"), QUICKSAND);
		Registry.register(Registries.ITEM, Quicksand.id("quicksand_bucket"), QUICKSAND_BUCKET);
		QUICKSAND_BUCKET.appendBlocks(Item.BLOCK_ITEMS, QUICKSAND_BUCKET);
		Registry.register(Registries.BLOCK, Quicksand.id("quicksand_cauldron"), QUICKSAND_CAULDRON);

		Registry.register(Registries.BLOCK, Quicksand.id("red_quicksand"), RED_QUICKSAND);
		Registry.register(Registries.ITEM, Quicksand.id("red_quicksand_bucket"), RED_QUICKSAND_BUCKET);
		RED_QUICKSAND_BUCKET.appendBlocks(Item.BLOCK_ITEMS, RED_QUICKSAND_BUCKET);
		Registry.register(Registries.BLOCK, Quicksand.id("red_quicksand_cauldron"), RED_QUICKSAND_CAULDRON);
	}
}
