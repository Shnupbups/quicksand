package com.shnupbups.quicksand;

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
		return 1;
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if (this.isEntityTouchingFluid(state, pos, entity)) {
			entity.slowMovement(state, new Vec3d(0.6D, 0.4D, 0.6D));
		}
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		if (context instanceof EntityShapeContext) {
			EntityShapeContext entityShapeContext = (EntityShapeContext) context;
			Optional<Entity> optional = entityShapeContext.getEntity();
			if (optional.isPresent()) {
				if (optional.get() instanceof FallingBlockEntity || (QuicksandBlock.canWalkOnQuicksand(optional.get()) && context.isAbove(VoxelShapes.fullCube(), pos, false) && !context.isDescending())) {
					return VoxelShapes.fullCube();
				}
			}
		}

		return super.getCollisionShape(state, world, pos, context);
	}
}
