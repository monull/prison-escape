package io.github.monull.roomescape.screen;

import io.github.monull.roomescape.block.PasswordModifiable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;

@Environment(EnvType.CLIENT)
public class SetPasswordScreen extends Screen {
    public SetPasswordScreen(PasswordModifiable modifiable) {
        super(new LiteralText(""));
        super.init(MinecraftClient.getInstance(), MinecraftClient.getInstance().getWindow().getWidth(), MinecraftClient.getInstance().getWindow().getHeight());

    }

}
