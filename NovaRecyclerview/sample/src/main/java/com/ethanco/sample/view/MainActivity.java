package com.ethanco.sample.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.ethanco.nova.AdapterWrap;
import com.ethanco.nova.NovaRecyclerView;
import com.ethanco.nova.NovaSupervisor;
import com.ethanco.sample.R;
import com.ethanco.sample.adapter.DataAdapter;
import com.ethanco.sample.bean.ItemModel;
import com.ethanco.sample.databinding.ActivityMainBinding;
import com.ethanco.sample.utils.T;
import com.ethanco.sample.viewmodel.SampleViewModel;
import com.ethanco.sample.widget.SampleHeader;
import com.github.jdsjlzx.view.LoadingFooter;
import com.lib.frame.view.BaseActivity;

import java.util.Collection;

public class MainActivity extends BaseActivity<ISampleView<ItemModel>, SampleViewModel> implements ISampleView, SwipeRefreshLayout.OnRefreshListener {
    private ActivityMainBinding binding;

    private AdapterWrap<ItemModel> adapterWrap;
    private NovaSupervisor supervisor;

    @Override
    protected SampleViewModel createViewModel() {
        return new SampleViewModel();
    }

    @Override
    protected void initVarAndView(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);

        adapterWrap = new AdapterWrap(new DataAdapter(this));
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setAdapter(adapterWrap);

        supervisor = new NovaSupervisor(binding.list);
        supervisor.setHeaderView(new SampleHeader(this));
        supervisor.openLoadMore();
    }

    @Override
    protected void initEvent() {
        binding.swipeRefreshLayout.setOnRefreshListener(this);

        supervisor.setLoadMoreListener((pageIndex, pageSize) -> mViewModel.loadMore(pageIndex, pageSize));

        //如果同时使用上拉刷新，请添加这个Listener，防止与下拉加载的冲突
        supervisor.setRefreshStateListener(() -> binding.swipeRefreshLayout.isRefreshing());

        supervisor.setErrorClickListener(view -> supervisor.loadMore());

        adapterWrap.addOnItemClickListener((v, position) -> T.show(binding.fab, "click:" + position));

        adapterWrap.addOnItemLongClickListener((v, position) -> T.show(binding.fab, "long click:" + position));

        binding.list.addOnScrollBottomListener(() -> {

        });


        binding.list.addOnScrolledListener((start, end) -> {

        });

        binding.list.addOnScrollStateChangedListener(state -> {

        });


        binding.list.addScrollListener(new NovaRecyclerView.onScrollListener() {

            @Override
            public void onScrollUp() {

            }

            @Override
            public void onScrollDown() {

            }

            @Override
            public void onBottom() {

            }

            @Override
            public void onScrolled(int i, int i1) {

            }

            @Override
            public void onScrollStateChanged(int i) {

            }
        });

        binding.fab.setOnClickListener(view -> T.show(binding.fab, "Hello"));
    }

    @Override
    protected void initDoing() {
        super.initDoing();

        reload();
    }

    private void reload() {
        binding.swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        mViewModel.refresh();
    }

    @Override
    public void onRefreshSuccess(Collection collection) {
        adapterWrap.getAdapter().setNewData(collection);
        binding.swipeRefreshLayout.setRefreshing(false);
        supervisor.setFooterViewState(LoadingFooter.State.Normal);
    }

    @Override
    public void onRefreshFailed(String error) {
        T.show(binding.fab, "刷新错误:" + error);
        binding.swipeRefreshLayout.setRefreshing(false);
        supervisor.setFooterViewState(LoadingFooter.State.Normal);
    }

    @Override
    public void onLoadMoreSuccess(Collection collection) {
        adapterWrap.getAdapter().addAll(collection);
        supervisor.setFooterViewState(LoadingFooter.State.Normal);
    }

    @Override
    public void onLoadMoreFailed(String error) {
        supervisor.setFooterViewState(LoadingFooter.State.NetWorkError);
        T.show(binding.fab, "加载错误:" + error);
    }

    @Override
    public void setTotolCount(int totalCount) {
        supervisor.setTotalCount(totalCount);
    }
}
