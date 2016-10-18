package com.ethanco.sample.viewmodel;

import com.ethanco.sample.bean.ItemModel;
import com.ethanco.sample.model.ISampleModel;
import com.ethanco.sample.model.SampleModel;
import com.ethanco.sample.view.ISampleView;
import com.lib.frame.viewmodel.BaseViewModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by EthanCo on 2016/9/30.
 */

public class SampleViewModel extends BaseViewModel<ISampleView<ItemModel>> {
    private ISampleModel model;

    public SampleViewModel() {
        this.model = new SampleModel();
    }

    public void refresh() {
        model.reLoadDataFromNet()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(result -> getView().onRefreshSuccess(result),
                        throwable -> getView().onRefreshFailed(throwable.getLocalizedMessage()),
                        () -> {

                        });
    }

    public void loadMore(int pageIndex, int pageSize) {
        model.loadDataFromNet(pageIndex, pageSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(result -> {
                            //设置总共的数据Count，一般为从服务器中获取到，此处为模拟
                            getView().setTotolCount(64);
                            getView().onLoadMoreSuccess(result);
                        },
                        throwable -> getView().onLoadMoreFailed(throwable.getLocalizedMessage()),
                        () -> {

                        });
    }
}
