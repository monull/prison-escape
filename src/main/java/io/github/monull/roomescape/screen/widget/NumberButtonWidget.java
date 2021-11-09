package io.github.monull.roomescape.screen.widget;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.monull.roomescape.screen.CheckPasswordScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.screen.narration.NarrationPart;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class NumberButtonWidget extends ButtonWidget {

    public final int number;
    private static final Identifier TEXTURE = new Identifier("roomescape:textures/gui/number.png");

    public NumberButtonWidget(int x, int y, int width, int height, Text text, int number, PressAction action) {
        super(x, y, width, height, text, action);

        this.number = number;
    }

    @Override
    public void appendNarrations(NarrationMessageBuilder builder) {
        this.appendDefaultNarrations(builder);
    }
}
