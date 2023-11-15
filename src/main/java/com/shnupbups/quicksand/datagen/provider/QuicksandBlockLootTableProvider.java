package com.shnupbups.quicksand.datagen.provider;

import com.shnupbups.quicksand.registry.QuicksandBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Blocks;

public class QuicksandBlockLootTableProvider extends FabricBlockLootTableProvider {
    public QuicksandBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        this.addDrop(QuicksandBlocks.QUICKSAND, dropsNothing());
        this.addDrop(QuicksandBlocks.RED_QUICKSAND, dropsNothing());
        this.addDrop(QuicksandBlocks.QUICKSAND_CAULDRON, Blocks.CAULDRON);
        this.addDrop(QuicksandBlocks.RED_QUICKSAND_CAULDRON, Blocks.CAULDRON);
    }
}
