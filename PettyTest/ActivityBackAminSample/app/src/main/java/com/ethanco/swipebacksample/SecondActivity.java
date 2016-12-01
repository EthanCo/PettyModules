package com.ethanco.swipebacksample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ethanco.swipebacksample.databinding.ActivitySecondBinding;
import com.r0adkll.slidr.Slidr;


/**
 * Detail see http://blog.csdn.net/eiuly/article/details/47356101
 */
public class SecondActivity extends AppCompatActivity {
    private ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second);
        setContentView(R.layout.activity_second);

        Slidr.attach(this);
        //Slidr.attach(this, R.color.colorAccent, R.color.colorPrimary);
    }
}
