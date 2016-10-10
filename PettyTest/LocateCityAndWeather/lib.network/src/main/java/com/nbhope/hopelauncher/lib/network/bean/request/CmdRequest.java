package com.nbhope.hopelauncher.lib.network.bean.request;

public class CmdRequest extends BaseRequest {

    private Object Data;
    private Result Result;

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    public Result getResult() {
        return Result;
    }

    public void setResult(Result Result) {
        this.Result = Result;
    }

    public static class Result {
        private String Result;

        public String getResult() {
            return Result;
        }

        public void setResult(String result) {
            Result = result;
        }

    }
}
