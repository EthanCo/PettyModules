package com.ethanco.sample.model;

import com.ethanco.sample.bean.ItemModel;

import java.util.List;

import rx.Observable;

/**
 * Created by EthanCo on 2016/9/30.
 */

public interface ISampleModel {
    Observable<List<ItemModel>> loadDataFromNet(int pageIndex, int pageSize);

    Observable<List<ItemModel>> reLoadDataFromNet();
}
