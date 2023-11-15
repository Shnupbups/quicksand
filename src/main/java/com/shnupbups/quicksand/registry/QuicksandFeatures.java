package com.shnupbups.quicksand.registry;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;

import com.shnupbups.quicksand.Quicksand;

public class QuicksandFeatures {
	public static final Identifier QUICKSAND_LAKE_ID = Quicksand.id("quicksand_lake");
	public static final Identifier RED_QUICKSAND_LAKE_ID = Quicksand.id("red_quicksand_lake");

	public static final RegistryKey<ConfiguredFeature<?, ?>> QUICKSAND_LAKE_CONFIGURED_FEATURE_KEY = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, QUICKSAND_LAKE_ID);
	public static final RegistryKey<ConfiguredFeature<?, ?>> RED_QUICKSAND_LAKE_CONFIGURED_FEATURE_KEY = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, RED_QUICKSAND_LAKE_ID);

	public static final RegistryKey<PlacedFeature> QUICKSAND_LAKE_PLACED_FEATURE_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, QUICKSAND_LAKE_ID);
	public static final RegistryKey<PlacedFeature> RED_QUICKSAND_LAKE_PLACED_FEATURE_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, RED_QUICKSAND_LAKE_ID);

	public static void init() {
		BiomeModifications.create(QUICKSAND_LAKE_ID).add(ModificationPhase.ADDITIONS, (biomeSelectionContext) ->
				biomeSelectionContext.hasTag(QuicksandTags.HAS_QUICKSAND_LAKES), (biomeSelectionContext, biomeModificationContext) ->
				biomeModificationContext.getGenerationSettings().addFeature(GenerationStep.Feature.LAKES, QUICKSAND_LAKE_PLACED_FEATURE_KEY)
		);

		BiomeModifications.create(RED_QUICKSAND_LAKE_ID).add(ModificationPhase.ADDITIONS, (biomeSelectionContext) ->
				biomeSelectionContext.hasTag(QuicksandTags.HAS_RED_QUICKSAND_LAKES), (biomeSelectionContext, biomeModificationContext) ->
				biomeModificationContext.getGenerationSettings().addFeature(GenerationStep.Feature.LAKES, RED_QUICKSAND_LAKE_PLACED_FEATURE_KEY)
		);
	}
}
