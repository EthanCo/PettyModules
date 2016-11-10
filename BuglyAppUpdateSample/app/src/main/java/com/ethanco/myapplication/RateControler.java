package com.ethanco.myapplication;

/**
 * @Description 频率控制
 * Created by YOLANDA on 2015-12-07.
 */
public class RateControler {
    private int rateTime = 500;//默认500毫秒内按钮无效，可自己调整频率
    private long lastClickTime = 0;

    public RateControler() {
    }

    public RateControler(int rateTime) {
        this.rateTime = rateTime;
    }

    /**
     *
     * @return true 在rateTime之内 false 第一次或已超过rateTime的时间
     */
    public boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        lastClickTime = time;
        if (0 < timeD && timeD < rateTime) {
            return true;
        }
        return false;
    }
}
