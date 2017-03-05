package com.ethanco.mydagger2test;

import dagger.Component;

/**
 * Created by EthanCo on 2016/3/19.
 */
@Component(dependencies = ActivityComponent.class, modules = ContainerModule.class)
public interface ContainerComponent {
    void inject(MainActivity activity);
}
