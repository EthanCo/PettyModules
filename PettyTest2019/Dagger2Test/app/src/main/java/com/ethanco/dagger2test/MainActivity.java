package com.ethanco.dagger2test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //student = new Student();

        //把自己的这个Activity交给Dagger2框架
        DaggerStudentComponent.create().injectMainActivity(this);
    }
}
