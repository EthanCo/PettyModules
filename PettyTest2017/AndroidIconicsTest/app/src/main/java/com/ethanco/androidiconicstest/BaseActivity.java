package com.ethanco.androidiconicstest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;

import com.mikepenz.iconics.context.IconicsLayoutInflater;

/**
 * Created by EthanCo on 2017/2/12.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //3. Define IconicsLayoutInflater to enable automatic xml icons detection (optional)
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);
    }

//    @Override
//    protected void attachBaseContext(Context newBase) {
//        //3.ALTERNATIVE: Inject into Context (optional)
//        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));
//    }
}
