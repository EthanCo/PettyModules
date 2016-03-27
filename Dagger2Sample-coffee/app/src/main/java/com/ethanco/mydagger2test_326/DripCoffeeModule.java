package com.ethanco.mydagger2test_326;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by EthanCo on 2016/3/26.
 */
@Module(includes = PumbModule.class)
public class DripCoffeeModule {
    @Singleton
    @Provides
    public Heater provideHeater(){
        return new ElectricHeater();
    }
}
