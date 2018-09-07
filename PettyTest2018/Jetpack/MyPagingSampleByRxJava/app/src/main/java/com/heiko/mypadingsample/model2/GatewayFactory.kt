package com.heiko.mypadingsample.model2

import android.arch.paging.DataSource
import com.heiko.mypadingsample.bean.Gateway

/**
 * @author EthanCo
 * @since 2018/9/5
 */
class GatewayFactory : DataSource.Factory<Int, Gateway>() {
    override fun create(): DataSource<Int, Gateway>  = GatewayModel2()

}