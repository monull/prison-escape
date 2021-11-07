package io.github.monull.roomescape.blockentity;

import io.github.monull.roomescape.RegistrationHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public class KeyCardReaderBlockEntity extends BlockEntity {

    public ArrayList<Boolean> allows = new ArrayList<>(5);

    public KeyCardReaderBlockEntity(BlockPos pos, BlockState state) {
        super(RegistrationHandler.KEY_CARD_READER_BLOCK, pos, state);
    }

    public boolean check(int level) {
        if (allows.get(level)) return true; else return false;
    }

    public boolean getAllows(int i) {
        return allows.get(i);
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        return nbt;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
    }
}
