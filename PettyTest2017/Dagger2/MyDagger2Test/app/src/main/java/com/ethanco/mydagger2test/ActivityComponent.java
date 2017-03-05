package com.ethanco.mydagger2test;

import dagger.Component;

/**
 * Created by EthanCo on 2016/3/19.
 */
@Component(modules = ActivityModule.class)
public interface ActivityComponent {
    //void inject(MainActivity activity);
    Person person();
}
