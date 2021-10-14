package com.heiko.aspectjxtest.aspect;

import android.util.Log;

import com.heiko.aspectjxtest.anno.CheckLogin;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by LS
 * 通过CheckLogin注解检查用户是否登陆注解，通过aop切片的方式在编译期间织入源代码中
 * 功能：检查用户是否登陆，未登录则跳转登录，不会执行下面的逻辑
 */
@Aspect
public class CheckLoginAspect {

    //在连接点进行方法替换
    @Around("execution(@com.heiko.aspectjxtest.anno.CheckLogin * *(..)) && @annotation(checkLogin)")
    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint, CheckLogin checkLogin) throws Throwable {
        Log.i("CheckLoginAspect", "登录验证");
    }
}
