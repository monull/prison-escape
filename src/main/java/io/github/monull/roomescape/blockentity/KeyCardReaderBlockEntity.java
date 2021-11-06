package io.github.monull.roomescape.blockentity;

import io.github.monull.roomescape.RegistrationHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class KeyCardReaderBlockEntity extends BlockEntity {

    public List<Boolean> allows = new ArrayList<>(5);

    public KeyCardReaderBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public KeyCardReaderBlockEntity(BlockPos pos, BlockState state) {
        super(RegistrationHandler.KEY_CARD_READER_BLOCK, pos, state);
    }

    public boolean check(int level) {
        if (allows.get(level)) return true; else return false;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        for (int i = 1; i <= 5; i++) {
            boolean bool = allows.get(i);
            nbt.putBoolean(String.valueOf(i), bool);
        }
        return nbt;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        for (int i = 1; i <= 5; i++) {
            if (nbt.contains(String.valueOf(i))) {
                allows.set(i, nbt.getBoolean(String.valueOf(i)));
            }
        }
    }
}
