package com.ethanco.mytest;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;

import com.ethanco.mytest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Z-";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdaptiveActivity.class);
                startActivity(intent);
            }
        });

        // 获取屏幕密度（方法3）
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        float density = dm.density;      // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        int densityDPI = dm.densityDpi;     // 屏幕密度（每寸像素：120/160/240/320）
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;

        int screenWidthDip = dm.widthPixels;        // 屏幕宽
        int screenHeightDip = dm.heightPixels;      // 屏幕高

        int screenWidth = (int) (dm.widthPixels * density + 0.5f);      // 屏幕宽（px，如：480px，？？？）
        int screenHeight = (int) (dm.heightPixels * density + 0.5f);     // 屏幕高（px，如：800px，？？？）

        /*binding.tvScreenInfo.append("屏幕密度(像素比例):" + density + "\n" + "屏幕密度(每寸像素):" + densityDPI + "\n");
        binding.tvScreenInfo.append("屏幕宽度dpi:" + xdpi + "dpi\n" + "屏幕高度dpi:" + ydpi + "dpi\n");
        binding.tvScreenInfo.append("屏幕宽度:" + screenWidth + "px\n" + "屏幕高度:" + screenHeight + "px\n");*/

        binding.tvScreenInfo.append("屏幕宽:" + screenWidthDip + "px\n" + "屏幕高:" + screenHeightDip + "px\n");
        binding.tvScreenInfo.append("屏幕密度(像素比例):" + density + "，表示px:dp=1:" + density + "\n" + "屏幕密度(每寸像素):" + densityDPI + " dpi\n");

        //binding.tvScreenInfo.append("屏幕宽度:" + screenWidth + "px\n" + "屏幕高度:" + screenHeight + "px\n");

        /*// 获取屏幕密度（方法2）
        DisplayMetrics dm = new DisplayMetrics();
        dm = getResources().getDisplayMetrics();

        float density = dm.density;        // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        int densityDPI = dm.densityDpi;     // 屏幕密度（每寸像素：120/160/240/320）
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;

        int screenWidth = dm.widthPixels;      // 屏幕宽（像素，如：480px）
        int screenHeight = dm.heightPixels;     // 屏幕高（像素，如：800px）*/
    }
}
