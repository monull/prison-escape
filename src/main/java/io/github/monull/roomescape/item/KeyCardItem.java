package io.github.monull.roomescape.item;

import net.minecraft.item.Item;

public class KeyCardItem  extends Item {
    private final int level;

    public KeyCardItem(Settings settings, int level) {
        super(settings);
        this.level = level;
    }

    public int getLevel() {
        return level;
    }


}
