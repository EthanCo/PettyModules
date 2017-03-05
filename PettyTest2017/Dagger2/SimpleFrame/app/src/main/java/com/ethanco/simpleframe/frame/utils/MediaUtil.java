package com.ethanco.simpleframe.frame.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;

/**
 * Description  音乐媒体播放工具类
 * Created by chenqiao on 2015/10/16.
 */
public class MediaUtil {
    private static MediaPlayer mediaPlayer;

    /**
     * 播放Assets中的媒体文件
     *
     * @param context  上下文
     * @param fileName 媒体文件名
     */
    public static void playFromAssets(Context context, String fileName) {
        try {
            AssetFileDescriptor fileDescript = context.getAssets().openFd(fileName);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(fileDescript.getFileDescriptor(), fileDescript.getStartOffset(), fileDescript.getLength());
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 播放资源中的媒体文件
     *
     * @param context 上下文
     * @param resId   资源ID
     */
    public static void playFromRes(Context context, int resId) {
        try {
            MediaPlayer.create(context, resId).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 播放Uri指向的媒体文件
     *
     * @param context 上下文
     * @param uri     资源Uri
     */
    public static void playFromUri(Context context, Uri uri) {
        try {
            MediaPlayer.create(context, uri).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
