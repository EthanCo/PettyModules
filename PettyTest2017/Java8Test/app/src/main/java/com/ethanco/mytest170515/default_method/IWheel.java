package com.ethanco.mytest170515.default_method;

import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2017/5/15
 */

public interface IWheel {
    void rotate();

    @RequiresApi(api = Build.VERSION_CODES.N)
    default void roll() {
        throw new IllegalStateException("not support roll");
    }
}
