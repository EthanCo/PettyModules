package com.ethanco.messengerclient;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 */
public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private Messenger mService;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mService = new Messenger(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mService = null;
        }
    };
    private Messenger mGetReplyMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constants.MSG_FROM_SERVICE:
                    Log.i("Z-Client", "receive msg from Service:" + msg.getData().getString("reply"));
                    break;
                default:
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_bind_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bindMyService();
            }
        });

        findViewById(R.id.btn_send_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mService == null) {
                    Toast.makeText(MainActivity.this, "mService is null", Toast.LENGTH_SHORT).show();
                    return;
                }

                Message msg = Message.obtain(null, Constants.MSG_FROM_CLIENT);
                Bundle data = new Bundle();
                data.putString("msg", "hello,this is client");
                //注意下面这句话，服务端会通过msg.replyTo获取到Client Messenger，通过这个来返回数据
                msg.replyTo = mGetReplyMessenger;
                msg.setData(data);
                try {
                    mService.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void bindMyService() {
        intent = new Intent();
        intent.setAction("com.ethanco.messengerserver.mymessenger");
        intent.setPackage("com.ethanco.messengerserver");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        bindService(intent, conn, Service.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (conn != null) {
            unbindService(conn);
        }
    }
}
