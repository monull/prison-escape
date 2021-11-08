package io.github.monull.roomescape.blockentity;

import io.github.monull.roomescape.RegistrationHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class KeypadBlockEntity extends BlockEntity {

    public KeypadBlockEntity(BlockPos pos, BlockState state) {
        super(RegistrationHandler.KEY_PAD_BLOCK, pos, state);
    }
}
