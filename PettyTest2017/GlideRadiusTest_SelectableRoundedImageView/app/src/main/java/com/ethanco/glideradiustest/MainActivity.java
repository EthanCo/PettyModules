package com.ethanco.glideradiustest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.joooonho.SelectableRoundedImageView;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;

public class MainActivity extends AppCompatActivity {

    private SelectableRoundedImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = (SelectableRoundedImageView) findViewById(R.id.img_1);

//        Glide.with(this)
//                .load(R.drawable.bg1)
//                //transform要按照顺序加载
//                //.transform(new CenterCrop(this), new GlideTransformUtil(this, 25))
//                .transform(new CenterCrop(this), new GlideTransformUtil2(this, 25))
//                // .centerCrop()
//                .into(img1);

//        Glide.with(this)
//                .load("http://img.tuku.cn/file_big/201502/d130653bfb884152b8a5ba9e846362d1.jpg")
//                //.asBitmap()
//                .into(img1);

        Glide.with(this)
                .load("http://img.tuku.cn/file_big/201502/d130653bfb884152b8a5ba9e846362d1.jpg")
                .bitmapTransform(
                        new BlurTransformation(this, 10, 1),
                        new CropTransformation(this, img1.getWidth(), img1.getHeight()))
                .into(img1);
    }
}
