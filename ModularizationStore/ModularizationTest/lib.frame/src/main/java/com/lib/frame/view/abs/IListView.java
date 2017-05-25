package com.lib.frame.view.abs;

import java.util.Collection;

/**
 * Created by EthanCo on 2016/9/30.
 */

public interface IListView<M> {
    void onRefreshSuccess(Collection<M> collection);

    void onRefreshFailed(String error);

    void onLoadMoreSuccess(Collection<M> collection);

    void onLoadMoreFailed(String error);

    void loadEnd();
}
