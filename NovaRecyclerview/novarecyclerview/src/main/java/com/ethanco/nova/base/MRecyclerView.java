package com.ethanco.nova.base;

import android.content.Context;
import android.util.AttributeSet;

import com.github.jdsjlzx.recyclerview.LuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description RecyclerView
 * Created by EthanCo on 2016/9/28.
 */

public abstract class MRecyclerView extends LuRecyclerView {

    public MRecyclerView(Context context) {
        super(context);
        init(context);
        initEvent(context);
    }

    public MRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initEvent(context);
    }

    public MRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
        initEvent(context);
    }

    private void init(Context context) {

    }

    private void initEvent(Context context) {
        super.setLScrollListener(new LScrollListener() {
            @Override
            public void onScrollUp() {
                for (LScrollListener scrollListener : scrollListeners) {
                    scrollListener.onScrollUp();
                }
            }

            @Override
            public void onScrollDown() {
                for (LScrollListener scrollListener : scrollListeners) {
                    scrollListener.onScrollDown();
                }
            }

            @Override
            public void onBottom() {
                for (OnScrollBottomListener onScrollBottomListener : onScrollBottomListeners) {
                    onScrollBottomListener.onBottom();
                }

                for (LScrollListener scrollListener : scrollListeners) {
                    scrollListener.onBottom();
                }
            }

            @Override
            public void onScrolled(int i, int i1) {
                for (OnScrolledListener onScrolledListener : onScrolledListeners) {
                    onScrolledListener.onScrolled(i, i1);
                }

                for (LScrollListener scrollListener : scrollListeners) {
                    scrollListener.onScrolled(i, i1);
                }
            }

            @Override
            public void onScrollStateChanged(int i) {
                for (OnScrollStateChangedListener onScrollStateChanged : onScrollStateChangeds) {
                    onScrollStateChanged.onScrollStateChanged(i);
                }

                for (LScrollListener scrollListener : scrollListeners) {
                    scrollListener.onScrollStateChanged(i);
                }
            }
        });
    }

    public interface onScrollListener extends LScrollListener {
    }

    public interface OnScrolledListener {
        void onScrolled(int var1, int var2);
    }

    public interface OnScrollStateChangedListener {
        void onScrollStateChanged(int var1);
    }

    public interface OnScrollBottomListener {
        void onBottom();
    }

    protected List<onScrollListener> scrollListeners = new ArrayList<>();
    protected List<OnScrolledListener> onScrolledListeners = new ArrayList<>();
    protected List<OnScrollStateChangedListener> onScrollStateChangeds = new ArrayList<>();
    protected List<OnScrollBottomListener> onScrollBottomListeners = new ArrayList<>();

    public void addScrollListener(onScrollListener listener) {
        if (!scrollListeners.contains(listener))
            scrollListeners.add(listener);
    }

    public void addOnScrolledListener(OnScrolledListener listener) {
        if (!onScrolledListeners.contains(listener))
            onScrolledListeners.add(listener);
    }

    public void addOnScrollStateChangedListener(OnScrollStateChangedListener listener) {
        if (!onScrollStateChangeds.contains(listener))
            onScrollStateChangeds.add(listener);
    }

    public void addOnScrollBottomListener(OnScrollBottomListener listener) {
        if (!onScrollBottomListeners.contains(listener)) {
            onScrollBottomListeners.add(listener);
        }

    }

    @Override
    @Deprecated
    public void setLScrollListener(LScrollListener listener) {
        throw new IllegalStateException("please use addScrollListener,addOnScrolledListener,addOnScrollStateChangedListener");
    }
}
