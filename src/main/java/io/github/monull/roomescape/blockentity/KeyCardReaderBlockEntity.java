package io.github.monull.roomescape.blockentity;

import io.github.monull.roomescape.RegistrationHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.stream.Stream;

public class KeyCardReaderBlockEntity extends BlockEntity {

    public ArrayList<Boolean> allows = new ArrayList<>(5);

    public KeyCardReaderBlockEntity(BlockPos pos, BlockState state) {
        super(RegistrationHandler.KEY_CARD_READER_BLOCK, pos, state);
        for (int i = 0; i <= 4; i++) {
            allows.add(i, false);
        }
    }

    public boolean check(int level) {
        if (allows.get(level - 1)) return true; else return false;
    }

    public boolean getAllows(int i) {
        return allows.get(i);
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putBoolean("one", allows.get(0));
        nbt.putBoolean("two", allows.get(1));
        nbt.putBoolean("three", allows.get(2));
        nbt.putBoolean("four", allows.get(3));
        nbt.putBoolean("five", allows.get(4));
        return nbt;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (Stream.of("one", "two", "three", "four", "five").allMatch(nbt::contains)) {
            allows.set(0, nbt.getBoolean("one"));
            allows.set(1, nbt.getBoolean("two"));
            allows.set(2, nbt.getBoolean("three"));
            allows.set(3, nbt.getBoolean("four"));
            allows.set(4, nbt.getBoolean("five"));
        }
    }
}
