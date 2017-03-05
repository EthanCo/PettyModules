package com.ethanco.mydagger2test_326;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by EthanCo on 2016/3/26.
 */
@Singleton
@Component(modules = DripCoffeeModule.class)
public interface CoffeeComponent {
    CoffeeMaker maker();
}
