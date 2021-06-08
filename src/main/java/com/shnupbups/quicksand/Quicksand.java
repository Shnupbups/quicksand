package com.shnupbups.quicksand;

import com.shnupbups.quicksand.block.QuicksandBlock;
import com.shnupbups.quicksand.block.QuicksandCauldronBlock;
import com.shnupbups.quicksand.registry.ModBlocksAndItems;
import com.shnupbups.quicksand.registry.ModCauldronBehavior;
import com.shnupbups.quicksand.registry.ModConfiguredFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tag.TagRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PowderSnowBucketItem;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Quicksand implements ModInitializer {
	public static final String MOD_ID = "quicksand";

	public static final Tag<EntityType<?>> QUICKSAND_WALKABLE_MOBS = TagRegistry.entityType(id("quicksand_walkable_mobs"));

	public static final DamageSource QUICKSAND_DAMAGE = new DamageSource("quicksand") {
		@Override
		public boolean bypassesArmor() {
			return true;
		}
	};

	@Override
	public void onInitialize() {
		ModBlocksAndItems.init();
		ModCauldronBehavior.init();
		ModConfiguredFeatures.init();
	}

	public static Identifier id(String id) {
		return new Identifier(MOD_ID, id);
	}
}
