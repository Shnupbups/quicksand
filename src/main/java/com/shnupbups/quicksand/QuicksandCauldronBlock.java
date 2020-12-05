package com.shnupbups.quicksand;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class QuicksandCauldronBlock extends AbstractCauldronBlock {
	public QuicksandCauldronBlock(AbstractBlock.Settings settings) {
		super(settings, ModCauldronBehavior.QUICKSAND_CAULDRON_BEHAVIOR);
	}

	@Override
	protected double getFluidHeight(BlockState state) {
		return 0.9375D;
	}

	@Override
	public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
		return 1;
	}
}
