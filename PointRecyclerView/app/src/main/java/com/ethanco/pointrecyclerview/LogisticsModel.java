package com.ethanco.pointrecyclerview;

/**
 * @Description Bean
 * Created by EthanCo on 2016/3/10.
 */
public class LogisticsModel {
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LogisticsModel(String city, String status, String time) {
        this.city = city;
        this.status = status;
        this.time = time;
    }

    private String city;
    private String status;
    private String time;
}
