package com.companialince.wortenia.block;

import com.companialince.wortenia.Wortenia;
import com.companialince.wortenia.block.custom.GoldOre;
import com.companialince.wortenia.item.ModItemGroup;
import com.companialince.wortenia.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Wortenia.MOD_ID);

    public static final RegistryObject<Block> GOLD_BLOCK = registerBlock("gold_block",
            () -> new Block(Block.Properties.of(Material.METAL).strength(9f).requiresCorrectToolForDrops().harvestTool(net.minecraftforge.common.ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> ORE_GOLD_BLOCK = registerBlock("ore_gold_block",
            () -> new GoldOre(Block.Properties.of(Material.METAL).strength(9f).requiresCorrectToolForDrops().harvestTool(net.minecraftforge.common.ToolType.PICKAXE).harvestLevel(2)));

    private static <T extends Block> RegistryObject<T> registerBlock (String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ModItemGroup.WORTENIA_GROUP)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
