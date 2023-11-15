package com.shnupbups.quicksand.datagen.provider;

import com.shnupbups.quicksand.registry.QuicksandDamage;
import com.shnupbups.quicksand.registry.QuicksandFeatures;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.concurrent.CompletableFuture;

public class QuicksandDynamicRegistryProvider extends FabricDynamicRegistryProvider {
    public QuicksandDynamicRegistryProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        addPlacedFeatures(registries, entries);
        addConfiguredFeatures(registries, entries);
        addDamageTypes(registries, entries);
    }

    public void addDamageTypes(RegistryWrapper.WrapperLookup registries, Entries entries) {
        addDamageType(registries, entries, QuicksandDamage.QUICKSAND_DAMAGE_TYPE);
    }

    public void addPlacedFeatures(RegistryWrapper.WrapperLookup registries, Entries entries) {
        addPlacedFeature(registries, entries, QuicksandFeatures.QUICKSAND_LAKE_PLACED_FEATURE_KEY);
        addPlacedFeature(registries, entries, QuicksandFeatures.RED_QUICKSAND_LAKE_PLACED_FEATURE_KEY);
    }

    public void addConfiguredFeatures(RegistryWrapper.WrapperLookup registries, Entries entries) {
        addConfiguredFeature(registries, entries, QuicksandFeatures.QUICKSAND_LAKE_CONFIGURED_FEATURE_KEY);
        addConfiguredFeature(registries, entries, QuicksandFeatures.RED_QUICKSAND_LAKE_CONFIGURED_FEATURE_KEY);
    }

    public void addDamageType(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<DamageType> key) {
        addEntry(registries, entries, RegistryKeys.DAMAGE_TYPE, key);
    }

    public void addConfiguredFeature(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<ConfiguredFeature<?, ?>> key) {
        addEntry(registries, entries, RegistryKeys.CONFIGURED_FEATURE, key);
    }

    public void addPlacedFeature(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<PlacedFeature> key) {
        addEntry(registries, entries, RegistryKeys.PLACED_FEATURE, key);
    }

    public <T> void addEntry(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<Registry<T>> registryKey, RegistryKey<T> key) {
        entries.add(key, registries.getWrapperOrThrow(registryKey).getOrThrow(key).value());
    }

    @Override
    public String getName() {
        return "Quicksand Features";
    }
}
