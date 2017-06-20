package com.ethanco.mynumberpickertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.shawnlin.numberpicker.NumberPicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Z-MainActivity";
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        Log.i(TAG, " hourOfDay:" + hourOfDay + " minute:" + minute+" hour:"+hour);
        numberPicker = (NumberPicker) findViewById(R.id.number_picker);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.i(TAG, "onValueChange:" + newVal);
            }
        });

    }
}
