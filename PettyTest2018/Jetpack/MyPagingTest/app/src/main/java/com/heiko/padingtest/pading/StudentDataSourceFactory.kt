package com.heiko.padingtest.pading

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import java.util.concurrent.Executor

/**
 * 一个简单的数据源工厂，它提供了一种观察上次创建的数据源的方式，这使得我们能够将其网络请求状态等返回到UI界面
 * Created by juan on 2018/05/23.
 */
class StudentDataSourceFactory(private val retryExecutor: Executor): DataSource.Factory<String, StudentBean>() {
    val sourceLiveData= MutableLiveData<StudentDataSource>()
    override fun create(): DataSource<String, StudentBean> {
        val source= StudentDataSource(retryExecutor)
        sourceLiveData.postValue(source)
        return source
    }
}