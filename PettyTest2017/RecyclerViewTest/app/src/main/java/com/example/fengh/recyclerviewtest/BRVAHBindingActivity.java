package com.example.fengh.recyclerviewtest;

import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BRVAHBindingActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private DataBindingUseAdapter mAdapter;
    private Button btnAdd;
    private Button btnDel;
    private ArrayList<Movie> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brvahbinding);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnDel = (Button) findViewById(R.id.btn_del);

        mAdapter = new DataBindingUseAdapter(R.layout.item_movie, genData());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "Chad";
                int price = 11111;
                int len = 22222;
                Movie movie = new Movie(name, len, price, "Go To Home.");
                list.add(movie);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(list.size()-1);
            }
        });

    }

    private List<Movie> genData() {
        list = new ObservableArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            String name = "Chad";
            int price = random.nextInt(10) + 10;
            int len = random.nextInt(80) + 60;
            Movie movie = new Movie(name, len, price, "He was one of Australia's most distinguished artistes");
            list.add(movie);
        }
        return list;
    }
}
