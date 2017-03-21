package com.ethanco.skintest;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import skin.support.SkinCompatManager;
import skin.support.utils.SkinFileUtils;

public class MainActivity extends BaseActivity {

    public static final String TAG = "Z-Skin";
    private Button btn1;
    private Button btn2;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btn1 = (Button) findViewById(R.id.btn_1);
        btn2 = (Button) findViewById(R.id.btn_2);

        setSupportActionBar(toolbar);
        setTitle(R.string.app_name);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dir = SkinFileUtils.getSkinDir(MainActivity.this);
                //String dir = SkinFileUtils.getCacheDir(MainActivity.this);
                Log.i(TAG, "dir:" + dir);

                // 指定皮肤插件
                SkinCompatManager.getInstance().loadSkin("skinblack.skin", new SkinCompatManager.SkinLoaderListener() {
                    @Override
                    public void onStart() {
                        Log.i(TAG, "onStart");
                    }

                    @Override
                    public void onSuccess() {
                        Log.i(TAG, "onSuccess");
                    }

                    @Override
                    public void onFailed(String errMsg) {
                        Log.i(TAG, "onFailed:" + errMsg);
                    }
                });
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SkinCompatManager.getInstance().restoreDefaultTheme();
            }
        });
    }
}
