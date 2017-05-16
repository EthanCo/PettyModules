package com.ethanco.contentresolvertest;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Z-Main";
    private Button btnQuery1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnQuery1 = (Button) findViewById(R.id.btn_query1);
        btnQuery1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    @Override
                    public void run() {
                        Cursor cursor = getContentResolver().query(Uri.parse("content://com.ethanco.provider/version"), null, null, null, null);
                        while (cursor.moveToNext()) {
                            int version = cursor.getInt(0);
                            Log.i(TAG, "version:" + version);
                        }

                        cursor = getContentResolver().query(Uri.parse("content://com.ethanco.provider/abc"), null, null, null, null);
                        while (cursor.moveToNext()) {
                            int adc = cursor.getInt(0);
                            Log.i(TAG, "abc:" + adc);
                        }
                    }
                }.start();
            }
        });
    }
}
