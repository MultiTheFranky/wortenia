package com.companialince.wortenia.item;

import com.companialince.wortenia.Wortenia;
import com.companialince.wortenia.sound.ModSounds;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Wortenia.MOD_ID);

    public static final RegistryObject<Item> LOGO = ITEMS.register("logo",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOLD = ITEMS.register("gold",
            () -> new Item(new Item.Properties().tab(ModItemGroup.WORTENIA_GROUP)));

    public static final RegistryObject<Item> RAW_GOLD = ITEMS.register("raw_gold",
            () -> new Item(new Item.Properties().tab(ModItemGroup.WORTENIA_GROUP)));

    public static final RegistryObject<Item> WORTENIA_MUSIC_DISC = ITEMS.register("wortenia_music_disk",
            () -> new MusicDiscItem(1, ModSounds.WORTENIA,
                    new Item.Properties().stacksTo(1).tab(ModItemGroup.WORTENIA_GROUP)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
