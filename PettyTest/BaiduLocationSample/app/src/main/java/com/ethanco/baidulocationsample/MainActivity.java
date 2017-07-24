package com.ethanco.baidulocationsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ethanco.lib.baidu.BaiduFacede;

public class MainActivity extends AppCompatActivity {
    private Button btnLocation;
    private Button btnStopLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLocation = (Button) findViewById(R.id.btn_location);
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaiduFacede.startLocate(MainActivity.this);
            }
        });

        btnStopLocation = (Button) findViewById(R.id.btn_stop_location);
        btnStopLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaiduFacede.stopLocate(MainActivity.this);
            }
        });
    }
}
