package io.github.monull.roomescape.screen;

import io.github.monull.roomescape.block.PasswordModifiable;
import io.github.monull.roomescape.screen.widget.NumberButtonWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;

import java.util.ArrayList;

public class CheckPasswordScreen extends Screen {
    public ArrayList<Integer> password = new ArrayList<>();
    public CheckPasswordScreen(PasswordModifiable modifiable) {
        super(new LiteralText(""));
        super.init(MinecraftClient.getInstance(), MinecraftClient.getInstance().getWindow().getWidth(), MinecraftClient.getInstance().getWindow().getHeight());
        addDrawableChild(new NumberButtonWidget(0, 0, 30, 30, new LiteralText(""), 1, button -> {
            this.password.add(1);
        }));
    }
}
