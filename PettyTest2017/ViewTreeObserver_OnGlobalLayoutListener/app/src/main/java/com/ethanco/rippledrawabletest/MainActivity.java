package com.ethanco.rippledrawabletest;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnRipple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRipple = (Button) findViewById(R.id.btn_ripple);
        btnRipple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout layoutRoot = (LinearLayout) findViewById(R.id.layout_root);
                TextView tv = new TextView(MainActivity.this);
                tv.setText("hello");
                tv.setTextColor(Color.BLUE);
                layoutRoot.addView(tv);
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) btnRipple.getLayoutParams();
                lp.height = 400;
                lp.width=400;
                btnRipple.setLayoutParams(lp);
            }
        });

        final View decorView = getWindow().getDecorView();
        decorView.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    //当View树的状态发生改变或者View的内部的View可见性发生改变时，onGlobalLayout方法会被调用
                    @Override
                    public void onGlobalLayout() {
                        //可在此获取控件的宽高
                        Log.i("Z-Main", "onGlobalLayout");
                        decorView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });

        decorView.post(new Runnable() {
            @Override
            public void run() {
                Log.i("Z-Main", "decorView.post");
            }
        });
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (int i = 0; i < 10; i++) {
            Log.i("Z-Main", "onResume" + i);
            SystemClock.sleep(500);
        }
    }
}
