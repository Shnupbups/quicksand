package com.shnupbups.quicksand.datagen.provider;

import com.shnupbups.quicksand.registry.QuicksandTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.biome.Biome;

import java.util.concurrent.CompletableFuture;

public class QuicksandBiomeTagProvider extends FabricTagProvider<Biome> {
    public QuicksandBiomeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BIOME, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        getOrCreateTagBuilder(QuicksandTags.HAS_QUICKSAND_LAKES).forceAddTag(ConventionalBiomeTags.DESERT);
        getOrCreateTagBuilder(QuicksandTags.HAS_RED_QUICKSAND_LAKES).forceAddTag(ConventionalBiomeTags.BADLANDS);
    }
}
