package com.ethanco.nomediatest;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * .Nomedia文件夹相关
 * 这个文件夹内的音频文件不会被系统数据库发现
 *
 * @author EthanCo
 * @since 2017/4/20
 */

public class NomediaUtil {
    private static File nomedia;

    public static File getNomedia(Context context) {
        if (nomedia == null) {
            nomedia = new File(getCustomDir(context, "HopeLauncher", ".nomedia"));
        }
        return nomedia;
    }

    /**
     * @param filePath 路径
     * @return 是否创建成功
     */
    public static boolean makeDirs(String filePath) {
        File folder = new File(filePath);
        return (folder.exists() && folder.isDirectory())
                ? true
                : folder.mkdirs();
    }

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
}
