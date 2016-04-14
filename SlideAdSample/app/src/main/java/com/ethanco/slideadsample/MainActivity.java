package com.ethanco.slideadsample;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ethanco.slidead.AdView;

public class MainActivity extends AppCompatActivity {

    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //需在Application中进行Fresco的初始化
        adView = (AdView) findViewById(R.id.adView_main);
        adView.addData(getUri(R.mipmap.img1), "IMAGE1");
        adView.addData(getUri(R.mipmap.img2), "IMAGE2");
        adView.addData(getUri(R.mipmap.img3), "IMAGE3");
        adView.addData(getUri(R.mipmap.img4), "IMAGE4");
        adView.addData(getUri(R.mipmap.img5), "IMAGE5");
        adView.show(true);

        final Button nextBtn = (Button) findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adView.toNext();
            }
        });

        final Button previousBtn = (Button) findViewById(R.id.previousBtn);
        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adView.toPrevious();
            }
        });
    }

    private Uri getUri(@DrawableRes int resId) {
        return Uri.parse("res://" + getPackageName() + "/" + resId);
    }
}
