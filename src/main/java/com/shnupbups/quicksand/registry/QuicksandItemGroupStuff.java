package com.shnupbups.quicksand.registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;

public class QuicksandItemGroupStuff {
    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register((entries) -> {
            entries.addAfter(Items.POWDER_SNOW_BUCKET,
                    QuicksandBlocks.QUICKSAND_BUCKET, QuicksandBlocks.RED_QUICKSAND_BUCKET
            );
        });
    }
}
