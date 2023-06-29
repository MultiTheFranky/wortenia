package com.companialince.wortenia.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ModItemGroup {

    public static final ItemGroup WORTENIA_GROUP = new ItemGroup("wortenia") {
        @Override
        @Nonnull
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.LOGO.get());
        }
    };

}
