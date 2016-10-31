package com.ethanco.hugotest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import hugo.weaving.DebugLog;

public class MainActivity extends AppCompatActivity {

    private String TAG = "Z-Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v(TAG, "onCreate : ");
        int result = fibonacci(1);
        Charmer charmer = new Charmer("ethanco");
        String name = charmer.askHowAreYou();
    }


    @DebugLog
    private int fibonacci(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Number must be greater than zero.");
        }
        if (number == 1 || number == 2) {
            return 1;
        }
        // NOTE: Don't ever do this. Use the iterative approach!
        return fibonacci(number - 1) + fibonacci(number - 2);
    }

    @DebugLog
    class Charmer {
        private final String name;

        Charmer(String name) {
            this.name = name;
        }

        public String askHowAreYou() {
            return "How are you " + name + "?";
        }

        @Override
        public String toString() {
            return "Charmer name:" + name;
        }
    }
}
