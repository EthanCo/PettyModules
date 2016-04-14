package com.ethanco.mrecyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Zhk on 2016/4/9.
 */
public class PullOnRecyclerView extends RecyclerView {

    private int lastVisibleItem;
    private int pageIndex = 0;
    private int pageSize = 10;
    private boolean isLoading = false;

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public PullOnRecyclerView(Context context) {
        this(context, null);
    }

    public PullOnRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullOnRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);


        addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == getAdapter().getItemCount()) {
                    if (!isLoading) {
                        isLoading = true;
                        if (mPullUpListener != null)
                            mPullUpListener.onPullUp(pageIndex, pageSize);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
            }
        });
    }

    public interface OnPullUpListener {
        void onPullUp(int pageIndex, int pageSize);
    }

    private OnPullUpListener mPullUpListener;

    public void setPullUpListener(OnPullUpListener pullUpListener) {
        mPullUpListener = pullUpListener;
    }

    public void setLoadFinish(boolean status) {
        isLoading = false;
    }

    /**
     * Adapter必继承MAdapter
     *
     * @param adapter
     */
    @Override
    public void setAdapter(Adapter adapter) {
        if (adapter instanceof PullOnAdapter) {
            super.setAdapter(adapter);
        } else {
            throw new IllegalArgumentException("Adapter必须继承MAdapter");
        }
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        if (layout instanceof LinearLayoutManager) {
            super.setLayoutManager(layout);
        } else {
            throw new IllegalArgumentException("layout必须为LinearLayoutManager");
        }
    }
}
