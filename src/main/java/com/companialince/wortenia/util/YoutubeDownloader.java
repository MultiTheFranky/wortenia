package com.companialince.wortenia.util;

import com.sapher.youtubedl.YoutubeDL;
import com.sapher.youtubedl.YoutubeDLException;
import com.sapher.youtubedl.YoutubeDLRequest;

import java.io.File;

public class YoutubeDownloader {

    public static final String DOWNLOAD_PATH = System.getProperty("java.io.tmpdir"); // Use the system's temporary directory
    public static final String DL_FILE = System.getenv("APPDATA") + "/.wortenia/instances/Wortenia-1.16.5/downloader/yt-dlp.exe";
    public static File downloadMusic(String url) throws YoutubeDLException {
        YoutubeDL.setExecutablePath(DL_FILE);
        YoutubeDLRequest request = new YoutubeDLRequest(url, DOWNLOAD_PATH);
        String randomGUID = java.util.UUID.randomUUID().toString();

        request.setOption("extract-audio");
        request.setOption("format","bestaudio");
        request.setOption("audio-format","mp3");
        request.setOption("audio-quality","0");
        request.setOption("add-metadata");
        request.setOption("output", DOWNLOAD_PATH+"/"+randomGUID+".mp3");
        YoutubeDL.execute(request);
        return new File(DOWNLOAD_PATH+"/"+randomGUID+".mp3");
    }

    public static File downloadVideo(String url) throws YoutubeDLException {
        YoutubeDL.setExecutablePath(DL_FILE);
        String randomGUID = java.util.UUID.randomUUID().toString();

        YoutubeDLRequest request = new YoutubeDLRequest(url, DOWNLOAD_PATH);
        request.setOption("format","bestvideo[ext=mp4]+bestaudio[ext=m4a]/best[ext=mp4]/best");
        request.setOption("format-sort","codec:h264");
        request.setOption("output", DOWNLOAD_PATH+"/"+randomGUID+".mp4");

        YoutubeDL.execute(request);
        return new File(DOWNLOAD_PATH+"/"+randomGUID+".mp4");
    }
}
