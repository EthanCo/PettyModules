package com.ethanco.bottomsheettest;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void intro(View view) {
        BottomSheetBehavior behavior = BottomSheetBehavior.from(findViewById(R.id.scroll));
        //behavior.setPeekHeight();
        //behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        if(behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }else {
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    public void select(View view){

    }
}
