package com.ethanco.mydagger2test;

import dagger.Module;
import dagger.Provides;

/**
 * Created by EthanCo on 2016/3/19.
 */
@Module
public class ContainerModule {
    @Provides
    public ShoppingCartModel provideContainerModule(){
        return new ShoppingCartModel();
    }
}
