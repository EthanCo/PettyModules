package com.ethanco.pointrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    private MyPointAdapter logisticsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview_logistics);
        List<LogisticsModel> list = new ArrayList<>();
        list.add(new LogisticsModel("杭州市", "快件已签收，感谢您使用中通快递!感谢您使用中通快递!期待再次为您服务", "2016-03-10 10:12:34"));
        list.add(new LogisticsModel("嘉兴市", "嘉兴市中转站 已发出", "2016-03-10 6:43:31"));
        list.add(new LogisticsModel("上海市", "上海市中转站 已发出", "2016-03-10 2:13:23"));
        list.add(new LogisticsModel("苏州市", "苏州市中转站 已发出", "2016-03-09 14:43:31"));
        list.add(new LogisticsModel("常州市", "常州市中转站 已发出", "2016-03-09 6:43:31"));
        list.add(new LogisticsModel("南京市", "南京市中转站 已发出", "2016-03-09 2:13:23"));
        logisticsAdapter = new MyPointAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(logisticsAdapter);
    }
}
