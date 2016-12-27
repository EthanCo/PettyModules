package com.ethanco.filedownloadertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "Z-Download";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String path = DirectionCompat.getDownloadDir(this);
        Log.i(TAG, path);
        //String url = "http://img3.cache.netease.com/photo/0001/2016-12-27/C99JNLA000AP0001.jpg";
        String url = "http://cdn.sinacloud.net/leisurealarmclock/test/plugin.hdl-debug.apk";
        FileDownloader.getImpl().create(url)
                .setPath(path, true) //true: 表示path为路径，不是文件
                .setListener(new FileDownloadListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        Log.i(TAG, "pending");
                    }

                    @Override
                    protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
                        Log.i(TAG, "connected");
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        Log.i(TAG, "progress soFarBytes:" + soFarBytes + " totalBytes:" + totalBytes);
                    }

                    @Override
                    protected void blockComplete(BaseDownloadTask task) {
                        Log.i(TAG, "blockComplete");
                    }

                    @Override
                    protected void retry(final BaseDownloadTask task, final Throwable ex, final int retryingTimes, final int soFarBytes) {
                        Log.i(TAG, "retry");
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        Log.i(TAG, "completed");
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        Log.i(TAG, "paused");
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        Log.e(TAG, "error:" + e.getMessage());
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                        Log.i(TAG, "warn");
                    }
                }).start();
    }
}
