package com.shnupbups.quicksand;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.EntityShapeContext;
import net.minecraft.block.FluidDrainable;
import net.minecraft.block.SandBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.Optional;
import java.util.Random;

public class QuicksandBlock extends SandBlock implements FluidDrainable {
	public QuicksandBlock(AbstractBlock.Settings settings) {
		super(14076051, settings);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
		return stateFrom.isOf(this) || super.isSideInvisible(state, stateFrom, direction);
	}

	@Override
	public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
		return VoxelShapes.empty();
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if (!(entity instanceof LivingEntity) || ((LivingEntity)entity).getBlockState().isOf(Quicksand.QUICKSAND)) {
			entity.slowMovement(state, new Vec3d(0.6D, 0.4D, 0.6D));
		}

		if(world.getRandom().nextBoolean()) {
			if (entity instanceof LivingEntity && world.getBlockState(new BlockPos(entity.getBlockX(), entity.getEyeY() - 0.1111111119389534D, entity.getBlockZ())).isOf(Quicksand.QUICKSAND)) {
				LivingEntity living = (LivingEntity)entity;
				living.damage(Quicksand.QUICKSAND_DAMAGE, 1f);
			}

			if (!entity.isSpectator() && (entity.lastRenderX != entity.getX() || entity.lastRenderZ != entity.getZ())) {
				spawnParticles(world, state, new Vec3d(entity.getX(), pos.getY(), entity.getZ()));
			}
		}
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		if (context instanceof EntityShapeContext) {
			EntityShapeContext entityShapeContext = (EntityShapeContext)context;
			Optional<Entity> optional = entityShapeContext.getEntity();
			if (optional.isPresent()) {
				if(optional.get() instanceof FallingBlockEntity || (canWalkOnQuicksand(optional.get()) && context.isAbove(VoxelShapes.fullCube(), pos, false) && !context.isDescending())) {
					return super.getCollisionShape(state, world, pos, context);
				}
			}
		}

		return VoxelShapes.empty();
	}

	@Override
	public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return VoxelShapes.empty();
	}

	public static void spawnParticles(World world, BlockState state, Vec3d pos) {
		if (world.isClient) {
			Random random = world.getRandom();
			double d = pos.y + 1.0D;

			for(int i = 0; i < random.nextInt(3); ++i) {
				world.addParticle(new BlockStateParticleEffect(ParticleTypes.FALLING_DUST, state), pos.x, d, pos.z, (-1.0F + random.nextFloat() * 2.0F) / 12.0F, 0.05000000074505806D, (-1.0F + random.nextFloat() * 2.0F) / 12.0F);
			}
		}
	}

	public static boolean canWalkOnQuicksand(Entity entity) {
		if (entity.getType().isIn(Quicksand.QUICKSAND_WALKABLE_MOBS)) {
			return true;
		} else {
			return entity instanceof LivingEntity && ((LivingEntity) entity).getEquippedStack(EquipmentSlot.FEET).isOf(Items.LEATHER_BOOTS);
		}
	}

	@Override
	public ItemStack tryDrainFluid(WorldAccess world, BlockPos pos, BlockState state) {
		world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
		if (!world.isClient()) {
			world.syncWorldEvent(2001, pos, Block.getRawIdFromState(state));
		}

		return new ItemStack(Quicksand.QUICKSAND_BUCKET);
	}

	@Override
	public Optional<SoundEvent> getBucketFillSound() {
		return Optional.of(SoundEvents.BLOCK_SAND_BREAK);
	}

	@Override
	protected void configureFallingBlockEntity(FallingBlockEntity entity) {
		entity.dropItem = false;
	}
}
