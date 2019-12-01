package com.ethanco.dagger2test;

import dagger.Component;

/**
 * (视频例子中的快递员)
 *
 * @author EthanCo
 * @since 2019/10/1
 */
//@Component
@Component(modules = StudentModule.class) //modules:一个快递员可以寄很多个快递
public interface StudentComponent {

    //送到收货地址 --- 注入到Activity
    void injectMainActivity(MainActivity mainActivity); //不具备多态功能的
}
