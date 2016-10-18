package com.ethanco.nova.inter;

/**
 * @Description 列表加载
 * Created by EthanCo on 2016/9/30.
 */

public interface LoadMoreListener {
    void loadMore(int pageIndex, int pageSize);
}
