package com.ethanco.mytest306;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by EthanCo on 2016/4/6.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initInject(App.getInstance().getBuilder().appModule(new AppModule(this)).build());
    }

    public abstract void initInject(AppComponent component);
}
