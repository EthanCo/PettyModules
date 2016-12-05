package com.ethanco.mimaclient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


//mina-core-2.0.16依赖于slf4j-android-1.6.1-RC1.jar
//http://www.tuicool.com/articles/e6FBRvN
public class MainActivity extends AppCompatActivity {

    private MyReceiver receiver;
    private Intent intentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerMyReceiver();

        findViewById(R.id.btn_start_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentService = new Intent(MainActivity.this, MinaService.class);
                startService(intentService);
            }
        });

        findViewById(R.id.btn_send_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManager.getInstance().writeToServer("123");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intentService);
        unRegisterMyReceiver();
    }

    private void registerMyReceiver() {
        receiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter(ConnectionManager.BROADCAST_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, intentFilter);
    }

    private void unRegisterMyReceiver() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    public static class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String s = intent.getExtras().getString(ConnectionManager.MESSAGE);
            Log.i("Z-", s);
        }
    }
}
