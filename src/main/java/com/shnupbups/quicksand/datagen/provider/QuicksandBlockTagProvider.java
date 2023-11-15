package com.shnupbups.quicksand.datagen.provider;

import com.shnupbups.quicksand.registry.QuicksandBlocks;
import com.shnupbups.quicksand.registry.QuicksandTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class QuicksandBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public QuicksandBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        getOrCreateTagBuilder(QuicksandTags.QUICKSAND).add(QuicksandBlocks.QUICKSAND).add(QuicksandBlocks.RED_QUICKSAND);
        getOrCreateTagBuilder(QuicksandTags.QUICKSAND_CAULDRONS).add(QuicksandBlocks.QUICKSAND_CAULDRON).add(QuicksandBlocks.RED_QUICKSAND_CAULDRON);
        getOrCreateTagBuilder(BlockTags.CAULDRONS).addTag(QuicksandTags.QUICKSAND_CAULDRONS);
        getOrCreateTagBuilder(BlockTags.SAND).addTag(QuicksandTags.QUICKSAND);
        getOrCreateTagBuilder(BlockTags.INSIDE_STEP_SOUND_BLOCKS).addTag(QuicksandTags.QUICKSAND);
        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).addTag(QuicksandTags.QUICKSAND);
    }
}
