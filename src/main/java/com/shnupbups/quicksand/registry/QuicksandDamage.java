package com.shnupbups.quicksand.registry;

import com.shnupbups.quicksand.Quicksand;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class QuicksandDamage {
    public static final RegistryKey<DamageType> QUICKSAND_DAMAGE_TYPE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Quicksand.id("quicksand"));

    private static DamageSources cachedSources;

    public static DamageSource quicksand;

    public static DamageSource quicksand(DamageSources sources) {
        if(quicksand == null || cachedSources != sources) {
            cachedSources = sources;
            quicksand = sources.create(QUICKSAND_DAMAGE_TYPE);
        }
        return quicksand;
    }
}
