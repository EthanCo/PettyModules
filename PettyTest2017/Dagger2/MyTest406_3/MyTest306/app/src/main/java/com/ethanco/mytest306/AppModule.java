package com.ethanco.mytest306;

import com.ethanco.mytest306.mvvm.viewmodel.MainViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by EthanCo on 2016/4/6.
 */
@Module(includes = {SecondModule.class})
public class AppModule {

    private final BaseActivity activity;

    public AppModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    public Person providePerson() {
        return new Person("zhk", 17);
    }

    @Provides
    int provideYear() {
        return 2016;
    }

    @Provides
    public MainViewModel provideMainViewModel() {
        return new MainViewModel(activity);
    }
}
