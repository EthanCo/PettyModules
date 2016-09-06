package com.ethanco.tinttest;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Tint
 * http://mp.weixin.qq.com/s?__biz=MzA3MDMyMjkzNg==&mid=2652261822&idx=1&sn=dcd42d7b4b1a360906b080db1724045a&scene=23&srcid=0725whBzxgUMOv2QnF1TEln7#rd
 *
 */
public class MainActivity extends AppCompatActivity {

    private ImageView img1;
    private ImageView img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);

        final Drawable originBitmapDrawable = getResources().getDrawable(R.mipmap.update).mutate();
        img1.setImageDrawable(tintDrawable(originBitmapDrawable, ColorStateList.valueOf(Color.GREEN)));
    }

    public static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }
}
