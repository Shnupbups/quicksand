package com.shnupbups.quicksand.datagen.provider;

import com.shnupbups.quicksand.registry.QuicksandTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class QuicksandEntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {
    public QuicksandEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        getOrCreateTagBuilder(QuicksandTags.QUICKSAND_WALKABLE_MOBS).add(EntityType.HUSK).add(EntityType.RABBIT);
        getOrCreateTagBuilder(QuicksandTags.SURVIVES_IN_QUICKSAND)
                .add(EntityType.IRON_GOLEM).add(EntityType.SKELETON).add(EntityType.STRAY).add(EntityType.ZOMBIFIED_PIGLIN)
                .add(EntityType.ZOGLIN).add(EntityType.ZOMBIE).add(EntityType.HUSK);
    }
}
