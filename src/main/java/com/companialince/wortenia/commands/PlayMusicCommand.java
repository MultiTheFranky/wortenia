package com.companialince.wortenia.commands;

import com.companialince.wortenia.Wortenia;
import com.companialince.wortenia.util.StreamMusicPlay;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.sun.javafx.application.PlatformImpl;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class PlayMusicCommand {

    public static final double DEFAULT_VOLUME = 100;

    public static StreamMusicPlay player;
    public PlayMusicCommand(CommandDispatcher<CommandSource> dispatcher) {
        player = new StreamMusicPlay(DEFAULT_VOLUME);
        registerPlayMusic(dispatcher);
        registerStopMusic(dispatcher);
        registerSetVolume(dispatcher);
    }

    public static void registerPlayMusic(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
                Commands.literal("playmusic")
                        .then(Commands.argument("music", StringArgumentType.greedyString())
                                .executes(context -> {
                                    String music = StringArgumentType.getString(context, "music");
                                    // Check if music is an url
                                    if (music.startsWith("http")) {
                                        PlatformImpl.startup(() -> {});
                                        // Play music from url
                                        Wortenia.LOGGER.info("Playing music from url: " + music);
                                        player.playMusic(music);
                                        context.getSource().sendSuccess(
                                                new StringTextComponent("Playing music from url: " + music),
                                                true
                                        );
                                        return 0;
                                    } else {
                                        Wortenia.LOGGER.info("Invalid url: " + music);
                                        // Send error message to player
                                        context.getSource().sendFailure(
                                                new StringTextComponent("Invalid url")
                                        );
                                        return -1;
                                    }
                                })
                        )
        );
    }

    public static void registerSetVolume (CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
                Commands.literal("volume")
                        .then(Commands.argument("volume", IntegerArgumentType.integer())
                                .executes(context -> {
                                    int volume = IntegerArgumentType.getInteger(context, "volume");
                                    // Check if music is an url
                                    if (volume >= 0 && volume <= 100) {
                                        // Play music from url
                                        Wortenia.LOGGER.info("Setting volume to: " + volume/100.0);
                                        player.setVolume(volume/100.0);
                                        context.getSource().sendSuccess(
                                                new StringTextComponent("Setting volume to: " + volume),
                                                true
                                        );
                                        return 0;
                                    } else {
                                        Wortenia.LOGGER.info("Invalid volume: " + volume);
                                        // Send error message to player
                                        context.getSource().sendFailure(
                                                new StringTextComponent("Invalid volume")
                                        );
                                        return -1;
                                    }
                                })
                        )
        );
    }

    public static void registerStopMusic(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
                Commands.literal("stopmusic")
                        .executes(context -> {
                            // Stop music
                            if(player != null) {
                                player.stopMusic();
                                context.getSource().sendSuccess(
                                        new StringTextComponent("Stopped music"),
                                        true
                                );
                            } else {
                                context.getSource().sendFailure(
                                        new StringTextComponent("No music is playing")
                                );
                            }
                            return 0;
                        })
        );
    }



}
