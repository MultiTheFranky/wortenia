package com.companialince.wortenia.util;

import com.companialince.wortenia.Wortenia;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

public class StreamVideoPlay {
    public static final String VIDEO_PATH = System.getProperty("java.io.tmpdir"); // Use the system's temporary directory

    double volume;
    MediaPlayer mediaPlayer;


    public StreamVideoPlay(double volume) {
        if(new File(VIDEO_PATH).mkdirs())
            Wortenia.LOGGER.info("Created video folder: " + VIDEO_PATH);
        this.volume = volume;
    }

    public void playVideo(String url) {
        new Thread(() -> {
            try {
                if(mediaPlayer != null && mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)){
                    mediaPlayer.stop();
                    Thread.sleep(1000);
                }
                File video = YoutubeDownloader.downloadVideo(url);
                Platform.startup(() -> {});
                playVisualVideo(video.getAbsolutePath());
            } catch (Exception e) {
                Wortenia.LOGGER.error("Error while playing video from url: " + url);
                e.printStackTrace();
            }
        }).start();
    }

    private void playVisualVideo(String path) {
        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            try {
                final File f = new File(path);

                final Media m = new Media(f.toURI().toURL().toExternalForm());
                mediaPlayer = new MediaPlayer(m);
                mediaPlayer.setAutoPlay(true);
                final MediaView mediaView  = new MediaView(mediaPlayer);

                mediaView.setPreserveRatio(true);

                final StackPane root = new StackPane();
                root.getChildren().add(mediaView);

                final Scene scene = new Scene(root, 960, 540);
                scene.setFill(Color.BLACK);
                scene.setCursor(Cursor.NONE);

                final Stage primaryStage = new Stage();

                primaryStage.setScene(scene);

                primaryStage.setTitle("Wortenia Fullscreen Video");
                primaryStage.getIcons().add(new Image("assets/wortenia/textures/logo.png"));
                primaryStage.setFullScreen(true);
                primaryStage.show();
                primaryStage.setOnCloseRequest(event -> {
                    Wortenia.LOGGER.info("Closing video");
                    stopVideo();
                });
                mediaPlayer.setOnEndOfMedia(() -> {
                    Wortenia.LOGGER.info("Video ended");
                    stopVideo();
                });

                final DoubleProperty width = mediaView.fitWidthProperty();
                final DoubleProperty height = mediaView.fitHeightProperty();
                width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
                height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));

                Wortenia.LOGGER.info("Playing video");
                mediaPlayer.play();
            } catch (Exception ex) {
                System.out.println("Error occured during playback process:" + ex.getMessage());
            }
        });
    }

    public void setVolume(double volume) {
        try {
            this.volume = volume;
            Wortenia.LOGGER.info("Setting volume to: " + volume);
            if (mediaPlayer != null)
                mediaPlayer.setVolume(volume);
        } catch (Exception ex) {
            System.out.println("Error occured during set volume process:" + ex.getMessage());
        }
    }

    public void stopVideo() {
        try {
            Wortenia.LOGGER.info("Stopping video");
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.dispose();
                mediaPlayer = null;
            }
            Wortenia.LOGGER.info("Stopped video");
        } catch (Exception ex) {
            System.out.println("Error occured during stop playback process:" + ex.getMessage());
        }
    }
}
