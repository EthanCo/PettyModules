package com.ethanco.simpleframe.dagger.test;

import dagger.Module;
import dagger.Provides;

/**
 * Created by YOLANDA on 2016-01-11.
 */
@Module
public class MainActivityModule {
    @Provides
    public Person generatePerson() {
        return new Person("EthanCo", 18);
    }
}
