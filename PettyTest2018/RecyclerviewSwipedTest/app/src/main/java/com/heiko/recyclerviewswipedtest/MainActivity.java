package com.heiko.recyclerviewswipedtest;

import android.app.Service;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//详见 https://www.cnblogs.com/wjtaigwh/p/6543354.html
//https://blog.csdn.net/liaoinstan/article/details/51200618
//进阶 https://www.jianshu.com/p/d30fd8da4eac
public class MainActivity extends AppCompatActivity {

    private RecyclerView list;
    private ItemTouchHelper mItemTouchHelper;
    private SwipedAdapter adapter;
    private List<Bean> datas;
    private boolean isA;
    private String TAG = "Z-Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list);
        //list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list.setLayoutManager(new GridLayoutManager(this,4));

        datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add(new Bean("item" + i,false));
        }

        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            /*
            * 这个方法是设置是否滑动时间，以及拖拽的方向，所以在这里需要判断一下是列表布局还是网格布局，
            * 如果是列表布局的话则拖拽方向为DOWN和UP，如果是网格布局的话则是DOWN和UP和LEFT和RIGHT
            *
            * */
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                if (isA) {
                    isA = false;
                    for (Bean data : datas) {
                        data.setChecked(false);
                    }
                    adapter.notifyDataSetChanged();
                }

                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    final int swipeFlags = 0;
                    return makeMovementFlags(dragFlags, swipeFlags);
                } else {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    final int swipeFlags = 0;
                    return makeMovementFlags(dragFlags, swipeFlags);
                }
            }

            //onMove（）方法则是我们在拖动的时候不断回调的方法
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //得到当拖拽的viewHolder的Position
                int fromPosition = viewHolder.getAdapterPosition();
                //拿到当前拖拽到的item的viewHolder
                int toPosition = target.getAdapterPosition();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(datas, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(datas, i, i - 1);
                    }
                }
                adapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            //onSwiped（）是替换后调用的方法，可以不用管
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }

            /**
             * 长按选中Item的时候开始调用
             *
             * @param viewHolder
             * @param actionState
             */
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                    //获取系统震动服务
                    Vibrator vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                    //震动70毫秒
                    vib.vibrate(70);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            @Override
            public boolean isLongPressDragEnabled() {
                boolean enable = super.isLongPressDragEnabled();
//                isA = true;
//                for (Bean data : datas) {
//                    data.setChecked(true);
//                }
//                adapter.notifyDataSetChanged();
                return enable;
            }

            /**
             * 手指松开的时候还原
             * @param recyclerView
             * @param viewHolder
             */
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(0);
            }

            /**
             * 重写拖拽不可用
             * @return
             */
            /*@Override
            public boolean isLongPressDragEnabled() {
                return false;
            }*/
        });
        mItemTouchHelper.attachToRecyclerView(list);

        /*list.addOnItemTouchListener(new OnRecyclerItemClickListener(list) {
            //item 长点击事件
            @Override
            public void onLongClick(RecyclerView.ViewHolder vh) {
                //如果item不是最后一个，则执行拖拽
                boolean result = vh.getLayoutPosition()!=datas.size()-1;
                Log.i(TAG, "result:"+result+" vh.getLayoutPosition():"+vh.getLayoutPosition()+" ==>"+(vh.getLayoutPosition()!=datas.size()-1));
                *//*if (vh.getLayoutPosition()!=datas.size()-1) {
                    mItemTouchHelper.startDrag(vh);
                }*//*
                isA = true;
                for (Bean data : datas) {
                    data.setChecked(true);
                }
                adapter.notifyDataSetChanged();
            }
            //item 点击事件
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
            }
        });*/

        adapter = new SwipedAdapter(datas, this,mItemTouchHelper);
        list.setAdapter(adapter);
    }
}
