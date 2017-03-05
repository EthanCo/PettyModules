package com.ethanco.mydagger2test;

import dagger.Module;
import dagger.Provides;

/**
 * Created by EthanCo on 2016/3/19.
 */
@Module
public class ActivityModule {
    @Provides
    public Person providePersonModel() {
        return new Person(21, "EthanCO");
    }
}
