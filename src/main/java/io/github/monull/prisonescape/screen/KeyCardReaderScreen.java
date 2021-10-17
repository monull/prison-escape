package io.github.monull.prisonescape.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.monull.prisonescape.screen.widget.CheckBoxWidget;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;

public class KeyCardReaderScreen extends Screen {

    BlockEntity entity = null;

    public KeyCardReaderScreen(BlockEntity entity) {
        super(new LiteralText(""));
        super.init();
        super.init(MinecraftClient.getInstance(), MinecraftClient.getInstance().getWindow().getWidth(), MinecraftClient.getInstance().getWindow().getHeight());
        this.entity = entity;
        addDrawableChild(new ButtonWidget(45, 45, 45, 45, new LiteralText("hello world"), (widget) -> {

        }));
        addDrawableChild(new CheckBoxWidget(5, 5, 20, 20));
    }

    @Override
    public void init() {
        assert client != null;
        itemRenderer = client.getItemRenderer();
        textRenderer = client.textRenderer;
        setFocused(null);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);

        RenderSystem.setShaderTexture(0, new Identifier("prisonescape:textures/gui/bricks.png"));
        DrawableHelper.drawTexture(matrices, 0, 0, width, height, 0, 0, 696, 564, 696, 564);
    }


}
