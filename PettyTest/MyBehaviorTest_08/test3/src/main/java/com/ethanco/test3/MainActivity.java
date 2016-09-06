package com.ethanco.test3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * RecyclerView 长按 换位置
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView list;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = (RecyclerView) findViewById(R.id.list);

        list.setLayoutManager(new LinearLayoutManager(this));
        //list.setLayoutManager(new GridLayoutManager(this, 3));
        MyAdapter adapter = new MyAdapter(this);


        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(list);

        list.setAdapter(adapter);
    }
}
