package com.ethanco.sample.view;

import java.util.Collection;

/**
 * Created by EthanCo on 2016/9/30.
 */

public interface ISampleView<M> {
    void onRefreshSuccess(Collection<M> collection);

    void onRefreshFailed(String error);

    void onLoadMoreSuccess(Collection<M> collection);

    void onLoadMoreFailed(String error);

    void setTotolCount(int totalCount);
}
