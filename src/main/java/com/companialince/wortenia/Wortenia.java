package com.companialince.wortenia;

import com.companialince.wortenia.block.ModBlocks;
import com.companialince.wortenia.item.ModItems;
import com.companialince.wortenia.sound.ModSounds;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(Wortenia.MOD_ID)
public class Wortenia
{
    public static final String MOD_ID = "wortenia";
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public Wortenia() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModSounds.register(eventBus);

        eventBus.addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("| WORTENIA | PREINIT |");
    }
}
