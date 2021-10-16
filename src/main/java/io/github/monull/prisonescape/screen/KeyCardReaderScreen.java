package io.github.monull.prisonescape.screen;

import io.github.monull.prisonescape.screen.widget.CheckBoxWidget;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.LiteralText;

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
}
