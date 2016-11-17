package com.ethanco.fragmentoverlaptest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lib.frame.view.BaseFragmentActivity;
import com.lib.frame.viewmodel.BaseViewModel;

public class MainActivity extends BaseFragmentActivity {
    private static final String TAG = "Z-MainActivity";

    @Override
    protected BaseViewModel createViewModel() {
        return null;
    }

    @Override
    public void initVarAndView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        Button btnFragment1 = (Button) findViewById(R.id.btn_fragment_1);
        Button btnFragment2 = (Button) findViewById(R.id.btn_fragment_2);

        btnFragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(BlankFragment.newInstance());
            }
        });

        btnFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(Blank2Fragment.newInstance());
            }
        });

        switchFragment(BlankFragment.newInstance());
    }

    private void switchFragment(Fragment fragment) {
        Log.i(TAG,"switchFragment:" + fragment.getClass().getSimpleName());
        changeFragment(fragment, R.id.layout_container);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void initEvent() {

    }
}
