package com.ethanco.eventbustest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class StickyActivity extends AppCompatActivity {
    private Button btnRegister;
    private Button btnSendSticky;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky);

        btnRegister = (Button) findViewById(R.id.btn_register);
        btnSendSticky = (Button) findViewById(R.id.btn_send_sticky);
        btnSendSticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageEvent messageEvent = new MessageEvent(What.TEST_STICKY);
                messageEvent.setStr1("sticky test count:" + count + " !");
                EventBus.getDefault().postSticky(messageEvent);
                count++;
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().register(StickyActivity.this);
            }
        });
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent) {
        Toast.makeText(StickyActivity.this, messageEvent.getStr1(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
