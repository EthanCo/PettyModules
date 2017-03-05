package com.ethanco.mytest306;

import dagger.Component;

/**
 * Created by EthanCo on 2016/4/6.
 */
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MainActivity activity);

    void inject(SecondActivity activity);
}
