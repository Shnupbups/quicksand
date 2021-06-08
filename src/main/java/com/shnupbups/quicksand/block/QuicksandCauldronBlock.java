package com.shnupbups.quicksand.block;

import com.shnupbups.quicksand.registry.ModCauldronBehavior;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.EntityShapeContext;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Optional;

public class QuicksandCauldronBlock extends AbstractCauldronBlock {
	public QuicksandCauldronBlock(AbstractBlock.Settings settings) {
		super(settings, ModCauldronBehavior.QUICKSAND_CAULDRON_BEHAVIOR);
	}

	@Override
	public boolean isFull(BlockState state) {
		return true;
	}

	@Override
	protected double getFluidHeight(BlockState state) {
		return 0.9375D;
	}

	@Override
	public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
		return 3;
	}
}
