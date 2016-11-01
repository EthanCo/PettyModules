package com.ethanco.mylib;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Zhk on 2016/10/31.
 */

public class MyUtil {
    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
