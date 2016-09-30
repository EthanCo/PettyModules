package com.ethanco.nova;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ethanco.nova.base.MRecyclerView;
import com.ethanco.nova.inter.IListLoad;
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter;
import com.github.jdsjlzx.util.LuRecyclerViewStateUtils;
import com.github.jdsjlzx.util.LuRecyclerViewUtils;
import com.github.jdsjlzx.view.LoadingFooter;

import java.lang.ref.WeakReference;

import static android.content.ContentValues.TAG;

/**
 * @Description Recyclerview 监管者
 * Created by EthanCo on 2016/9/28.
 */

public class NovaSupervisor {

    /**
     * 服务器端一共多少条数据
     */
    private static int totalCount = 64;

    /**
     * 每一页展示多少条数据
     */
    private static int listPageSize = 10;

//    /**
//     * 已经获取到多少条数据了
//     */
//    private static int mCurrentCounter = 0;


    //private WeakReference<AdapterWrap> adapterWrapRef;
    private WeakReference<NovaRecyclerView> recyclerViewRef;

    private IListLoad listLoad;

    public void setListLoad(IListLoad listLoad) {
        this.listLoad = listLoad;
    }

    private boolean isRefresh = false;

    public NovaSupervisor(NovaRecyclerView recyclerView) {
        this.recyclerViewRef = new WeakReference<>(recyclerView);

        /*Field adapterField = null;
        try {
            Field sss = ((RecyclerView) recyclerView).getClass().getDeclaredField("sss");
            adapterField = ((RecyclerView)recyclerView).getClass().getDeclaredField("mAdapter");
            adapterField.setAccessible(true);
            Adapter adapter = (Adapter) adapterField.get(recyclerView);
            if (adapter instanceof AdapterWrap) {
                adapterWrapRef = new WeakReference<>((AdapterWrap) adapter);
            } else {
                throw new IllegalStateException("adapter not is AdapterWrap");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    //打开加载更多功能
    public void openLoadMore() {
        openLoadMore(null);
    }

    public void openLoadMore(IListLoad _listLoad) {
        NovaRecyclerView recyclerView = recyclerViewRef.get();
        if (recyclerView == null) {
            return;
        }

        this.listLoad = _listLoad;
        recyclerView.addOnScrollBottomListener(new MRecyclerView.OnScrollBottomListener() {
            @Override
            public void onBottom() {
                RecyclerView recyclerView = recyclerViewRef.get();
                if (recyclerView == null) {
                    return;
                }

                LoadingFooter.State state = LuRecyclerViewStateUtils.getFooterViewState(recyclerView);
                if (state == LoadingFooter.State.Loading) {
                    Log.d(TAG, "the state is Loading, just wait..");
                    return;
                }

                int currCount = recyclerView.getAdapter().getItemCount();
                if (currCount < NovaSupervisor.totalCount) {
                    // loading more
                    setFooterViewState(recyclerView, listPageSize, LoadingFooter.State.Loading, null);
                    if (listLoad != null) {
                        listLoad.loadMore(currCount / listPageSize, listPageSize);
                    }
                } else {
                    //the end
                    setFooterViewState(recyclerView, listPageSize, LoadingFooter.State.TheEnd, null);

                }
            }
        });
    }

    public static void setFooterViewState(RecyclerView recyclerView, int pageSize, LoadingFooter.State state, View.OnClickListener errorListener) {
        //if(instance != null && !instance.isFinishing()) {
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && outerAdapter instanceof LuRecyclerViewAdapter) {
            LuRecyclerViewAdapter LuRecyclerViewAdapter = (LuRecyclerViewAdapter) outerAdapter;
            if (LuRecyclerViewAdapter.getInnerAdapter().getItemCount() >= pageSize) {
                if (LuRecyclerViewAdapter.getFooterViewsCount() > 0) {
                    LoadingFooter footerView = (LoadingFooter) LuRecyclerViewAdapter.getFooterView();
                    footerView.setState(state);
                    footerView.setVisibility(View.VISIBLE);
                    if (state == LoadingFooter.State.NetWorkError) {
                        footerView.setOnClickListener(errorListener);
                    }
                }
            }
        }
    }

    public void setHeaderView(View view) {
        NovaRecyclerView recyclerView = recyclerViewRef.get();
        if (recyclerView == null) return;

        LuRecyclerViewUtils.setHeaderView(recyclerView, view);
    }

    public void setFooterView(View view) {
        NovaRecyclerView recyclerView = recyclerViewRef.get();
        if (recyclerView == null) return;

        LuRecyclerViewUtils.setFooterView(recyclerView, view);
    }

    public void removeFooterView() {
        NovaRecyclerView recyclerView = recyclerViewRef.get();
        if (recyclerView == null) return;

        LuRecyclerViewUtils.removeFooterView(recyclerView);
    }

    public void removeHeaderView() {
        NovaRecyclerView recyclerView = recyclerViewRef.get();
        if (recyclerView == null) return;

        LuRecyclerViewUtils.removeHeaderView(recyclerView);
    }

    /*private static class PreviewHandler extends Handler {
        public static final int FLAG_LOAD_SUCCESS = 200;
        private WeakReference<NovaSupervisor> ref;
        //TODO
        private IListLoad listLoad;

        PreviewHandler(NovaSupervisor activity) {
            ref = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final NovaSupervisor activity = ref.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            switch (msg.what) {

                case -1:
                    if (NovaSupervisor.isRefresh) {
                        //TODO DATA CLEAR
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

                    //TODO Data Changed

                    activity.binding.swipeRefreshLayout.setRefreshing(false);
                    activity.notifyDataSetChanged();
                    break;
                case -3:

                    //TODO NetWork Error

                    if (activity.isRefresh) {
                        activity.isRefresh = false;
                        activity.binding.swipeRefreshLayout.setRefreshing(false);
                    }
                    LuRecyclerViewStateUtils.setFooterViewState(activity, activity.binding.list, REQUEST_COUNT, LoadingFooter.State.NetWorkError, activity.mFooterClick);
                    activity.notifyDataSetChanged();

                    break;

                case FLAG_LOAD_SUCCESS:
                    if (listLoad.isRefresh()) {

                        //TODO
                        //activity.setRefreshing(false);
                    }


                    LuRecyclerViewStateUtils.setFooterViewState(listLoad.getRecyclerView(), LoadingFooter.State.Normal);
                    notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    }*/
}
