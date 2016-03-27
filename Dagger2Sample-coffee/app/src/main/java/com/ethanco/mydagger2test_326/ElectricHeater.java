package com.ethanco.mydagger2test_326;

/**
 * Created by EthanCo on 2016/3/26.
 */
public class ElectricHeater implements Heater {
    boolean heating;

    @Override
    public void on() {
        System.out.println("~~~ heating ~~~");
    }

    @Override
    public void off() {
        this.heating = false;
    }

    @Override
    public boolean isHot() {
        return false;
    }
}
