package io.github.monull.prisonescape.blockentity;

import io.github.monull.prisonescape.RegistrationHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class KeyCardReaderBlockEntity extends BlockEntity {

    int requires = 0;

    public KeyCardReaderBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public KeyCardReaderBlockEntity(BlockPos pos, BlockState state) {
        super(RegistrationHandler.KEY_CARD_READER_BLOCK, pos, state);
    }

    public int getRequires() {
        return requires;
    }

    public void setRequires(int requires) {
        this.requires = requires;
    }

    public boolean check(int level) {
        if (level < requires) return false; else return true;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("requiresLevel", requires);
        return nbt;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("requiresLevel"))
            requires = nbt.getInt("requiresLevel");
    }
}
