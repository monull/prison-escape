package io.github.monull.roomescape.block;

import io.github.monull.roomescape.blockentity.KeypadBlockEntity;
import io.github.monull.roomescape.item.BlockModifierItem;
import io.github.monull.roomescape.screen.CheckPasswordScreen;
import io.github.monull.roomescape.screen.SetPasswordScreen;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
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

import java.util.ArrayList;
import java.util.Objects;

public class KeypadBlock extends Block implements BlockEntityProvider, PasswordModifiable {

    public static final DirectionProperty FACING = Properties.FACING;
    public static final BooleanProperty POWERED = Properties.POWERED;

    public ArrayList<Integer> password = new ArrayList<>();



    public KeypadBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Item item = player.getInventory().getMainHandStack().getItem();
        if (item instanceof BlockModifierItem) {
            MinecraftClient.getInstance().setScreen(new SetPasswordScreen(this));
        } else {
            MinecraftClient.getInstance().setScreen(new CheckPasswordScreen(this));
        }
        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new KeypadBlockEntity(pos, state);
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (state.get(POWERED)) return 15; else return 0;
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

    @Override
    public void setPassword(ArrayList<Integer> password) {
        this.password.clear();
        for (int i = 0; i < password.size(); i++) {
            this.password.add(i, password.get(i));
        }
    }

    @Override
    public boolean checkPassword(ArrayList<Integer> password) {
        if (password.equals(this.password)) return true; else return false;
    }
}
