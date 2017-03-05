package com.ethanco.mydagger2test_326;

import dagger.Module;
import dagger.Provides;

/**
 * Created by EthanCo on 2016/3/26.
 */
@Module
public class PumbModule {
    @Provides
    public Pump providePumb(Thermosiphon pump){
        return pump;
    }

}
