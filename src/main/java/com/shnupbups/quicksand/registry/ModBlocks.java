package com.shnupbups.quicksand.registry;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PowderSnowBucketItem;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.registry.Registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
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
		QUICKSAND_BUCKET = new PowderSnowBucketItem(QUICKSAND, SoundEvents.BLOCK_SAND_PLACE, new FabricItemSettings().maxCount(1).group(ItemGroup.MISC));
		QUICKSAND_CAULDRON =  new FullCauldronBlock(FabricBlockSettings.copyOf(Blocks.CAULDRON), ModCauldronBehavior.QUICKSAND_CAULDRON_BEHAVIOR);

		RED_QUICKSAND = new QuicksandBlock(FabricBlockSettings.copyOf(Blocks.RED_SAND), 11098145, () -> RED_QUICKSAND_BUCKET);
		RED_QUICKSAND_BUCKET = new PowderSnowBucketItem(RED_QUICKSAND, SoundEvents.BLOCK_SAND_PLACE, new FabricItemSettings().maxCount(1).group(ItemGroup.MISC));
		RED_QUICKSAND_CAULDRON =  new FullCauldronBlock(FabricBlockSettings.copyOf(Blocks.CAULDRON), ModCauldronBehavior.RED_QUICKSAND_CAULDRON_BEHAVIOR);

		register();
	}

	private static void register() {
		Registry.register(Registry.BLOCK, Quicksand.id("quicksand"), QUICKSAND);
		Registry.register(Registry.ITEM, Quicksand.id("quicksand_bucket"), QUICKSAND_BUCKET);
		QUICKSAND_BUCKET.appendBlocks(Item.BLOCK_ITEMS, QUICKSAND_BUCKET);
		Registry.register(Registry.BLOCK, Quicksand.id("quicksand_cauldron"), QUICKSAND_CAULDRON);

		Registry.register(Registry.BLOCK, Quicksand.id("red_quicksand"), RED_QUICKSAND);
		Registry.register(Registry.ITEM, Quicksand.id("red_quicksand_bucket"), RED_QUICKSAND_BUCKET);
		RED_QUICKSAND_BUCKET.appendBlocks(Item.BLOCK_ITEMS, RED_QUICKSAND_BUCKET);
		Registry.register(Registry.BLOCK, Quicksand.id("red_quicksand_cauldron"), RED_QUICKSAND_CAULDRON);
	}
}
