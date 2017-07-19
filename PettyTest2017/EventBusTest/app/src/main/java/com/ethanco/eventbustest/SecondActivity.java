package com.ethanco.eventbustest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by EthanCo on 2017/7/17.
 */

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnPost;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnPost = (Button) findViewById(R.id.button_post);
        btnPost.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        MessageEvent message = new MessageEvent(What.TEST);
        message.setStr1("Say Hello");
        EventBus.getDefault().post(message);
    }
}
