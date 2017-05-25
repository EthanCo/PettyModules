package com.lib.utils.compat;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Description 路径的兼容
 * Created by Zhk on 2015/12/24.
 */
public class DirectionCompat {
    public static final String FOLDER_DOWNLOAD = "download";
    public static final String FOLDER_HOPE = "Hope";
    public static final String FOLDER_Config = "Config";

    /**
     * 获取自定义路径 外置存储/folder
     *
     * @param context
     * @param folder  外置存储一级文件夹名
     * @return
     */
    public static String getCustomDir(Context context, String folder) {
        String dir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            dir = Environment.getExternalStorageDirectory() + File.separator + folder;
        } else {
            dir = getFileDir(context, folder);
        }
        return dir;
    }

    /**
     * 获取自定义路径 外置存储/folder/folderChild
     *
     * @param context
     * @param folder      外置存储一级文件夹名
     * @param folderChild folder文件夹内的文件夹名
     * @return
     */
    public static String getCustomDir(Context context, String folder, String folderChild) {
        String dir = getCustomDir(context, folder);
        dir += File.separator + folderChild;
        return dir;
    }

    /**
     * 获取 Android/data/包名/Cache
     *
     * @param context
     * @return
     */
    public static String getCacheDir(Context context) {
        String path;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (null == context.getExternalCacheDir()) {
                path = Environment.getExternalStorageDirectory().getPath();
            } else {
                path = context.getExternalCacheDir().getPath();
            }
        } else {
            path = context.getCacheDir().getPath();
        }
        return path;
    }

    /**
     * 获取 Android/data/包名/File
     *
     * @param context
     * @return
     */
    public static String getFileDir(Context context) {
        return getFileDir(context, null);
    }

    /**
     * 获取 Android/data/包名/File/folder
     *
     * @param context
     * @param folder
     * @return
     */
    public static String getFileDir(Context context, String folder) {
        String dir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            dir = context.getExternalFilesDir(folder).getPath();
        } else {
            dir = context.getFilesDir().getPath();
        }
        return dir;
    }

    /**
     * 获取 Android/data/包名/download
     *
     * @param context
     * @return
     */
    public static String getDownloadDir(Context context) {
        return getFileDir(context, FOLDER_DOWNLOAD);
    }
}