package com.lib.frame.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Toast;

import com.ethanco.nova.NovaSupervisor;
import com.lib.frame.view.abs.IListView;
import com.lib.frame.viewmodel.BaseViewModel;

import java.util.Collection;

/**
 * Recyclerview列表 Fragment
 * Created by EthanCo on 2016/6/13.
 *
 * @param <B> 数据类型
 * @param <I> IListView子类
 * @param <T> ViewModel
 */
@SuppressWarnings("unchecked")
public abstract class BaseLoadMoreListFragment<B, I extends IListView<B>, T extends BaseViewModel<I>> extends BaseFragment<I, T> implements IListView<B> {
    protected NovaSupervisor supervisor;

    @Override
    public void midfield() {
        super.midfield();
        supervisor = createSupervisor();
        supervisor.setErrorClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                supervisor.loadMore();
            }
        });
    }

    protected abstract NovaSupervisor createSupervisor();

    protected abstract SwipeRefreshLayout getSwipeRefreshLayout();


    @Override
    public void onRefreshSuccess(Collection<B> collection) {
        if (collection == null) {
            onRefreshFailed("加载失败");
        } else {
            final boolean isLoadEnd = collection.size() < supervisor.getPageSize();
            supervisor.setLoadEnd(isLoadEnd);
            supervisor.onRefreshSuccess(collection);
            setRefreshing(false);
        }
    }

    @Override
    public void onRefreshFailed(String error) {
        supervisor.onRefreshFailed(error);
        setRefreshing(false);

        Toast.makeText(getActivity(), "刷新错误:" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMoreSuccess(Collection<B> collection) {
        if (collection == null) {
            onLoadMoreFailed("加载失败");
        } else {
            supervisor.onLoadMoreSuccess(collection);
            if (collection.size() < supervisor.getPageSize()) {
                supervisor.setLoadEnd(true);
            }
        }
    }

    @Override
    public void onLoadMoreFailed(String error) {
        supervisor.onLoadMoreFailed(error);

        Toast.makeText(getActivity(), "加载错误:" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadEnd() {
        supervisor.setLoadEnd(true);
    }

    protected void setRefreshing(boolean refreshing) {
        SwipeRefreshLayout swipeRefreshLayout = getSwipeRefreshLayout();

        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(refreshing);
        }
    }
}
