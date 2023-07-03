package com.companialince.wortenia.commands;

import com.companialince.wortenia.Wortenia;
import com.companialince.wortenia.util.StreamVideoPlay;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.sun.javafx.application.PlatformImpl;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class PlayVideoCommand {

    public static final double DEFAULT_VOLUME = 100;

    public static StreamVideoPlay player;
    public PlayVideoCommand(CommandDispatcher<CommandSource> dispatcher) {
        player = new StreamVideoPlay(DEFAULT_VOLUME);
        registerPlayVideo(dispatcher);
        registerStopVideo(dispatcher);
        registerSetVideoVolume(dispatcher);
    }

    public static void registerPlayVideo(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
                Commands.literal("playvideo")
                        .then(Commands.argument("video", StringArgumentType.greedyString())
                                .executes(context -> {
                                    String video = StringArgumentType.getString(context, "video");
                                    // Check if music is an url
                                    if (video.startsWith("http")) {
                                        PlatformImpl.startup(() -> {});
                                        // Play music from url
                                        Wortenia.LOGGER.info("Playing video from url: " + video);
                                        player.playVideo(video);
                                        context.getSource().sendSuccess(
                                                new StringTextComponent("Playing video from url: " + video),
                                                true
                                        );
                                        return 0;
                                    } else {
                                        Wortenia.LOGGER.info("Invalid url: " + video);
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

    public static void registerSetVideoVolume (CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
                Commands.literal("videovolume")
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

    public static void registerStopVideo(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
                Commands.literal("stopvideo")
                        .executes(context -> {
                            // Stop music
                            if(player != null) {
                                player.stopVideo();
                                context.getSource().sendSuccess(
                                        new StringTextComponent("Stopped music"),
                                        true
                                );
                            } else {
                                context.getSource().sendFailure(
                                        new StringTextComponent("No video is playing")
                                );
                            }
                            return 0;
                        })
        );
    }



}
