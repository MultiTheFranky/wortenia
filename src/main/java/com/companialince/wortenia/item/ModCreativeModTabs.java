package com.companialince.wortenia.item;

import com.companialince.wortenia.Wortenia;
import com.companialince.wortenia.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Wortenia.MOD_ID);

    public static final RegistryObject<CreativeModeTab> WORTENIA_TAB = CREATIVE_MODE_TABS.register("tutorial_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.LOGO.get()))
                    .title(Component.translatable("itemGroup.wortenia"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(new ItemStack(ModItems.GOLD.get()));
                        pOutput.accept(new ItemStack(ModItems.RAW_GOLD.get()));
                        pOutput.accept(new ItemStack(ModItems.WORTENIA_MUSIC_DISC.get()));
                        pOutput.accept(new ItemStack(ModBlocks.GOLD_BLOCK.get()));
                        pOutput.accept(new ItemStack(ModBlocks.GRAVEL_BLOCK.get()));
                        pOutput.accept(new ItemStack(ModBlocks.ORE_GOLD_BLOCK.get()));
                    })
                    .build());
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
