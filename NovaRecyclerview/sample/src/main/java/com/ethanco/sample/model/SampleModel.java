package com.ethanco.sample.model;

import android.os.SystemClock;
import android.support.annotation.NonNull;

import com.ethanco.sample.R;
import com.ethanco.sample.bean.ItemModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by EthanCo on 2016/9/30.
 */

public class SampleModel implements ISampleModel {

    @Override
    public Observable<List<ItemModel>> reLoadDataFromNet() {


        return Observable.interval(1, 1, TimeUnit.MILLISECONDS)
                .take(20)
                .map(v -> Integer.valueOf(String.valueOf(v)))
                .map(i -> generationItemModel(i))
                .toList()
                .doOnNext(result -> SystemClock.sleep(2500)); //模拟网络加载
    }

    @Override
    public Observable<List<ItemModel>> loadDataFromNet(int pageIndex, int pageSize) {
        int start = pageIndex * pageSize;
        int end = (pageIndex + 1) * pageSize;
        if (end > 64) { //模拟服务器最大的数据
            end = 64;
        }
        int count = end - start;

        return Observable.range(start, count)
                .map(i -> generationItemModel(i))
                .toList()
                .doOnNext(result -> SystemClock.sleep(800)); //模拟网络加载
    }

    @NonNull
    private ItemModel generationItemModel(Integer i) {
        ItemModel itemModel = new ItemModel();
        itemModel.id = i;
        itemModel.title = "title" + i;
        itemModel.imgRes = R.mipmap.ic_launcher;
        return itemModel;
    }
}
