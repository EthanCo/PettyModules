package com.ethanco.propertiestest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String dir = DirectionCompat.getCustemDir(this, "Hope", "properties");
        PropertiesHelper helper = new PropertiesHelper(dir, "MyTest");

        Map<String, String> map = new HashMap<>();
        map.put("key01", "value001");
        map.put("key02", "value002");
        helper.setProperties(map);

        Map<String, String> result = helper.getProperties();
        System.out.println("key01--->>>" + result.get("key01"));
    }
}
