package com.example.fengh.recyclerviewtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnRecyclerviewBinding;
    private Button btnHRVAHDataBinding;
    private Button btnPullToRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRecyclerviewBinding = (Button) findViewById(R.id.btn_recycler_view_binding);
        btnRecyclerviewBinding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecyclerViewBindingActivity.class);
                startActivity(intent);
            }
        });

        btnHRVAHDataBinding = (Button) findViewById(R.id.btn_brvah_data_binding);
        btnHRVAHDataBinding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BRVAHBindingActivity.class);
                startActivity(intent);
            }
        });

        btnPullToRefresh = (Button) findViewById(R.id.btn_pull_to_refresh);
        btnPullToRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PullToRefreshActivity.class);
                startActivity(intent);
            }
        });
    }
}
