package io.github.monull.roomescape.screen;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.monull.roomescape.blockentity.KeyCardReaderBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class KeyCardReaderScreen extends Screen {

    BlockEntity entity = null;

    public ImmutableList<Boolean> list;
    public ArrayList<CheckboxWidget> checkboxList = new ArrayList<>(5);

    public KeyCardReaderScreen(BlockEntity entity) {
        super(new LiteralText(""));
        super.init();
        super.init(MinecraftClient.getInstance(), MinecraftClient.getInstance().getWindow().getWidth(), MinecraftClient.getInstance().getWindow().getHeight());
        this.entity = entity;
        if (entity instanceof KeyCardReaderBlockEntity) {
            KeyCardReaderBlockEntity reader = (KeyCardReaderBlockEntity) entity;
            list = ImmutableList.copyOf(reader.allows);
        }
        for (int i = 1; i <= 5; i++) {
            CheckboxWidget widget = new CheckboxWidget(0, 10 * (i - 1), 10, 10, new LiteralText(""), list.get(i));
            checkboxList.set(i, widget);
            addDrawableChild(widget);
        }
    }

    @Override
    public void tick() {

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
        RenderSystem.setShaderTexture(0, new Identifier("prisonescape:textures/gui/panel_light.png"));
        DrawableHelper.drawTexture(matrices, 30, 20, 100, 40, 0, 0, 16, 16, 16, 16);
    }


}
