package com.companialince.wortenia.util;

import com.companialince.wortenia.Wortenia;
import javafx.application.Platform;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class StreamMusicPlay {
    public static final String MUSIC_PATH = System.getProperty("java.io.tmpdir"); // Use the system's temporary directory

    double volume;
    MediaPlayer player;

    public StreamMusicPlay(double volume) {
        if(new File(MUSIC_PATH).mkdirs())
            Wortenia.LOGGER.info("Created music folder: " + MUSIC_PATH);
        this.volume = volume;
    }

    public void playMusic(String url) {
        new Thread(() -> {
            try {
                if(player != null && player.getStatus().equals(MediaPlayer.Status.PLAYING)){
                    player.stop();
                    Thread.sleep(1000);
                }
                Platform.startup(() -> {});
                playMusicAudio(YoutubeDownloader.downloadMusic(url).getPath());
            } catch (Exception e) {
                Wortenia.LOGGER.error("Error while playing music from url: " + url);
                e.printStackTrace();
            }
        }).start();
    }

    private void playMusicAudio(String path) {
        try {
            player = new MediaPlayer(new javafx.scene.media.Media(new File(path).toURI().toString()));
            Wortenia.LOGGER.info("Playing music from path: " + path + " with volume: " + volume);
            player.setVolume(volume);
            player.play();
        } catch (Exception ex) {
            System.out.println("Error occured during playback process:" + ex.getMessage());
        }
    }

    public void setVolume(double volume) {
        try {
            this.volume = volume;
            Wortenia.LOGGER.info("Setting volume to: " + volume);
            if (player != null)
                player.setVolume(volume);
        } catch (Exception ex) {
            System.out.println("Error occured during set volume process:" + ex.getMessage());
        }
    }

    public void stopMusic() {
        try {
            player.stop();
            player.dispose();
            player = null;
        } catch (Exception ex) {
            System.out.println("Error occured during stop playback process:" + ex.getMessage());
        }
    }
}
