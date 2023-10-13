package com.companialince.wortenia.item;

import com.companialince.wortenia.Wortenia;
import com.companialince.wortenia.sound.ModSounds;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Wortenia.MOD_ID);

    public static final RegistryObject<Item> LOGO = ITEMS.register("logo",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOLD = ITEMS.register("gold",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_GOLD = ITEMS.register("raw_gold",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WORTENIA_MUSIC_DISC = ITEMS.register("wortenia_music_disk",
            () -> new RecordItem(6, ModSounds.WORTENIA, new Item.Properties().stacksTo(1),3840));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
