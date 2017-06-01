package com.ethanco.getidentifiertest;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

/**
 * 如何使用Android自带的资源
 * http://www.jianshu.com/p/f49a0f6f6224#
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Z-MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Resources.getSystem().getIdentifier("资源名称", "资源类型", "资源所在包");

        int lebId = Resources.getSystem().getIdentifier("permlab_accessNetworkState", "string", "android");
        String lab = getString(lebId);
        Log.e(TAG, "lab : " + lab);

        int descId = Resources.getSystem().getIdentifier("permdesc_createNetworkSockets", "string", "android");
        String desc = getString(descId);
        Log.e(TAG, "desc : " + desc);

        int okId = Resources.getSystem().getIdentifier("sym_keyboard_feedback_ok", "drawable", "android");
        ((ImageView) findViewById(R.id.imageView)).setImageResource(okId);
    }
}
