package io.github.monull.roomescape.block;

import io.github.monull.roomescape.RegistrationHandler;
import io.github.monull.roomescape.blockentity.KeyCardReaderBlockEntity;
import io.github.monull.roomescape.item.BlockModifierItem;
import io.github.monull.roomescape.item.KeyCardItem;
import io.github.monull.roomescape.screen.KeyCardReaderScreen;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Random;

public class KeyCardReaderBlock extends Block implements BlockEntityProvider {

    public static final DirectionProperty FACING = Properties.FACING;
    public static final BooleanProperty POWERED = Properties.POWERED;

    public KeyCardReaderBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        KeyCardReaderBlockEntity be = (KeyCardReaderBlockEntity) world.getBlockEntity(pos);
        for (int i = 0; i <= 4; i++) {
            be.allows.add(i, false);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Item item = player.getInventory().getMainHandStack().getItem();
        if (item instanceof KeyCardItem keyCard) {
            int level = keyCard.getLevel();
            if (world.getBlockEntity(pos) != null) {
                KeyCardReaderBlockEntity be = (KeyCardReaderBlockEntity) world.getBlockEntity(pos);
                assert be != null;
                boolean isEnable = be.check(level);
                if (isEnable) activate(world, pos, 30);
            }
        } else if (item instanceof BlockModifierItem) {
            MinecraftClient.getInstance().setScreen(new KeyCardReaderScreen(world.getBlockEntity(pos)));
        }
        return ActionResult.SUCCESS;
    }

    public static void activate(World world, BlockPos pos, int signalLength) {
        world.setBlockState(pos, world.getBlockState(pos).with(POWERED, true));
        world.updateNeighborsAlways(pos, RegistrationHandler.keyCardReaderBlocks.get(0));
        world.getBlockTickScheduler().schedule(pos, RegistrationHandler.keyCardReaderBlocks.get(0), signalLength);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient) {
            world.setBlockState(pos, world.getBlockState(pos).with(POWERED, false));
        }
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (state.get(POWERED)) {
            return 15;
        } else {
            return 0;
        }
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getPlacementState(ctx.getWorld(), ctx.getBlockPos(), ctx.getSide(), ctx.getHitPos().x, ctx.getHitPos().y, ctx.getHitPos().z, ctx.getPlayer());
    }

    public BlockState getPlacementState(World world, BlockPos pos, Direction facing, double hitX, double hitY, double hitZ, PlayerEntity placer) {
        return getDefaultState().with(FACING, placer.getHorizontalFacing().getOpposite()).with(POWERED, false);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
        builder.add(POWERED);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rot)
    {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror)
    {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new KeyCardReaderBlockEntity(pos, state);
    }
}
