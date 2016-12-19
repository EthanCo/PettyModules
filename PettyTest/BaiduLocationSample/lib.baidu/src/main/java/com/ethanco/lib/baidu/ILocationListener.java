package com.ethanco.lib.baidu;

import com.baidu.location.BDLocation;

/**
 * 自定义的 定位回调 简易
 *
 * @author EthanCo
 * @since 2016/12/19
 */

public interface ILocationListener {
    void onLocationSuccess(BDLocation location);

    void onLocationFaild(int locType);
}
