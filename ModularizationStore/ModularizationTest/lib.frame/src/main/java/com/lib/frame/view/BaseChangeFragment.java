package com.lib.frame.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.lib.frame.view.changer.FragmentChanger;
import com.lib.frame.view.changer.IFragmentChanger;
import com.lib.frame.view.changer.IFragmentSave;
import com.lib.frame.viewmodel.BaseViewModel;

/**
 * 使用切换Fragment基础类，对于嵌套fragment，提供便捷的方法
 */
public abstract class BaseChangeFragment<V, T extends BaseViewModel<V>> extends BaseFragment<V, T> implements IFragmentChanger {
    private IFragmentChanger fragmentChanger;
    private IFragmentSave fragmentSave;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        initFragmentChanger();
        super.onCreate(savedInstanceState);
        fragmentSave.restoreInstanceState(savedInstanceState);
    }

    private void initFragmentChanger() {
        FragmentChanger changerImpl = new FragmentChanger(getFragmentManager());
        fragmentChanger = changerImpl;
        fragmentSave = changerImpl;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
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
     * @param isBackStack     是否回退
     * @param useCache        是否使用缓存 true使用add/hide false使用replace
     */
    @Override
    public void changeFragment(Fragment fragment, int containerViewId, boolean isBackStack, boolean useCache) {
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
