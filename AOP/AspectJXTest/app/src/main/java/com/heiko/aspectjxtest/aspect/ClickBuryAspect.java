package com.heiko.aspectjxtest.aspect;

import android.util.Log;
import android.view.View;

import com.heiko.aspectjxtest.anno.ClickBury;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * 点击事件 埋点
 */
@Aspect
public class ClickBuryAspect {
    private static final String TAG = "ClickBuryAspect";

    @Around("execution(@com.heiko.aspectjxtest.anno.ClickBury * *(..)) && @annotation(clickBury)")
    //在连接点进行方法替换
    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint, final ClickBury clickBury) throws Throwable {
        int[] ids = clickBury.value();
        for (int id : ids) {
            Log.i(TAG, "id:" + id);
            View target = (View) joinPoint.getArgs()[0];
            Log.i(TAG, "target.id:" + target.getId() + " id:" + id);
            if (target.getId() == id) {
                Log.i(TAG, "上报埋点:" + target.getTag());
                joinPoint.proceed();
                return;
            }
        }
        joinPoint.proceed();
    }
}

