package com.shnupbups.quicksand.registry;

import com.shnupbups.quicksand.Quicksand;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;

public class ModConfiguredFeatures extends ConfiguredFeatures {
	public static final RegistryKey<ConfiguredFeature<?, ?>> QUICKSAND_LAKE_FEATURE_KEY = RegistryKey.of(BuiltinRegistries.CONFIGURED_FEATURE.getKey(), Quicksand.id("quicksand_lake"));
	public static final ConfiguredFeature<?, ?> QUICKSAND_LAKE_CONFIGURED_FEATURE = Feature.LAKE.configure(new SingleStateFeatureConfig(ModBlocksAndItems.QUICKSAND.getDefaultState())).range(Decorators.BOTTOM_TO_TOP).spreadHorizontally().applyChance(4);

	public static void init() {

		BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, Quicksand.id("quicksand_lake"), QUICKSAND_LAKE_CONFIGURED_FEATURE);

		BiomeModifications.create(Quicksand.id("quicksand")).add(ModificationPhase.ADDITIONS, (biomeSelectionContext) ->
				biomeSelectionContext.getBiomeKey().equals(BiomeKeys.DESERT), (biomeSelectionContext, biomeModificationContext) ->
					biomeModificationContext.getGenerationSettings().addFeature(GenerationStep.Feature.LAKES, QUICKSAND_LAKE_FEATURE_KEY)
		);
	}
}
