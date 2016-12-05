package com.ethanco.mimaclient;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

/**
 * Created by EthanCo on 2016/12/5.
 */

public class MinaService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private ConnectionThread thread;

    @Override
    public void onCreate() {
        super.onCreate();
        thread = new ConnectionThread("mina", getApplicationContext());
        thread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        thread.disConnection();
        thread = null;
    }

    /**
     * 负责调用Connection manager类来完成与服务器的连接
     */
    class ConnectionThread extends HandlerThread {

        boolean isConnection;
        ConnectionManager mManager;

        public ConnectionThread(String name, Context context) {
            super(name);

            ConnectionConfig config = new ConnectionConfig.Builder(context)
                    .setIp("192.168.1.1")
                    .setPort(9123)
                    .setConnectionTimeout(5)
                    .setReadBufferSize(2048)
                    .builder();
        }

        //相当于run方法
        //开始连接我们的服务器
        @Override
        protected void onLooperPrepared() {
            super.onLooperPrepared();

            while (true) {
                isConnection = mManager.connect();
                if (isConnection) {
                    break;
                }
                SystemClock.sleep(3000);
            }
        }

        public void disConnection() {
            mManager.disConnection();
        }
    }
}
