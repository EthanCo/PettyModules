package com.ethanco.mytest306;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ethanco.mytest306.databinding.ActivityMainBinding;
import com.ethanco.mytest306.mvvm.view.IMainView;
import com.ethanco.mytest306.mvvm.viewmodel.MainViewModel;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements IMainView {

    private ActivityMainBinding binding;

    @Inject
    Person person;

    @Inject
    int year;

    @Inject
    MainViewModel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Z-MainActivity", "onCreate person: " + person.getName());
        Log.i("Z-MainActivity", "onCreate year:: " + year);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //DaggerAppComponent.builder().build().inject(this);
        binding.setPerson(person);

        binding.tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        Log.i("Z-MainActivity", "onCreate viewModel: " + viewmodel.getClass().getName());
    }

    @Override
    public void initInject(AppComponent component) {
        component.inject(this);
    }
}
