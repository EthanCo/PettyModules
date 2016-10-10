package com.nbhope.hopelauncher.lib.network.bean.response;

/**
 * Created by qiukeling on 2016/7/14.
 */
public interface IResponse<T extends BaseDataBean> {

    boolean getResult();

    T getData();
}
