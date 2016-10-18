package com.nbhope.hopelauncher.lib.network.bean.request;

/**
 * @Description 网络请求基类
 * Created by EthanCo on 2016/5/23.
 */
public class BaseRequest {
    protected String Cmd;

    public void setCmd(String Cmd) {
        this.Cmd = Cmd;
    }

    public String getCmd() {
        return Cmd;
    }
}
