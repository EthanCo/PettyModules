package com.ethanco.proidplugintest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.ethanco.proidplugintest.base.changer.FragmentChanger;
import com.ethanco.proidplugintest.base.changer.IFragmentChanger;
import com.ethanco.proidplugintest.base.changer.IFragmentSave;

/**
 * @Description 使用Fragment的Activity基础该类
 * Created by EthanCo on 2016/6/14.
 */
public abstract class BaseFragmentActivity extends AppCompatActivity implements IFragmentChanger {
    private IFragmentChanger fragmentChanger;
    private IFragmentSave fragmentSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initFragmentChanger();
        super.onCreate(savedInstanceState);
    }

    private void initFragmentChanger() {
        FragmentChanger changerImpl = new FragmentChanger(getSupportFragmentManager());
        fragmentChanger = changerImpl;
        fragmentSave = changerImpl;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        fragmentSave.restoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        fragmentSave.saveInstanceState(outState);
    }

    /**
     * 改变Fragment，不回退,缓存Fragment
     *
     * @param fragment        Fragment
     * @param containerViewId containerView id
     */
    @Override
    public void changeFragment(Fragment fragment, int containerViewId) {
        fragmentChanger.changeFragment(fragment, containerViewId);
    }

    /**
     * 改变Fragment
     *
     * @param fragment        Fragment
     * @param containerViewId containerView id
     * @param isBackStack     是否回退 如果为nul或空字符串，则不会退，否则会回退
     * @param useCache        是否使用缓存
     */
    @Override
    public void changeFragment(Fragment fragment, int containerViewId, String isBackStack, boolean useCache) {
        fragmentChanger.changeFragment(fragment, containerViewId, isBackStack, useCache);
    }

    /**
     * 替换Fragment
     *
     * @param fragment
     * @param containerViewId
     */
    @Override
    public void replaceFragment(Fragment fragment, int containerViewId) {
        fragmentChanger.replaceFragment(fragment, containerViewId);
    }
}
