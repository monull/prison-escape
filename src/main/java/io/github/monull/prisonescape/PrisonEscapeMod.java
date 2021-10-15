package io.github.monull.prisonescape;

import net.fabricmc.api.ModInitializer;

public class PrisonEscapeMod implements ModInitializer {
    @Override
    public void onInitialize() {
        RegistrationHandler.registerBlocks();
        RegistrationHandler.registerItems();
        RegistrationHandler.registerBlockEntities();
    }
}
