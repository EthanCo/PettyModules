package com.ethanco.dagger2test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        student = new Student();
    }
}
