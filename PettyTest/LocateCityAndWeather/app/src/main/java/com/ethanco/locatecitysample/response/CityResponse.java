package com.ethanco.locatecitysample.response;

/**
 * @Description 百度地图 城市Response
 * Created by EthanCo on 2016/10/10.
 */

public class CityResponse {
    /**
     * resultcode : 200
     * reason : Return Successd!
     * result : {"area":"浙江省宁波市","location":"电信"}
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    /**
     * area : 浙江省宁波市
     * location : 电信
     */

    private ResultBean result;
    private int error_code;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        private String area;
        private String location;

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}
