package io.github.monull.roomescape;

import net.fabricmc.api.ModInitializer;

public class RoomEscapeMod implements ModInitializer {
    @Override
    public void onInitialize() {
        RegistrationHandler.registerBlocks();
        RegistrationHandler.registerItems();
        RegistrationHandler.registerBlockEntities();
    }
}
