package com.shnupbups.quicksand;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
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
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;

public class Quicksand implements ModInitializer {
	public static final String MOD_ID = "quicksand";

	public static final Tag<EntityType<?>> QUICKSAND_WALKABLE_MOBS = TagRegistry.entityType(id("quicksand_walkable_mobs"));

	public static final Block QUICKSAND = new QuicksandBlock(FabricBlockSettings.copyOf(Blocks.SAND));
	public static final BlockItem QUICKSAND_BUCKET = new PowderSnowBucketItem(QUICKSAND, SoundEvents.BLOCK_SAND_PLACE, new FabricItemSettings().maxCount(1).group(ItemGroup.MISC));
	public static final Block QUICKSAND_CAULDRON = new QuicksandCauldronBlock(FabricBlockSettings.copyOf(Blocks.CAULDRON));

	public static final DamageSource QUICKSAND_DAMAGE = new DamageSource("quicksand") {
		@Override
		public boolean bypassesArmor() {
			return true;
		}
	};

	public static final RegistryKey<ConfiguredFeature<?, ?>> QUICKSAND_LAKE_FEATURE_KEY = RegistryKey.of(BuiltinRegistries.CONFIGURED_FEATURE.getKey(), id("quicksand_lake"));
	public static final ConfiguredFeature<?, ?> QUICKSAND_LAKE_CONFIGURED_FEATURE
			= Feature.LAKE.configure(new SingleStateFeatureConfig(QUICKSAND.getDefaultState()))
			.range(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.getBottom(), YOffset.getTop())))
			.spreadHorizontally().applyChance(4);

	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, Quicksand.id("quicksand"), QUICKSAND);
		Registry.register(Registry.ITEM, Quicksand.id("quicksand_bucket"), QUICKSAND_BUCKET);
		QUICKSAND_BUCKET.appendBlocks(Item.BLOCK_ITEMS, QUICKSAND_BUCKET);
		ModCauldronBehavior.init();
		Registry.register(Registry.BLOCK, Quicksand.id("quicksand_cauldron"), QUICKSAND_CAULDRON);

		BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, Quicksand.id("quicksand_lake"), QUICKSAND_LAKE_CONFIGURED_FEATURE);

		BiomeModifications.create(id("quicksand")).add(ModificationPhase.ADDITIONS, (biomeSelectionContext) -> biomeSelectionContext.getBiomeKey().equals(BiomeKeys.DESERT), (biomeSelectionContext, biomeModificationContext) -> {
			biomeModificationContext.getGenerationSettings().addFeature(GenerationStep.Feature.LAKES, QUICKSAND_LAKE_FEATURE_KEY);
		});
	}

	public static Identifier id(String id) {
		return new Identifier(MOD_ID, id);
	}
}
