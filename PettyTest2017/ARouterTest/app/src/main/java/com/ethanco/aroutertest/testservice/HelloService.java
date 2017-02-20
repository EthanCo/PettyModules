package com.ethanco.aroutertest.testservice;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2017/2/20
 */

public interface HelloService extends IProvider {
    void sayHello(String name);
}
