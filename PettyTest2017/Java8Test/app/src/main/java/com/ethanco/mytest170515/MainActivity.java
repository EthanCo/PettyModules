package com.ethanco.mytest170515;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.ethanco.mytest170515.stream_test.Dish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Z-Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(v -> toast("Hello World!"));

        //排序
        List<Apple> apples = new ArrayList<>();
        Collections.sort(apples, new Comparator<Apple>() {
            @Override
            public int compare(Apple t1, Apple t2) {
                return t1.getWeight().compareTo(t2.getWeight());
            }
        });

        Collections.sort(apples, (t1, t2) -> t1.getWeight().compareTo(t2.getWeight()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            apples.sort(Comparator.comparing(Apple::getWeight));
        }

        List<Dish> menu = new ArrayList<>();
        for (Dish dish : menu) {
            if (dish.getCalories() > 300) {
                Log.i(TAG, "greaterThan300:" + dish.getName());
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            menu.stream()
                    .filter(dish -> dish.getCalories() > 300)
                    .forEach(name -> Log.i(TAG, "greaterThan300:" + name));
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            List<String> menuNames = menu.stream()
                    .filter(dish -> dish.getCalories() > 300)
                    .map(Dish::getName)
                    .collect(Collectors.toList());
        }


    }

    public void toast(CharSequence message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
