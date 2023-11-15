package com.shnupbups.quicksand.datagen;

import com.shnupbups.quicksand.registry.QuicksandBlocks;
import com.shnupbups.quicksand.registry.QuicksandDamage;
import com.shnupbups.quicksand.registry.QuicksandFeatures;
import net.minecraft.block.Block;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;

public class QuicksandBootstrappers {
    public static void bootstrapDamageTypes(Registerable<DamageType> registerable) {
        registerable.register(QuicksandDamage.QUICKSAND_DAMAGE_TYPE, new DamageType("quicksand", 0.0f));
    }

    public static void bootstrapConfiguredFeatures(Registerable<ConfiguredFeature<?,?>> registerable) {
        ConfiguredFeatures.register(registerable, QuicksandFeatures.QUICKSAND_LAKE_CONFIGURED_FEATURE_KEY, Feature.LAKE,
                createConfig(QuicksandBlocks.QUICKSAND)
        );

        ConfiguredFeatures.register(registerable, QuicksandFeatures.RED_QUICKSAND_LAKE_CONFIGURED_FEATURE_KEY, Feature.LAKE,
                createConfig(QuicksandBlocks.RED_QUICKSAND)
        );
    }

    public static void bootstrapPlacedFeatures(Registerable<PlacedFeature> registerable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> configuredFeatureLookup = registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        PlacedFeatures.register(registerable, QuicksandFeatures.QUICKSAND_LAKE_PLACED_FEATURE_KEY,
                configuredFeatureLookup.getOrThrow(QuicksandFeatures.QUICKSAND_LAKE_CONFIGURED_FEATURE_KEY),
                modifiers(20)
        );

        PlacedFeatures.register(registerable, QuicksandFeatures.RED_QUICKSAND_LAKE_PLACED_FEATURE_KEY,
                configuredFeatureLookup.getOrThrow(QuicksandFeatures.RED_QUICKSAND_LAKE_CONFIGURED_FEATURE_KEY),
                modifiers(30)
        );
    }

    public static LakeFeature.Config createConfig(Block block) {
        BlockStateProvider provider = BlockStateProvider.of(block);
        return new LakeFeature.Config(provider, provider);
    }

    public static List<PlacementModifier> modifiers(int rarity) {
        return List.of(RarityFilterPlacementModifier.of(rarity), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
    }
}
