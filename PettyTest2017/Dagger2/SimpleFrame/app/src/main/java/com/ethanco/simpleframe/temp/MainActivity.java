package com.ethanco.simpleframe.temp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ethanco.simpleframe.dagger.test.DaggerMainActivityComponent;
import com.ethanco.simpleframe.dagger.test.MainActivityComponent;
import com.ethanco.simpleframe.dagger.test.MainActivityModule;
import com.ethanco.simpleframe.dagger.test.Person;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {
    @Inject
    Person person;

    private MainActivityComponent activityComponent;

    //private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        activityComponent = DaggerMainActivityComponent.builder().mainActivityModule(new MainActivityModule()).build();
        activityComponent.inject(this);
        Log.i("Z-MainActivity", "onCreate : "+person.getName()+person.getAge());
//        binding = DataBindingUtil.setContentView(this, com.bocai.boc_tongxing.simpleframesample.R.layout.activity_main);
//        binding.tvText.setText("Hello");
//
//        setSupportActionBar(binding.toolbar);
//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

}
