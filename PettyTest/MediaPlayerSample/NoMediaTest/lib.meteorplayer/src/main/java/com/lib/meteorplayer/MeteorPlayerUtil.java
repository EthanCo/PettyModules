package com.lib.meteorplayer;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.content.ContentValues.TAG;

/**
 * 工具类
 *
 * @author EthanCo
 * @since 2017/6/21
 */

class MeteorPlayerUtil {

    static void copeAssetsFileToSdcard(Context context, String fileName, File targetFile) throws IOException {
        InputStream in = context.getAssets().open(fileName);
        Log.i(TAG, "file.exists:" + targetFile.exists());
        Log.i(TAG, "file.isDirectory:" + targetFile.isDirectory());

        OutputStream out = new FileOutputStream(targetFile.getPath());
        byte[] buffer = new byte[1024];
        int length = in.read(buffer);
        while (length > 0) {
            out.write(buffer, 0, length);
            length = in.read(buffer);
        }

        out.flush();
        in.close();
        out.close();
        Log.i(TAG, "file.exists2:" + targetFile.exists());
        Log.i(TAG, "file.isDirectory2:" + targetFile.isDirectory());
        Log.i(TAG, "path:" + targetFile.getPath());
    }
}
