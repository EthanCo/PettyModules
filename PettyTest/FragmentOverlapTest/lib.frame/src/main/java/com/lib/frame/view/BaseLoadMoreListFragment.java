package com.lib.frame.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.ethanco.nova.NovaSupervisor;
import com.lib.frame.view.abs.IListView;
import com.lib.frame.viewmodel.BaseViewModel;

import java.util.Collection;

/**
 * @Description Recyclerview列表 Fragment
 * Created by EthanCo on 2016/6/13.
 */
@SuppressWarnings("unchecked")
public abstract class BaseLoadMoreListFragment<B, T extends BaseViewModel<IListView<B>>> extends BaseFragment<IListView<B>, T> implements IListView<B> {
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
        supervisor.onRefreshSuccess(collection);
        setRefreshing(false);
    }

    @Override
    public void onRefreshFailed(String error) {
        supervisor.onRefreshFailed(error);
        setRefreshing(false);

        //com.ethanco.sample.utils.T.show(binding.fab, "刷新错误:" + error);
        Toast.makeText(getActivity(), "刷新错误:" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMoreSuccess(Collection<B> collection) {
        supervisor.onLoadMoreSuccess(collection);
    }

    @Override
    public void onLoadMoreFailed(String error) {
        supervisor.onLoadMoreFailed(error);

        //com.ethanco.sample.utils.T.show(binding.fab, "加载错误:" + error);
        Toast.makeText(getActivity(), "加载错误:" + error, Toast.LENGTH_SHORT).show();
    }

    protected void setRefreshing(boolean refreshing) {
        SwipeRefreshLayout swipeRefreshLayout = getSwipeRefreshLayout();

        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(refreshing);
        }
    }
}
