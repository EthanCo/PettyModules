package com.ethanco.skintest;

import android.app.Application;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;

/**
 * App
 *
 * @author EthanCo
 * @since 2017/3/20
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SkinCompatManager.init(this)                          // 基础控件换肤初始化
                .addInflater(new SkinMaterialViewInflater())  // material design 控件换肤初始化[可选]
                .addInflater(new SkinCardViewInflater())      // CardView 控件换肤初始化[可选]
                .loadSkin();
    }
}
