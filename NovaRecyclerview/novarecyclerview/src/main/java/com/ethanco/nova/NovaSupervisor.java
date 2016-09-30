package com.ethanco.nova;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ethanco.nova.base.MRecyclerView;
import com.ethanco.nova.inter.LoadMoreListener;
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

    //服务器端一共多少条数据
    private int totalCount = Integer.MAX_VALUE;

    //每一页展示多少条数据
    private int listPageSize = 10;

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    private WeakReference<NovaRecyclerView> recyclerViewRef;

    private LoadMoreListener loadmoreListener;

    public void setLoadMoreListener(LoadMoreListener Listener) {
        this.loadmoreListener = Listener;
    }

    public NovaSupervisor(NovaRecyclerView recyclerView) {
        this.recyclerViewRef = new WeakReference<>(recyclerView);
    }

    //打开加载更多功能
    public void openLoadMore() {
        openLoadMore(null);
    }

    public void openLoadMore(LoadMoreListener _Listener) {
        NovaRecyclerView recyclerView = recyclerViewRef.get();
        if (recyclerView == null) {
            return;
        }

        this.loadmoreListener = _Listener;
        recyclerView.addOnScrollBottomListener(new MRecyclerView.OnScrollBottomListener() {
            @Override
            public void onBottom() {
                RecyclerView recyclerView = recyclerViewRef.get();
                if (recyclerView == null) return;

                LoadingFooter.State state = LuRecyclerViewStateUtils.getFooterViewState(recyclerView);
                if (state == LoadingFooter.State.Loading) {
                    Log.d(TAG, "the state is Loading, just wait..");
                    return;
                }

                int currCount = recyclerView.getAdapter().getItemCount();
                if (currCount < totalCount) {
                    //加载更多
                    setFooterViewState(listPageSize, LoadingFooter.State.Loading, null);
                    if (loadmoreListener != null) {
                        loadmoreListener.loadMore(currCount / listPageSize, listPageSize);
                    }
                } else {
                    //没有更多了
                    setFooterViewState(listPageSize, LoadingFooter.State.TheEnd, null);

                }
            }
        });
    }

    public void setFooterViewState(int pageSize, LoadingFooter.State state, View.OnClickListener errorListener) {
        NovaRecyclerView recyclerView = recyclerViewRef.get();
        if (recyclerView == null) return;

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

    public LoadingFooter.State getFooterViewState() {
        NovaRecyclerView recyclerView = recyclerViewRef.get();
        if (recyclerView == null) return LoadingFooter.State.TheEnd;

        return LuRecyclerViewStateUtils.getFooterViewState(recyclerView);
    }

    public void setFooterViewState(LoadingFooter.State state) {
        NovaRecyclerView recyclerView = recyclerViewRef.get();
        if (recyclerView == null) return;

        LuRecyclerViewStateUtils.setFooterViewState(recyclerView, state);
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
}
