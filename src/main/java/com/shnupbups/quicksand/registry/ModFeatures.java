package com.shnupbups.quicksand.registry;

import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.RarityFilterPlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.LakeFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;

import com.shnupbups.quicksand.Quicksand;

public class ModFeatures extends ConfiguredFeatures {
	public static final Identifier QUICKSAND_LAKE_ID = Quicksand.id("quicksand_lake");
	public static final ConfiguredFeature<?, ?> QUICKSAND_LAKE_CONFIGURED_FEATURE = Feature.LAKE.configure(new LakeFeature.Config(BlockStateProvider.of(ModBlocks.QUICKSAND), BlockStateProvider.of(Blocks.SAND)));
	public static final PlacedFeature QUICKSAND_LAKE_PLACED_FEATURE = QUICKSAND_LAKE_CONFIGURED_FEATURE.withPlacement(RarityFilterPlacementModifier.of(30), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());

	public static void init() {
		BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, QUICKSAND_LAKE_ID, QUICKSAND_LAKE_CONFIGURED_FEATURE);
		BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, QUICKSAND_LAKE_ID, QUICKSAND_LAKE_PLACED_FEATURE);

		BiomeModifications.create(QUICKSAND_LAKE_ID).add(ModificationPhase.ADDITIONS, (biomeSelectionContext) ->
				biomeSelectionContext.getBiomeKey().equals(BiomeKeys.DESERT), (biomeSelectionContext, biomeModificationContext) ->
				biomeModificationContext.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.LAKES, QUICKSAND_LAKE_PLACED_FEATURE)
		);
	}
}
