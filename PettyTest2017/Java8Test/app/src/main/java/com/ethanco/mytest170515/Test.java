package com.ethanco.mytest170515;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Optional;
import java.util.Random;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2017/5/15
 */

public class Test {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        Optional<String> info = getInfo();
        System.out.println(info.get());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Optional<String> getInfo() {
        boolean getSuccess = new Random().nextBoolean();

        if (getSuccess) {
            return Optional.of("hello world!");
        } else {
            return Optional.of(null);
        }
    }
}
