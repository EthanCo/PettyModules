package com.ethanco.mybehaviortest_08;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    float originX;
    float originY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View first = findViewById(R.id.layoutBlue);
        first.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("Z-MainActivity", "onTouch : " + event.getAction());
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        originX = event.getX();
                        originY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float dx = event.getX() - originX;
                        float dy = event.getY() - originY;
                        originX = event.getX();
                        originY = event.getY();
                        ((View) first.getParent()).scrollBy((int) -dx, (int) -dy);
                        first.invalidate();
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    default:
                }
                return true;
            }
        });
    }
}
