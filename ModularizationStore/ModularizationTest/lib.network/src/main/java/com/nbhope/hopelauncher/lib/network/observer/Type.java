package com.nbhope.hopelauncher.lib.network.observer;

/**
 * @Description 异常类型
 * Created by EthanCo on 2016/7/15.
 */
public enum Type {
    NO_OPERATE(1),;  //无权操作

    private int value;

    Type(int i) {
        this.value = i;
    }

    public static Type valueOf(int value) {
        if (value == 1) {
            return NO_OPERATE;
        }
        return NO_OPERATE;
    }

    public int getValue() {
        return value;
    }
}
