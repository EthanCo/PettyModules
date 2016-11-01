package com.ethanco.androidannotest;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setItemId(null);
        //setAppName(1233);
        //setAlpha(-1);
        //String s = check(""); //如果check("") 返回值没有赋值，则会报错

        A a = new A();
        B b = new B();
    }

    public void setItemId(@NonNull String itemId) {
        Toast.makeText(getApplication(), itemId, Toast.LENGTH_SHORT).show();
    }

    public void setAppName(@StringRes int appName) {
        setItemId(getString(appName));
    }

    public void setAlpha(@FloatRange(from = 0.0, to = 1.0) float alpha) {
        findViewById(R.id.tvInfo).setAlpha(alpha);
    }

    @CheckResult(suggest = "#check()")
    public String check(String s) {
        s = s + "";
        s = check();
        return s;
    }

    //普通情况
    public String check() {
        Toast.makeText(getApplication(), "check", Toast.LENGTH_SHORT).show();
        return "check...";
    }

    class A {
        @CallSuper //要求重写的幻术需要调用被重写的函数
        public void say() {
            System.out.println("say");
        }

        public void run() {
            System.out.println("run");
        }
    }

    class B extends A {
        @Override
        public void say() {
            super.say(); //注释掉这句会报错
        }

        @Override
        public void run() {
            super.run();
        }
    }

    //函数调用需要一个函数
    @RequiresPermission(Manifest.permission.SET_WALLPAPER)
    public void method1() {

    }

    //函数调用需要声明集合中最少一个权限
    @RequiresPermission(anyOf = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION})
    public void method2() {
    }

    //函数调用需要声明集合中最少一个权限
    @RequiresPermission(anyOf = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION})
    public void method3() {

    }
}
