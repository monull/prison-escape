package io.github.monull.prisonescape;

import io.github.monull.prisonescape.block.KeyCardReaderBlock;
import io.github.monull.prisonescape.blockentity.KeyCardReaderBlockEntity;
import io.github.monull.prisonescape.item.BlockModifierItem;
import io.github.monull.prisonescape.item.KeyCardItem;
import io.github.monull.prisonescape.item.KeyCardReaderItem;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class RegistrationHandler {

    public static BlockEntityType<KeyCardReaderBlockEntity> KEY_CARD_READER_BLOCK;

    public static final List<Block> keyCardReaderBlocks = new ArrayList<>();

    public static final ItemGroup PRISON_ESCAPE_GROUP = FabricItemGroupBuilder.create(new Identifier("prsionescape", "general")).icon(() -> keyCardReaderBlocks.get(0).asItem().getDefaultStack()).build();

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier("prisonescape", "key_card_reader"), new KeyCardReaderItem(keyCardReaderBlocks.get(0), new FabricItemSettings().group(PRISON_ESCAPE_GROUP)));
        Registry.register(Registry.ITEM, new Identifier("prisonescape", "key_card_level1"), new KeyCardItem(new FabricItemSettings().group(PRISON_ESCAPE_GROUP), 1));
        Registry.register(Registry.ITEM, new Identifier("prisonescape", "key_card_level2"), new KeyCardItem(new FabricItemSettings().group(PRISON_ESCAPE_GROUP), 2));
        Registry.register(Registry.ITEM, new Identifier("prisonescape", "key_card_level3"), new KeyCardItem(new FabricItemSettings().group(PRISON_ESCAPE_GROUP), 3));
        Registry.register(Registry.ITEM, new Identifier("prisonescape", "key_card_level4"), new KeyCardItem(new FabricItemSettings().group(PRISON_ESCAPE_GROUP), 4));
        Registry.register(Registry.ITEM, new Identifier("prisonescape", "key_card_level5"), new KeyCardItem(new FabricItemSettings().group(PRISON_ESCAPE_GROUP), 5));
        Registry.register(Registry.ITEM, new Identifier("prisonescape", "block_modifier"), new BlockModifierItem(new FabricItemSettings().group(PRISON_ESCAPE_GROUP)));
    }

    public static void registerBlocks() {
        Block block = Registry.register(Registry.BLOCK, new Identifier("prisonescape", "key_card_reader"), new KeyCardReaderBlock(FabricBlockSettings.of(Material.METAL).breakByHand(false)));
        keyCardReaderBlocks.add(block);
    }
    public static void registerBlockEntities() {
        KEY_CARD_READER_BLOCK = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("prisonescape", "key_card_reader"), FabricBlockEntityTypeBuilder.create(KeyCardReaderBlockEntity::new, keyCardReaderBlocks.toArray(new Block[0])).build(null));
    }
}
