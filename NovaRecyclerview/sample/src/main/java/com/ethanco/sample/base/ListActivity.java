package com.ethanco.sample.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.ethanco.nova.NovaSupervisor;
import com.ethanco.sample.view.ISampleView;
import com.lib.frame.view.BaseActivity;
import com.lib.frame.viewmodel.BaseViewModel;

import java.util.Collection;

/**
 * @Description Recyclerview列表 Adapter
 * Created by EthanCo on 2016/10/8.
 */

public abstract class ListActivity<B, T extends BaseViewModel<ISampleView<B>>> extends BaseActivity<ISampleView<B>, T> implements ISampleView<B> {
    protected NovaSupervisor supervisor;

    @Override
    protected void midfield() {
        super.midfield();
        supervisor = createSupervisor();
    }

    protected abstract NovaSupervisor createSupervisor();

    protected abstract SwipeRefreshLayout getSwipeRefreshLayout();


    @Override
    public void onRefreshSuccess(Collection<B> collection) {
        supervisor.onRefreshSuccess(collection);
        setRefreshing(false);
    }

    @Override
    public void onRefreshFailed(String error) {
        supervisor.onRefreshFailed(error);
        setRefreshing(false);

        //com.ethanco.sample.utils.T.show(binding.fab, "刷新错误:" + error);
        Toast.makeText(ListActivity.this, "刷新错误:" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMoreSuccess(Collection<B> collection) {
        supervisor.onLoadMoreSuccess(collection);
    }

    @Override
    public void onLoadMoreFailed(String error) {
        supervisor.onLoadMoreFailed(error);

        //com.ethanco.sample.utils.T.show(binding.fab, "加载错误:" + error);
        Toast.makeText(ListActivity.this, "加载错误:" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setTotolCount(int totalCount) {
        supervisor.setTotalCount(totalCount);
    }

    protected void setRefreshing(boolean refreshing) {
        SwipeRefreshLayout swipeRefreshLayout = getSwipeRefreshLayout();

        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(refreshing);
        }
    }
}
