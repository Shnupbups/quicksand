package com.shnupbups.quicksand.registry;

import net.minecraft.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.LakeFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;

import com.shnupbups.quicksand.Quicksand;

public class ModFeatures extends ConfiguredFeatures {
	public static final Identifier QUICKSAND_LAKE_ID = Quicksand.id("quicksand_lake");
	public static final Identifier RED_QUICKSAND_LAKE_ID = Quicksand.id("red_quicksand_lake");

	public static final RegistryEntry<ConfiguredFeature<LakeFeature.Config, ?>> QUICKSAND_LAKE_CONFIGURED_FEATURE = ConfiguredFeatures.register(QUICKSAND_LAKE_ID.toString(), Feature.LAKE, new LakeFeature.Config(BlockStateProvider.of(ModBlocks.QUICKSAND.getDefaultState()), BlockStateProvider.of(ModBlocks.QUICKSAND.getDefaultState())));
	public static final RegistryEntry<PlacedFeature> QUICKSAND_LAKE_PLACED_FEATURE = PlacedFeatures.register(QUICKSAND_LAKE_ID.toString(), QUICKSAND_LAKE_CONFIGURED_FEATURE, RarityFilterPlacementModifier.of(30), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());

	public static final RegistryEntry<ConfiguredFeature<LakeFeature.Config, ?>> RED_QUICKSAND_LAKE_CONFIGURED_FEATURE = ConfiguredFeatures.register(RED_QUICKSAND_LAKE_ID.toString(), Feature.LAKE, new LakeFeature.Config(BlockStateProvider.of(ModBlocks.RED_QUICKSAND.getDefaultState()), BlockStateProvider.of(ModBlocks.RED_QUICKSAND.getDefaultState())));
	public static final RegistryEntry<PlacedFeature> RED_QUICKSAND_LAKE_PLACED_FEATURE = PlacedFeatures.register(RED_QUICKSAND_LAKE_ID.toString(), RED_QUICKSAND_LAKE_CONFIGURED_FEATURE, RarityFilterPlacementModifier.of(50), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());

	public static void init() {
		BiomeModifications.create(QUICKSAND_LAKE_ID).add(ModificationPhase.ADDITIONS, (biomeSelectionContext) ->
				biomeSelectionContext.getBiomeKey().equals(BiomeKeys.DESERT), (biomeSelectionContext, biomeModificationContext) ->
				biomeModificationContext.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.LAKES, QUICKSAND_LAKE_PLACED_FEATURE.value())
		);

		BiomeModifications.create(RED_QUICKSAND_LAKE_ID).add(ModificationPhase.ADDITIONS, (biomeSelectionContext) ->
				biomeSelectionContext.getBiomeRegistryEntry().isIn(BiomeTags.IS_BADLANDS), (biomeSelectionContext, biomeModificationContext) ->
				biomeModificationContext.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.LAKES, RED_QUICKSAND_LAKE_PLACED_FEATURE.value())
		);
	}
}
