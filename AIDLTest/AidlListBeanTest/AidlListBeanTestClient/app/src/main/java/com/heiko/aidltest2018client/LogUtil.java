package com.heiko.aidltest2018client;

import android.util.Log;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2018/9/8
 */
public class LogUtil {
    /**
     * 截断输出日志
     * @param msg
     */
    public static void i(String tag, String msg) {
        if (tag == null || tag.length() == 0
                || msg == null || msg.length() == 0)
            return;

        int segmentSize = (3 * 1024);
        long length = msg.length();
        if (length <= segmentSize ) {// 长度小于等于限制直接打印
            Log.i(tag, msg);
        }else {
            while (msg.length() > segmentSize ) {// 循环分段打印日志
                String logContent = msg.substring(0, segmentSize );
                msg = msg.substring(segmentSize);//msg.replace(logContent, "");
                Log.i(tag, logContent);
            }
            Log.i(tag, msg);// 打印剩余日志
        }
    }
}
