package com.ethanco.sample.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
import com.github.jdsjlzx.util.LuRecyclerViewStateUtils;
import com.github.jdsjlzx.view.LoadingFooter;
import com.lib.frame.view.BaseActivity;

import java.util.Collection;

public class MainActivity extends BaseActivity<ISampleView<ItemModel>, SampleViewModel> implements ISampleView, SwipeRefreshLayout.OnRefreshListener {


    private static final String TAG = "Z-Main";
    private static final int TOTAL_COUNTER = 63;
    private static final int LIST_PAGE_SIZE = 10;
    private ActivityMainBinding binding;
    private AdapterWrap<ItemModel> adapterWrap;
    private NovaSupervisor supervisor;

    @Override
    protected SampleViewModel createViewModel() {
        return new SampleViewModel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVar();
        initView();
        initEvent();
    }

    private void initVar() {

    }

    private void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initToolbar(R.string.app_name, false);

        adapterWrap = new AdapterWrap(new DataAdapter(this));
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setAdapter(adapterWrap);

        supervisor = new NovaSupervisor(binding.list);
        supervisor.setHeaderView(new SampleHeader(this));
        supervisor.openLoadMore();
    }

    private void initEvent() {
        binding.swipeRefreshLayout.setOnRefreshListener(this);

        supervisor.setListLoad((pageIndex, pageSize) -> mViewModel.loadMore(pageIndex, pageSize));

        adapterWrap.addOnItemClickListener((v, position) -> T.show("click:" + position));

        adapterWrap.addOnItemLongClickListener((v, position) -> T.show("longClick:" + position));

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

        binding.fab.setOnClickListener(view -> Snackbar
                .make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    @Override
    public void onRefresh() {
        mViewModel.refresh();
    }

    @Override
    public void onRefreshSuccess(Collection collection) {
        adapterWrap.getAdapter().setNewData(collection);
        binding.swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefreshFailed(String error) {
        binding.swipeRefreshLayout.setRefreshing(false);
        binding.fab.setOnClickListener(view -> Snackbar
                .make(binding.fab, "刷新错误:" + error, Snackbar.LENGTH_LONG).show());
    }

    @Override
    public void onLoadMoreSuccess(Collection collection) {
        adapterWrap.getAdapter().addAll(collection);
        LuRecyclerViewStateUtils.setFooterViewState(binding.list, LoadingFooter.State.Normal);
    }

    @Override
    public void onLoadMoreFailed(String error) {
        LuRecyclerViewStateUtils.setFooterViewState(binding.list, LoadingFooter.State.Normal);
        Snackbar.make(binding.fab, "加载错误:" + error, Snackbar.LENGTH_LONG).show();
    }
}
