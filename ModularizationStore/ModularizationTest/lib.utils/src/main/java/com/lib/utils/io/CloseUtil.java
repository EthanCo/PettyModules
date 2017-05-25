package com.lib.utils.io;

import java.io.Closeable;
import java.io.IOException;

/**
 * 用于关闭流(Stream) 等继承自 Closeable的对象
 * Created by Zhk on 2016/3/12.
 */
public class CloseUtil {
    private CloseUtil() {
    }

    /**
     * 关闭Closeable对象
     *
     * @param closeable
     */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 安全关闭Closeable对象
     *
     * @param closeable
     */
    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                // Ignored
            }
        }
    }
}
