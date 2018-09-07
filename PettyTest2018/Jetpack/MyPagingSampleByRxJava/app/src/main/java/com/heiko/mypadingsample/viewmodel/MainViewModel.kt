package com.heiko.mypadingsample.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import com.heiko.mypadingsample.bean.Gateway
import com.heiko.mypadingsample.model.GatewayDatabase
import com.heiko.mypadingsample.model2.GatewayFactory
import io.reactivex.BackpressureStrategy

/**
 * @author EthanCo
 * @since 2018/9/4
 */
class MainViewModel(app: Application) : AndroidViewModel(app) {
    val dao = GatewayDatabase.get(app).dataBase()

    val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .build()
    val allGatewayRxJava = RxPagedListBuilder(dao.getGatways(), config).buildFlowable(BackpressureStrategy.BUFFER)
    val allGateways = LivePagedListBuilder(dao.getGatways(), config).build()
    //val allGateways2 = LivePagedListBuilder(, config).build()

    val allGatewayRxJava2 = RxPagedListBuilder(GatewayFactory(), config).buildFlowable(BackpressureStrategy.BUFFER)

    fun insertGateway(name: String) {
        dao.insert(Gateway(0, name))
    }
}