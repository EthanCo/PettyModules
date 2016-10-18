package com.ethanco.lrecyclerviewtest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.ethanco.lrecyclerviewtest.bean.ItemModel;
import com.ethanco.lrecyclerviewtest.databinding.ActivityDemoBinding;
import com.ethanco.lrecyclerviewtest.utils.AppToast;
import com.ethanco.lrecyclerviewtest.utils.NetworkUtil;
import com.ethanco.lrecyclerviewtest.view.SampleHeader;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LuRecyclerView;
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter;
import com.github.jdsjlzx.util.LuRecyclerViewStateUtils;
import com.github.jdsjlzx.util.LuRecyclerViewUtils;
import com.github.jdsjlzx.view.LoadingFooter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * 带HeaderView的分页加载LinearLayout RecyclerView
 */
public class SwipeRefreshLayoutActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "lzx";

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

    private DataAdapter mDataAdapter = null;

    private PreviewHandler mHandler = new PreviewHandler(this);
    private LuRecyclerViewAdapter mLRecyclerViewAdapter = null;

    private boolean isRefresh = false;

    private ActivityDemoBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_demo);
        setSupportActionBar(binding.toolbar);

        binding.swipeRefreshLayout.setOnRefreshListener(this);

        mDataAdapter = new DataAdapter(this);
        mLRecyclerViewAdapter = new LuRecyclerViewAdapter(mDataAdapter);
        binding.list.setAdapter(mLRecyclerViewAdapter);

        binding.list.setLayoutManager(new LinearLayoutManager(this));

        LuRecyclerViewUtils.setHeaderView(binding.list, new SampleHeader(this));

        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ItemModel item = mDataAdapter.getDataList().get(position);
                AppToast.showShortText(SwipeRefreshLayoutActivity.this, item.title);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                ItemModel item = mDataAdapter.getDataList().get(position);
                AppToast.showShortText(SwipeRefreshLayoutActivity.this, "onItemLongClick - " + item.title);
            }
        });

        binding.list.setLScrollListener(new LuRecyclerView.LScrollListener() {

            @Override
            public void onScrollUp() {
            }

            @Override
            public void onScrollDown() {
            }

            @Override
            public void onBottom() {
                LoadingFooter.State state = LuRecyclerViewStateUtils.getFooterViewState(binding.list);
                if (state == LoadingFooter.State.Loading) {
                    Log.d(TAG, "the state is Loading, just wait..");
                    return;
                }

                if (mCurrentCounter < TOTAL_COUNTER) {
                    // loading more
                    LuRecyclerViewStateUtils.setFooterViewState(SwipeRefreshLayoutActivity.this, binding.list, REQUEST_COUNT, LoadingFooter.State.Loading, null);
                    requestData();
                } else {
                    //the end
                    LuRecyclerViewStateUtils.setFooterViewState(SwipeRefreshLayoutActivity.this, binding.list, REQUEST_COUNT, LoadingFooter.State.TheEnd, null);

                }
            }

            @Override
            public void onScrolled(int distanceX, int distanceY) {
            }

            @Override
            public void onScrollStateChanged(int state) {

            }

        });

        onRefresh();
    }

    @Override
    public void onRefresh() {
        mCurrentCounter = 0;
        isRefresh = true;
        binding.swipeRefreshLayout.setRefreshing(true);
        requestData();
    }

    private void notifyDataSetChanged() {
        mLRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void addItems(ArrayList<ItemModel> list) {
        mDataAdapter.addAll(list);
        mCurrentCounter += list.size();
    }

    private static class PreviewHandler extends Handler {

        private WeakReference<SwipeRefreshLayoutActivity> ref;

        PreviewHandler(SwipeRefreshLayoutActivity activity) {
            ref = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final SwipeRefreshLayoutActivity activity = ref.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            switch (msg.what) {

                case -1:
                    if (activity.isRefresh) {
                        activity.mDataAdapter.clear();
                        mCurrentCounter = 0;
                    }

                    int currentSize = activity.mDataAdapter.getItemCount();

                    //模拟组装10个数据
                    ArrayList<ItemModel> newList = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        if (newList.size() + currentSize >= TOTAL_COUNTER) {
                            break;
                        }

                        ItemModel item = new ItemModel();
                        item.id = currentSize + i;
                        item.title = "item" + (item.id);

                        newList.add(item);
                    }


                    activity.addItems(newList);

                    if (activity.isRefresh) {
                        activity.isRefresh = false;
                        activity.binding.swipeRefreshLayout.setRefreshing(false);
                    }
                    LuRecyclerViewStateUtils.setFooterViewState(activity.binding.list, LoadingFooter.State.Normal);
                    activity.notifyDataSetChanged();

                    break;
                case -2:
                    activity.binding.swipeRefreshLayout.setRefreshing(false);
                    activity.notifyDataSetChanged();
                    break;
                case -3:
                    if (activity.isRefresh) {
                        activity.isRefresh = false;
                        activity.binding.swipeRefreshLayout.setRefreshing(false);
                    }
                    LuRecyclerViewStateUtils.setFooterViewState(activity, activity.binding.list, REQUEST_COUNT, LoadingFooter.State.NetWorkError, activity.mFooterClick);
                    activity.notifyDataSetChanged();

                    break;
                default:
                    break;
            }
        }
    }

    private View.OnClickListener mFooterClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LuRecyclerViewStateUtils.setFooterViewState(SwipeRefreshLayoutActivity.this, binding.list, REQUEST_COUNT, LoadingFooter.State.Loading, null);
            requestData();
        }
    };

    /**
     * 模拟请求网络
     */
    private void requestData() {
        Log.d(TAG, "requestData");

        new Thread() {

            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //模拟一下网络请求失败的情况
                if (NetworkUtil.isNetAvailable(SwipeRefreshLayoutActivity.this)) {
                    mHandler.sendEmptyMessage(-1);
                } else {
                    mHandler.sendEmptyMessage(-3);
                }
            }
        }.start();
    }
}