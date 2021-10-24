package io.github.monull.prisonescape.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.monull.prisonescape.screen.widget.CheckBoxWidget;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class KeyCardReaderScreen extends Screen {

    BlockEntity entity = null;

    public ArrayList<Boolean> list = new ArrayList<>();

    public KeyCardReaderScreen(BlockEntity entity) {
        super(new LiteralText(""));
        super.init();
        super.init(MinecraftClient.getInstance(), MinecraftClient.getInstance().getWindow().getWidth(), MinecraftClient.getInstance().getWindow().getHeight());
        for (int i = 0; i < 5; i++) {
            list.add(i, false);
        }
        this.entity = entity;
        addDrawableChild(new CheckBoxWidget(26, 100, 30, 30, this, 1));
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
        DrawableHelper.drawTexture(matrices, 36, 0, 348, 282, 0, 0, 696, 564, 696, 564);

        RenderSystem.setShaderTexture(0, new Identifier("prisonescape:textures/gui/number.png"));
        DrawableHelper.drawTexture(matrices, 26, 87, 172, 83, 0, 0, 1416, 666, 1416, 666);
        RenderSystem.setShaderTexture(0, new Identifier("prisonescape:textures/gui/number.png"));
        DrawableHelper.drawTexture(matrices, 71, 87, 172, 83, 0, 0, 1416, 666, 1416, 666);
        RenderSystem.setShaderTexture(0, new Identifier("prisonescape:textures/gui/number.png"));
        DrawableHelper.drawTexture(matrices, 116, 87, 172, 83, 0, 0, 1416, 666, 1416, 666);
        RenderSystem.setShaderTexture(0, new Identifier("prisonescape:textures/gui/number.png"));
        DrawableHelper.drawTexture(matrices, 161, 87, 172, 83, 0, 0, 1416, 666, 1416, 666);
        RenderSystem.setShaderTexture(0, new Identifier("prisonescape:textures/gui/number.png"));
        DrawableHelper.drawTexture(matrices, 206, 87, 172, 83, 0, 0, 1416, 666, 1416, 666);
    }


}
