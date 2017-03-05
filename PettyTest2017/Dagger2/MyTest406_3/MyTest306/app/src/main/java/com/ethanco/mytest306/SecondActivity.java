package com.ethanco.mytest306;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ethanco.mytest306.databinding.ActivityMainBinding;

import javax.inject.Inject;

/**
 * Created by EthanCo on 2016/4/6.
 */
public class SecondActivity extends BaseActivity {

    @Inject
    String city;

    @Inject
    @CityCodeAno
    int cityCode;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Log.i("Z-SecondActivity", "onCreate city: " + city + "_" + cityCode);
        binding.tvInfo.setText(city + "_" + cityCode);
    }

    @Override
    public void initInject(AppComponent component) {
        component.inject(this);
    }
}
