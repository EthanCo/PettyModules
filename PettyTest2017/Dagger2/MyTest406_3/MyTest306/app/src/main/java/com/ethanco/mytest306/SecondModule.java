package com.ethanco.mytest306;

import dagger.Module;
import dagger.Provides;

/**
 * Created by EthanCo on 2016/4/6.
 */
@Module
public class SecondModule {

    @Provides
    public String provideCity() {
        return "杭州";
    }

    @Provides
    @CityCodeAno
    int provideCityCode(){
        return 001;
    }
}
