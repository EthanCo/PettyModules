package com.ethanco.lrecyclerviewtest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.ethanco.lrecyclerviewtest.base.AdapterWrap;
import com.ethanco.lrecyclerviewtest.bean.ItemModel;
import com.ethanco.lrecyclerviewtest.databinding.ActivityTest2Binding;

public class Test2Activity extends AppCompatActivity {
    private static final String TAG = "Z-";
    private ActivityTest2Binding binding;
    private AdapterWrap<ItemModel> mAdapterWrap;

    /**
     * 服务器端一共多少条数据
     */
    private static final int TOTAL_COUNTER = 64;

    /**
     * 每一页展示多少条数据
     */
    private static final int REQUEST_COUNT = 10;

    /**
     * 已经获取到多少条数据了
     */
    private static int mCurrentCounter = 0;

    private boolean isRefresh = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test2);
        setSupportActionBar(binding.toolbar);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //binding.list.setPullRefreshEnabled(false);
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(Test2Activity.this, "refresh", Toast.LENGTH_SHORT).show();
            }
        });

        binding.list.setLayoutManager(new LinearLayoutManager(this));
        mAdapterWrap = new AdapterWrap(new DataAdapter(this));
        binding.list.setAdapter(mAdapterWrap);
    }

//        binding.list.setLayoutManager(new LinearLayoutManager(this));
//
//        mAdapterWrap = new AdapterWrap(new DataAdapter(this));
//        binding.list.setAdapter(mAdapterWrap);
//
//
//        //RecyclerViewUtils.setHeaderView(binding.list, new SampleHeader(this));
//        //binding.list.setPullRefreshEnabled(false);
//
//        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                RecyclerViewStateUtils.setFooterViewState(binding.list, LoadingFooter.State.Normal);
//                mAdapterWrap.adapter.clear();
//                mAdapterWrap.notifyDataSetChanged();//fix bug:crapped or attached views may not be recycled. isScrap:false isAttached:true
//                mCurrentCounter = 0;
//                isRefresh = true;
//                requestData();
//            }
//        });
//
//        /*//TODO reconsitution
//        binding.list.setLScrollListener(new LRecyclerView.LScrollListener() {
//            @Override
//            public void onRefresh() {
//                *//*RecyclerViewStateUtils.setFooterViewState(binding.list, LoadingFooter.State.Normal);
//                mAdapterWrap.adapter.clear();
//                mAdapterWrap.notifyDataSetChanged();//fix bug:crapped or attached views may not be recycled. isScrap:false isAttached:true
//                mCurrentCounter = 0;
//                isRefresh = true;
//                requestData();*//*
//            }
//
//            @Override
//            public void onScrollUp() {
//            }
//
//            @Override
//            public void onScrollDown() {
//            }
//
//            @Override
//            public void onBottom() {
//                LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(binding.list);
//                if (state == LoadingFooter.State.Loading) {
//                    Log.d(TAG, "the state is Loading, just wait..");
//                    return;
//                }
//
//                if (mCurrentCounter < TOTAL_COUNTER) {
//                    // loading more
//                    RecyclerViewStateUtils.setFooterViewState(TestActivity.this, binding.list, REQUEST_COUNT, LoadingFooter.State.Loading, null);
//                    requestData();
//                } else {
//                    //the end
//                    RecyclerViewStateUtils.setFooterViewState(TestActivity.this, binding.list, REQUEST_COUNT, LoadingFooter.State.TheEnd, null);
//
//                }
//            }
//
//            @Override
//            public void onScrolled(int distanceX, int distanceY) {
//            }
//
//            @Override
//            public void onScrollStateChanged(int state) {
//
//            }
//        });*/
//
//
//        //binding.list.setRefreshing(true);
//
//        /*mAdapterWrap.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                ItemModel item = mAdapterWrap.adapter.getDataList().get(position);
//                Toast.makeText(TestActivity.this, item.title, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onItemLongClick(View view, int position) {
//                ItemModel item = mAdapterWrap.adapter.getDataList().get(position);
//                Toast.makeText(TestActivity.this, "onItemLongClick - " + item.title, Toast.LENGTH_LONG).show();
//            }
//        });*/
//
//        binding.swipeRefreshLayout.setRefreshing(true);
//    }
//
//    private void addItems(ArrayList<ItemModel> list) {
//        mAdapterWrap.adapter.addAll(list);
//        mCurrentCounter += list.size();
//    }
//
//    private void notifyDataSetChanged() {
//        mAdapterWrap.notifyDataSetChanged();
//    }
//
//    /**
//     * 模拟请求网络
//     */
//    private void requestData() {
//        Log.d(TAG, "requestData");
//        new Thread() {
//
//            @Override
//            public void run() {
//                super.run();
//
//                try {
//                    Thread.sleep(800);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                //模拟一下网络请求失败的情况 (需读取网络状态权限)
//                if (NetworkUtil.isNetAvailable(Test2Activity.this)) {
//                    mHandler.sendEmptyMessage(-1);
//                } else {
//                    mHandler.sendEmptyMessage(-3);
//                }
//            }
//        }.start();
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    private View.OnClickListener mFooterClick = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            RecyclerViewStateUtils.setFooterViewState(Test2Activity.this, binding.list, REQUEST_COUNT, LoadingFooter.State.Loading, null);
//            requestData();
//        }
//    };
//
//    private static class PreviewHandler extends Handler {
//
//        private WeakReference<Test2Activity> ref;
//
//        PreviewHandler(Test2Activity activity) {
//            ref = new WeakReference<>(activity);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            final Test2Activity activity = ref.get();
//            if (activity == null || activity.isFinishing()) {
//                return;
//            }
//            switch (msg.what) {
//
//                case -1:
//                    if (activity.isRefresh) {
//                        activity.mAdapterWrap.adapter.clear();
//                        mCurrentCounter = 0;
//                    }
//
//                    int currentSize = activity.mAdapterWrap.adapter.getItemCount();
//
//                    //模拟组装10个数据
//                    ArrayList<ItemModel> newList = new ArrayList<>();
//                    for (int i = 0; i < 10; i++) {
//                        if (newList.size() + currentSize >= TOTAL_COUNTER) {
//                            break;
//                        }
//
//                        ItemModel item = new ItemModel();
//                        item.id = currentSize + i;
//                        item.title = "item" + (item.id);
//
//                        newList.add(item);
//                    }
//
//
//                    activity.addItems(newList);
//
//                    if (activity.isRefresh) {
//                        activity.isRefresh = false;
//                        activity.binding.swipeRefreshLayout.setRefreshing(false);
//                        //activity.binding.list.refreshComplete();
//                    }
//
//                    RecyclerViewStateUtils.setFooterViewState(activity.binding.list, LoadingFooter.State.Normal);
//                    activity.notifyDataSetChanged();
//                    break;
//                case -2:
//                    activity.notifyDataSetChanged();
//                    break;
//                case -3:
//                    if (activity.isRefresh) {
//                        activity.isRefresh = false;
//                        activity.binding.swipeRefreshLayout.setRefreshing(false);
//                        //activity.binding.list.refreshComplete();
//                    }
//                    activity.notifyDataSetChanged();
//                    RecyclerViewStateUtils.setFooterViewState(activity, activity.binding.list, REQUEST_COUNT, LoadingFooter.State.NetWorkError, activity.mFooterClick);
//                    break;
//                default:
//                    break;
//            }
//        }
//    }
}
