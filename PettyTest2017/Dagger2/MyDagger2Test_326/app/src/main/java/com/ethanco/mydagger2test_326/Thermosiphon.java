package com.ethanco.mydagger2test_326;

import javax.inject.Inject;

/**
 * Created by EthanCo on 2016/3/26.
 */
public class Thermosiphon implements Pump {
    private final Heater heater;

    @Inject
    Thermosiphon(Heater heater) {
        this.heater = heater;
    }

    @Override
    public void pump() {
        if (heater.isHot()) {
            System.out.println("-> -> pumping");
        }
    }
}
