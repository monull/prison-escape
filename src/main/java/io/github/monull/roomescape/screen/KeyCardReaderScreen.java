package io.github.monull.roomescape.screen;

import com.google.common.collect.ImmutableList;
import io.github.monull.roomescape.blockentity.KeyCardReaderBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

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
            list = ImmutableList.copyOf(((KeyCardReaderBlockEntity) entity).allows);
        }
        for (int i = 0; i <= 4; i++) {
            CheckboxWidget box = new CheckboxWidget(150, 100 + 20 * (i - 1), 20, 20, new LiteralText(String.valueOf(i + 1)), list.get(i));
            checkboxList.add(box);
            addDrawableChild(box);
        }
        addDrawableChild(new ButtonWidget(250, 90, 50, 20, new LiteralText("save and exit"), button -> {
            this.onClose();
        }));
    }

    @Override
    public void onClose() {
        super.onClose();
        for (int i = 0; i <= 4; i++) {
            if (entity instanceof KeyCardReaderBlockEntity) {
                ((KeyCardReaderBlockEntity) entity).allows.set(i, checkboxList.get(i).isChecked());
            }
        }
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
        renderBackground(matrices);
        drawCenteredText(matrices, textRenderer, new LiteralText("Key Card Reader Block Settings"), 220, 50, 0xFFFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
