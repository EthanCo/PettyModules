package com.heiko.mypadingsample.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.heiko.mypadingsample.model.Gateway
import com.heiko.mypadingsample.model.GatewayDatabase

/**
 * TODO
 *
 * @author EthanCo
 * @since 2018/9/4
 */
class MainViewModel(app: Application) : AndroidViewModel(app) {
    val dao = GatewayDatabase.get(app).dataBase()

    val allGateways = LivePagedListBuilder(dao.getGatways(), PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .build()).build()

    fun insertGateway(name: String) {
        dao.insert(Gateway(0, name))
    }
}