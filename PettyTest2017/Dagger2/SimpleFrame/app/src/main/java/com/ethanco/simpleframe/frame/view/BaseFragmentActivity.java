package com.ethanco.simpleframe.frame.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.text.TextUtils;

/**
 * 如果使用Fragment继承该类
 * Created by EthanCo on 2016/1/12.
 */
public abstract class BaseFragmentActivity extends BaseActivity {

    /**
     * 替换Fragment，不回退
     *
     * @param fragment
     * @param containerViewId
     */
    protected void replaceFragment(Fragment fragment, int containerViewId) {
        replaceFragment(fragment, containerViewId, null);
    }

    /**
     * 替换Fragment
     *
     * @param fragment        Fragment
     * @param containerViewId containerView id
     * @param isBackStack     是否回退
     */
    protected void replaceFragment(Fragment fragment, int containerViewId, String isBackStack) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transformation = fragmentManager.beginTransaction();
        transformation.replace(containerViewId, fragment);
        if (TextUtils.isEmpty(isBackStack)) {
            transformation.disallowAddToBackStack();
        } else {
            transformation.addToBackStack(isBackStack);
        }
        transformation.commit();
    }
}
