package io.github.monull.roomescape.screen;

import io.github.monull.roomescape.blockentity.KeyCardReaderBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

import java.util.ArrayList;

public class KeyCardReaderScreen extends Screen {

    BlockEntity entity = null;

    public ArrayList<Boolean> list = new ArrayList<>(5);
    public ArrayList<CheckboxWidget> checkboxList = new ArrayList<>(5);

    public KeyCardReaderScreen(BlockEntity entity) {
        super(new LiteralText(""));
        super.init();
        super.init(MinecraftClient.getInstance(), MinecraftClient.getInstance().getWindow().getWidth(), MinecraftClient.getInstance().getWindow().getHeight());
        this.entity = entity;
        if (entity instanceof KeyCardReaderBlockEntity) {
            for (int i = 0; i <= 4; i++) {
                boolean bool = ((KeyCardReaderBlockEntity) entity).getAllows(i);
                list.add(bool);
                System.out.println(bool);
            }
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


}
