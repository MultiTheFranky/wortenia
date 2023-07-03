package com.companialince.wortenia.events;

import com.companialince.wortenia.Wortenia;
import com.companialince.wortenia.commands.PlayMusicCommand;
import com.companialince.wortenia.commands.PlayVideoCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = Wortenia.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onCommandsRegister (RegisterCommandsEvent event) {
        new PlayMusicCommand(event.getDispatcher());
        new PlayVideoCommand(event.getDispatcher());
    }

}
