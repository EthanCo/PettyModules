package com.ethanco.proidplugintest.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Description 动态代理类
 * Created by EthanCo on 2016/9/8.
 */
public class DynamicProxy {
    public static <T> T newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) {
        return (T) Proxy.newProxyInstance(loader, interfaces, h);
    }
}
