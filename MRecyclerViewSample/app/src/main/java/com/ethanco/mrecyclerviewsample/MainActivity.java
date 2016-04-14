package com.ethanco.mrecyclerviewsample;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.ethanco.mrecyclerview.PullOnRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PullOnRecyclerView list;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (PullOnRecyclerView) findViewById(R.id.list);

        List data = new ArrayList();
        for (int i = 0; i < 20; i++) {
            data.add("Item" + i);
        }
        mLayoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(data);
        list.setAdapter(mAdapter);

        list.setPullUpListener(new PullOnRecyclerView.OnPullUpListener() {
            @Override
            public void onPullUp(int pageIndex, int pageSize) {
                new Thread() {
                    @Override
                    public void run() {
                        final List<String> newData = new ArrayList<>();
                        for (int i = 0; i < 10; i++) {
                            newData.add("New" + i);
                        }
                        SystemClock.sleep(2000);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //mAdapter.setFootVisible(false);
                                mAdapter.AddData(newData);
                                list.setLoadFinish(true);
                            }
                        });
                    }
                }.start();
            }
        });
    }
}
