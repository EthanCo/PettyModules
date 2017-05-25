package com.lib.frame.view.abs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Description 对OnCreate方法分解成这几个方法
 * Created by EthanCo on 2016/10/9.
 */

public interface ICreate {

    //初始化变量和界面
    abstract View initVarAndView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    //用于继承自BaseActivity的基类 进行一些初始化，一般情况下，不用重写
    void midfield();

    //初始化事件
    abstract void initEvent();

    //开始执行
    void initDoing();
}
