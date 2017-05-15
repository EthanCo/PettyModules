package com.ethanco.mytest170515.default_method;

import android.util.Log;

/**
 * 旧车
 *
 * @author EthanCo
 * @since 2017/5/15
 */

public class OldCar implements IWheel {
    private static final String TAG = "Z-OldCar";

    @Override
    public void rotate() {
        Log.i(TAG, "rotate");
    }
}
