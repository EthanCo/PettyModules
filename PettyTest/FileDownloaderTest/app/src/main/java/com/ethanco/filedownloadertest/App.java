package com.ethanco.filedownloadertest;

import android.app.Application;

import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.connection.FileDownloadUrlConnection;
import com.liulishuo.filedownloader.services.DownloadMgrInitialParams;

import java.net.Proxy;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2016/12/27
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //FileDownloader.init(this);

        //FileDownloader.init(getApplicationContext());

        FileDownloader.init(getApplicationContext(), new DownloadMgrInitialParams.InitCustomMaker()
                .connectionCreator(new FileDownloadUrlConnection
                        .Creator(new FileDownloadUrlConnection.Configuration()
                        .connectTimeout(15_000) // set connection timeout.
                        .readTimeout(15_000) // set read timeout.
                        .proxy(Proxy.NO_PROXY) // set proxy
                )));
    }
}
