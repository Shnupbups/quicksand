package com.shnupbups.quicksand.registry;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;

import com.shnupbups.quicksand.Quicksand;

public class ModFeatures {
	public static final Identifier QUICKSAND_LAKE_ID = Quicksand.id("quicksand_lake");
	public static final Identifier RED_QUICKSAND_LAKE_ID = Quicksand.id("red_quicksand_lake");

	public static final RegistryKey<PlacedFeature> QUICKSAND_LAKE_PLACED_FEATURE = RegistryKey.of(RegistryKeys.PLACED_FEATURE, QUICKSAND_LAKE_ID);
	public static final RegistryKey<PlacedFeature> RED_QUICKSAND_LAKE_PLACED_FEATURE = RegistryKey.of(RegistryKeys.PLACED_FEATURE, RED_QUICKSAND_LAKE_ID);

	public static void init() {
		BiomeModifications.create(QUICKSAND_LAKE_ID).add(ModificationPhase.ADDITIONS, (biomeSelectionContext) ->
				biomeSelectionContext.hasTag(ConventionalBiomeTags.DESERT), (biomeSelectionContext, biomeModificationContext) ->
				biomeModificationContext.getGenerationSettings().addFeature(GenerationStep.Feature.LAKES, QUICKSAND_LAKE_PLACED_FEATURE)
		);

		BiomeModifications.create(RED_QUICKSAND_LAKE_ID).add(ModificationPhase.ADDITIONS, (biomeSelectionContext) ->
				biomeSelectionContext.hasTag(ConventionalBiomeTags.BADLANDS), (biomeSelectionContext, biomeModificationContext) ->
				biomeModificationContext.getGenerationSettings().addFeature(GenerationStep.Feature.LAKES, RED_QUICKSAND_LAKE_PLACED_FEATURE)
		);
	}
}
