package com.nbhope.hopelauncher.lib.network.bean.response;

import android.text.TextUtils;

/**
 * Created by qiukeling on 2016/7/14.
 */
public abstract class BaseResponse<T extends BaseDataBean> implements IResponse<T> {
    protected String Cmd;
    private String Result;
    private T Data;

    public void setCmd(String Cmd) {
        this.Cmd = Cmd;
    }

    public String getCmd() {
        return Cmd;
    }

    public boolean getResult() {
        return "Success".equals(Result) || TextUtils.isEmpty(Result);
    }

    public void setResult(String result) {
        Result = result;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
