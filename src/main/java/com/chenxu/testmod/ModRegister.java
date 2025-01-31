package com.chenxu.testmod;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRegister {
    // Create a Deferred Register
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TestMod.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, TestMod.MODID);

    // Add new blocks
    public static final RegistryObject<Block> MY_BLOCK = BLOCKS.register("my_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));

    // Add new Items
    public static final RegistryObject<Item> MY_BLOCK_ITEM = ITEMS.register("my_block",
            () -> new BlockItem(MY_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> MY_FOOD = ITEMS.register("my_food", () -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder().alwaysEat().nutrition(1).saturationMod(2f).build())));

    // Add items to my CreativeModeTab
    public static final RegistryObject<CreativeModeTab> TEST_TAB = CREATIVE_MODE_TABS.register("test_mod",
            () -> CreativeModeTab.builder().withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> MY_FOOD.get().getDefaultInstance()).displayItems((parameters, output) -> {
                        output.accept(MY_FOOD.get());
                        output.accept(MY_BLOCK_ITEM.get());
                    }).build());

    // Register all things
    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
    }
}
