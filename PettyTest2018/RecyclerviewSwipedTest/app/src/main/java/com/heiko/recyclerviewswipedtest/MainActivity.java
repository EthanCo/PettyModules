package com.heiko.recyclerviewswipedtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.heiko.swipedhelper.ItemTouchCallBack;
import com.heiko.swipedhelper.SwipedCallBack;

import java.util.ArrayList;
import java.util.List;

//详见 https://www.cnblogs.com/wjtaigwh/p/6543354.html
//https://blog.csdn.net/liaoinstan/article/details/51200618
//进阶 https://www.jianshu.com/p/d30fd8da4eac
public class MainActivity extends AppCompatActivity {

    private RecyclerView list;
    private ItemTouchHelper mItemTouchHelper;
    private SwipedAdapter adapter;
    private List<Bean> datas;
    private String TAG = "Z-Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();

        list = findViewById(R.id.list);
        //list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list.setLayoutManager(new GridLayoutManager(this, 4));

        adapter = new SwipedAdapter(this,datas);
        list.setAdapter(adapter);

        ItemTouchCallBack<Bean> itemTouchCallBack = new ItemTouchCallBack<>(this, datas, adapter);
        itemTouchCallBack.setVibratorEnable(true); //是否震动
        itemTouchCallBack.setSwipedCallBack(new SwipedCallBack() {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Toast.makeText(MainActivity.this, "交换完毕!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mItemTouchHelper = new ItemTouchHelper(itemTouchCallBack);
        mItemTouchHelper.attachToRecyclerView(list);

        adapter.setmItemTouchHelper(mItemTouchHelper);
    }

    private void initDatas() {
        datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add(new Bean("item" + i, false));
        }
    }
}
