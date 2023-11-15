package com.shnupbups.quicksand.datagen;

import com.shnupbups.quicksand.datagen.provider.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class QuicksandDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(QuicksandDynamicRegistryProvider::new);
        pack.addProvider(QuicksandModelProvider::new);
        pack.addProvider(QuicksandBlockTagProvider::new);
        pack.addProvider(QuicksandEntityTypeTagProvider::new);
        pack.addProvider(QuicksandBlockLootTableProvider::new);
        pack.addProvider(QuicksandBiomeTagProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, QuicksandBootstrappers::bootstrapConfiguredFeatures);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, QuicksandBootstrappers::bootstrapPlacedFeatures);
        registryBuilder.addRegistry(RegistryKeys.DAMAGE_TYPE, QuicksandBootstrappers::bootstrapDamageTypes);
    }
}
