package com.ethanco.mydagger2test_326;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CoffeeComponent coffee = DaggerCoffeeComponent.builder().build();
        coffee.maker().brew();
    }
}
