package com.heiko.aspectjxtest.aspect;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Click时间 切面，可用作防抖之类的处理
 *
 * @author Heiko
 * @date 2020/3/26 0026
 */
@Aspect
public class ClickCheckAspect {
    @Pointcut("execution(void android.view.View.OnClickListener.onClick(..))")
    public void methodViewOnClick() {
    }

    @Around("methodViewOnClick()")
    public void aroundViewOnClick(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.i("ClickCheckAspect", "click--->");
        joinPoint.proceed();
    }
}
