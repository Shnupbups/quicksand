package com.shnupbups.quicksand.block;

import java.util.Optional;
import java.util.function.Supplier;

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
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import com.shnupbups.quicksand.Quicksand;
import com.shnupbups.quicksand.registry.ModTags;

public class QuicksandBlock extends SandBlock implements FluidDrainable {
	private static final VoxelShape FALLING_SHAPE = VoxelShapes.cuboid(0.0, 0.0, 0.0, 1.0, 0.9f, 1.0);

	public final Supplier<Item> bucket;

	public QuicksandBlock(AbstractBlock.Settings settings, int color, Supplier<Item> bucket) {
		super(color, settings);
		this.bucket = bucket;
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
		if (entity.getBlockStateAtPos().isIn(ModTags.QUICKSAND)) {
			entity.slowMovement(state, new Vec3d(0.6D, 0.4D, 0.6D));
		}

		if (!entity.isSpectator() && (hasEntityMoved(entity) || world.getRandom().nextFloat() < 0.2)) {
			if (!entity.getType().isIn(ModTags.SURVIVES_IN_QUICKSAND) && entity instanceof LivingEntity living && world.getBlockState(new BlockPos(entity.getBlockX(), entity.getEyeY() - 0.11, entity.getBlockZ())).isIn(ModTags.QUICKSAND)) {
				living.damage(Quicksand.QUICKSAND_DAMAGE, 1f);
			}

			if(world.getRandom().nextBoolean()) spawnParticles(world, state, new Vec3d(entity.getX(), pos.getY(), entity.getZ()));
		}
	}

	public boolean hasEntityMoved(Entity entity) {
		return entity.lastRenderX != entity.getX() || entity.lastRenderY != entity.getY() || entity.lastRenderZ != entity.getZ() ||
				entity.prevYaw != entity.getYaw() || entity.prevPitch != entity.getPitch();
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		if (context instanceof EntityShapeContext entityShapeContext) {
			Entity entity = entityShapeContext.getEntity();
			if (entity != null) {
				if (entity instanceof FallingBlockEntity || (canWalkOnQuicksand(entity) && context.isAbove(VoxelShapes.fullCube(), pos, false) && !context.isDescending())) {
					return super.getCollisionShape(state, world, pos, context);
				} else if (entity.fallDistance > 2.5f) {
					return FALLING_SHAPE;
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

			for (int i = 0; i < random.nextInt(3); ++i) {
				world.addParticle(new BlockStateParticleEffect(ParticleTypes.FALLING_DUST, state), pos.x+(MathHelper.nextBetween(random, -1.0f, 1.0f)), pos.y+(MathHelper.nextBetween(random, -1.0f, 1.0f)), pos.z+(MathHelper.nextBetween(random, -1.0f, 1.0f)), (-1.0F + random.nextFloat() * 2.0F) / 12.0F, 0.05, (-1.0F + random.nextFloat() * 2.0F) / 12.0F);
			}
		}
	}

	public static boolean canWalkOnQuicksand(Entity entity) {
		if (entity.getType().isIn(ModTags.QUICKSAND_WALKABLE_MOBS)) {
			return true;
		} else {
			return entity instanceof LivingEntity livingEntity && livingEntity.getEquippedStack(EquipmentSlot.FEET).isOf(Items.LEATHER_BOOTS);
		}
	}

	public Item getBucket() {
		return bucket.get();
	}

	@Override
	public ItemStack tryDrainFluid(WorldAccess world, BlockPos pos, BlockState state) {
		world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.REDRAW_ON_MAIN_THREAD | Block.NOTIFY_ALL);
		if (!world.isClient()) {
			world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, pos, Block.getRawIdFromState(state));
		}

		return getBucket().getDefaultStack();
	}

	@Override
	public Optional<SoundEvent> getBucketFillSound() {
		return Optional.of(SoundEvents.BLOCK_SAND_BREAK);
	}

	@Override
	protected void configureFallingBlockEntity(FallingBlockEntity entity) {
		entity.dropItem = false;
	}

	@Override
	public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
		return true;
	}
}
