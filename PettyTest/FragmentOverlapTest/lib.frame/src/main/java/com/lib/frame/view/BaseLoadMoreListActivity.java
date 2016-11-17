package com.lib.frame.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.ethanco.nova.NovaSupervisor;
import com.lib.frame.view.abs.IListView;
import com.lib.frame.viewmodel.BaseViewModel;

import java.util.Collection;

/**
 * @Description Recyclerview列表 Activity
 * Created by EthanCo on 2016/10/8.
 */

public abstract class BaseLoadMoreListActivity<B, T extends BaseViewModel<IListView<B>>> extends BaseActivity<IListView<B>, T> implements IListView<B> {
    protected NovaSupervisor supervisor;

    @Override
    public void midfield() {
        super.midfield();
        supervisor = createSupervisor();
    }

    protected abstract NovaSupervisor createSupervisor();

    protected abstract SwipeRefreshLayout getSwipeRefreshLayout();


    @Override
    public void onRefreshSuccess(Collection<B> collection) {
        supervisor.setLoadEnd(false);
        supervisor.onRefreshSuccess(collection);
        setRefreshing(false);
    }

    @Override
    public void onRefreshFailed(String error) {
        supervisor.onRefreshFailed(error);
        setRefreshing(false);

        Toast.makeText(BaseLoadMoreListActivity.this, "刷新错误:" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMoreSuccess(Collection<B> collection) {
        supervisor.onLoadMoreSuccess(collection);
    }

    @Override
    public void onLoadMoreFailed(String error) {
        supervisor.onLoadMoreFailed(error);

        Toast.makeText(BaseLoadMoreListActivity.this, "加载错误:" + error, Toast.LENGTH_SHORT).show();
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
