package com.ethanco.gridcontainerviewsample;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity {

    private GridContainerView containerView;
    private Button btnAddSpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this); //Fresco初始化
        setContentView(R.layout.activity_main);

        containerView = (GridContainerView) findViewById(R.id.containerViwe);
        btnAddSpan = (Button) findViewById(R.id.btnAddSpan);
        btnAddSpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, null);
                SimpleDraweeView img = (SimpleDraweeView) view.findViewById(R.id.img_item);
                img.setImageURI(Uri.parse("res://com.ethanco.myttttt/" + R.mipmap.img1));
                containerView.attachNewSpan(view);
            }
        });
    }
}
