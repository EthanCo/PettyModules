package com.ethanco.eventbustest;

import android.app.Application;

import com.code.xyz.TestEventBusIndex;

import org.greenrobot.eventbus.EventBus;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2017/7/19
 * -     ┌─┐       ┌─┐
 * -  ┌──┘ ┴───────┘ ┴──┐
 * -  │                 │
 * -  │       ───       │
 * -  │  ─┬┘       └┬─  │
 * -  │                 │
 * -  │       ─┴─       │
 * -  │                 │
 * -  └───┐         ┌───┘
 * -      │         │
 * -      │         │
 * -      │         │
 * -      │         └──────────────┐
 * -      │                        │
 * -      │                        ├─┐
 * -      │                        ┌─┘
 * -      │                        │
 * -      └─┐  ┐  ┌───────┬──┐  ┌──┘
 * -        │ ─┤ ─┤       │ ─┤ ─┤
 * -        └──┴──┘       └──┴──┘
 * --------------- 神兽保佑 ---------------
 * --------------- 永无BUG! ---------------
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //如果你做了单例的话，放到单例中，如果没有就在Application中放上这句就行了
        EventBus.builder().addIndex(new TestEventBusIndex()).installDefaultEventBus();
    }
}
