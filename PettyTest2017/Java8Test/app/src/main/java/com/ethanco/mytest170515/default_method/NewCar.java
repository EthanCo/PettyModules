package com.ethanco.mytest170515.default_method;

import android.util.Log;

/**
 * @author EthanCo
 * @since 2017/5/15
 */

public class NewCar implements IWheel {

    private static final String TAG = "Z-NewCar";

    @Override
    public void rotate() {
        Log.i(TAG, "rotate");
    }

    @Override
    public void roll() {
        Log.i(TAG, "roll");
    }
}
