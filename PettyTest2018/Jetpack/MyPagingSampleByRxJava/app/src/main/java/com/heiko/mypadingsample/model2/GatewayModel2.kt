package com.heiko.mypadingsample.model2

import android.arch.paging.ItemKeyedDataSource
import android.util.Log
import com.heiko.mypadingsample.bean.Gateway

/**
 * Datasource是数据源相关的类，针对不同场景，Paging 提供了三种 Datasource:
 *（1）PageKeyedDataSource：如果页面在加载时插入一个/下一个键，例如：从网络获取社交媒体的帖子，可能需要将nextPage加载到后续的加载中；
 *（2）ItemKeyedDataSource：在需要让使用的数据的item从N条增加到N+1条时使用；
 *（3）PositionalDataSource：如果需要从数据存储的任意位置来获取数据页面。此类支持你从任何位置开始请求一组item的数据集。例如，该请求可能会返回从位置1200条开始的20个数据项；
 * 根据使用场景选择实现DataSource不同的抽象类， 使用时需实现请求加载数据的方法。其中这三种Datasource 都需要实现 loadInitial()方法， 各自都封装了请求初始化数据的参数类型 LoadInitialParams。 不同的是分页加载数据的方法， PageKeyedDataSource和 ItemKeyedDataSource比较相似， 需要实现 loadBefore()和 loadAfter()方法，同样对请求参数做了封装，即 LoadParams。 PositionalDataSource需要实现 loadRange()。
 *
 */
class GatewayModel2 : ItemKeyedDataSource<Int, Gateway>() {
    val TAG = "Z-GatewayModel2"
    private var index: Int = 0

    //用于接收初始第一页加载的数据，在这里需要将获取到的数据通过
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Gateway>) {
        Log.d(TAG, "loadInitial params.requestedLoadSize:" + params.requestedLoadSize + " params.requestedInitialKey:" + params.requestedInitialKey)
        index = 0
        val list = ArrayList<Gateway>()
        for (i in index..params.requestedLoadSize) {
            list.add(Gateway(i, "Gateway$i"))
        }
        index++
        callback.onResult(list)
    }

    //用于接收后面每一页加载的数据，使用方法和loadInitial一样
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Gateway>) {
        Log.d(TAG, "loadAfter: params.requestedLoadSize:" + params.requestedLoadSize + " params.key:" + params.key)
        val list = ArrayList<Gateway>()
        if (index < 5) {
            val start = index * params.requestedLoadSize
            val end = (index + 1) * params.requestedLoadSize
            for (i in start..end) {
                list.add(Gateway(i, "Gateway$i"))
            }
            index++
        }
        callback.onResult(list)
    }

    //指定的密钥之前加载列表数据
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Gateway>) {
        Log.d(TAG, "loadBefore")
    }

    //返回与给定项目关联的密钥
    override fun getKey(item: Gateway): Int {
        Log.d(TAG, "getKey:" + item.id)
        return item.id
    }

}