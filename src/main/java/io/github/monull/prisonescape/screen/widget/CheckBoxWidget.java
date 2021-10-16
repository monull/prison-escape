package io.github.monull.prisonescape.screen.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

import java.util.Collections;

public class CheckBoxWidget extends ButtonWidget {

    public CheckBoxWidget(int x, int y, int width, int height) {
        super(x, y, width, height, new LiteralText(""), (widget) -> {
            MinecraftClient.getInstance().player.sendMessage(new LiteralText("hello world"), true);
        });
    }

    @Override
    public void renderTooltip(MatrixStack matrices, int mouseX, int mouseY) {
        Screen currentScreen = MinecraftClient.getInstance().currentScreen;

        if (currentScreen != null) {
            currentScreen.renderTooltip(matrices, Collections.singletonList(new TranslatableText("prisonescape.button")), mouseX, mouseY + 15);
        }
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        RenderSystem.setShaderTexture(0, new Identifier("prisonescape:textures/gui/button.png"));
        DrawableHelper.drawTexture(matrices, x, y, 25, 25, 0, 0, 8, 8, 16, 16);
    }
}
