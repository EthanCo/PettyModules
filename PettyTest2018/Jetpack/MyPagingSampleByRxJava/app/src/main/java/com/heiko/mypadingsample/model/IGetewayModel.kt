package com.heiko.mypadingsample.model

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.heiko.mypadingsample.bean.Gateway

/**
 * @author EthanCo
 * @since 2018/9/4
 */
@Dao
interface IGetewayModel {
    @Query ("SELECT * FROM Gateway ORDER BY name")
    fun getGatways(): DataSource.Factory<Int, Gateway>

    @Insert
    fun insert(gateways: List<Gateway>)

    @Insert
    fun insert(gateway: Gateway)
}
