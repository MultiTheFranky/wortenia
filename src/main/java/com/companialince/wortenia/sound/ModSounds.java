package com.companialince.wortenia.sound;

import com.companialince.wortenia.Wortenia;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Wortenia.MOD_ID);

    public static final RegistryObject<SoundEvent> WORTENIA = registerSoundEvent("wortenia");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(Wortenia.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }


}
