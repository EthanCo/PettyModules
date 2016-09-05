package com.ethanco.diffutiltest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView list;
    private List<Item> data;
    private MyAdapter adapter;
    private List<Item> newdata;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(final Message msg) {
            super.handleMessage(msg);
            if (msg.obj instanceof DiffUtil.DiffResult) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setData(newdata);
                        ((DiffUtil.DiffResult) msg.obj).dispatchUpdatesTo(adapter);
                    }
                });
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = ((RecyclerView) findViewById(R.id.list));
        initData();

        list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, data);
        list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item1:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        initData2();
                        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback(data, newdata));
                        Message message = handler.obtainMessage();
                        message.obj = diffResult;
                        handler.dispatchMessage(message);
                    }
                }).start();
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        if (data == null) {
            data = new ArrayList<>();
        }
        for (int i = 0; i < 20; i++) {
            data.add(new Item(i, "title" + i, "content" + i, "footer" + i));
        }
    }

    private void initData2() {
        if (newdata == null) {
            newdata = new ArrayList<>();
        } else {
            newdata.clear();
        }
        for (int i = 0; i < 20; i++) {
            if (i > 2 && i < 7) {
                newdata.add(new Item(i, "==title" + i, "==content" + i, "==footer" + i));
            } else {
                newdata.add(new Item(i, "title" + i, "content" + i, "footer" + i));
            }
        }
    }
}
