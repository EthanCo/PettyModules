package com.ethanco.materialdesigntab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ethanco.mylib.SimpleTabLayout;

/**
 * Created by EthanCo on 2016/3/15.
 */
public class MainActivity extends AppCompatActivity {
    private SimpleTabLayout simpleTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleTabLayout =(SimpleTabLayout) findViewById(R.id.simpleTabLayout);
        simpleTabLayout.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(),this));
    }
}
