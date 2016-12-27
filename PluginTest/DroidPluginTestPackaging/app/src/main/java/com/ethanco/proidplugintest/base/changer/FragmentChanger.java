package com.ethanco.proidplugintest.base.changer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import java.util.ArrayList;

/**
 * 改变Fragment
 *
 * @author EthanCo
 * @since 2016/11/16
 */

public class FragmentChanger implements IFragmentChanger, IFragmentSave {
    public static final String KEY_FRAGMENT_TAG_LIST = "fragment_tag_list";

    private String currFragmentTag;
    private ArrayList<String> tagList;
    private FragmentManager fragmentManager;

    public FragmentChanger(FragmentManager fragmentManager) {
        this.tagList = new ArrayList<>();
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void restoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            resetTagList(savedInstanceState);
            resetFragmentUI();
        }
    }

    @Override
    public void saveInstanceState(Bundle outState) {
        outState.putStringArrayList(KEY_FRAGMENT_TAG_LIST, tagList);
    }

    /**
     * 改变Fragment，不回退,缓存Fragment
     *
     * @param fragment        Fragment
     * @param containerViewId containerView id
     */
    @Override
    public void changeFragment(Fragment fragment, int containerViewId) {
        changeFragment(fragment, getFragmentTag(fragment), containerViewId, null, true);
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
        changeFragment(fragment, getFragmentTag(fragment), containerViewId, isBackStack, useCache);
    }

    /**
     * 替换Fragment
     *
     * @param fragment
     * @param containerViewId
     */
    @Override
    public void replaceFragment(Fragment fragment, int containerViewId) {
        changeFragment(fragment, "", containerViewId, null, false);
    }

    @NonNull
    private String getFragmentTag(Fragment fragment) {
        return fragment.getClass().getName();
    }

    //============================= Z-细节方法 ==============================/

    private void resetFragmentUI() {
        FragmentTransaction transformation = fragmentManager.beginTransaction();
        for (String tag : tagList) {
            Fragment fragment = fragmentManager.findFragmentByTag(tag);
            if (fragment == null) {
                continue;
            }

            if (tag.equals(currFragmentTag)) {
                transformation.show(fragment);
            } else {
                transformation.hide(fragment);
            }
        }
        transformation.commit();
    }

    private void resetTagList(Bundle savedInstanceState) {
        ArrayList<String> saveList = savedInstanceState.getStringArrayList(KEY_FRAGMENT_TAG_LIST);
        if (saveList != null && saveList.size() > 0) {
            this.tagList = saveList;
        }
    }

    /**
     * 改变Fragment
     *
     * @param fragment        Fragment
     * @param containerViewId containerView id
     * @param isBackStack     是否回退 如果为nul或空字符串，则不会退，否则会回退
     * @param useCache        是否使用缓存 hide-add
     */
    protected void changeFragment(Fragment fragment, String fragmentTag, int containerViewId, String isBackStack, boolean useCache) {
        FragmentTransaction transformation = fragmentManager.beginTransaction();
        if (useCache) {
            changeFragmentByCache(fragment, fragmentTag, containerViewId, fragmentManager, transformation);
        } else {
            replaceFragment(fragment, containerViewId, transformation);
        }
        if (TextUtils.isEmpty(isBackStack)) {
            transformation.disallowAddToBackStack();
        } else {
            transformation.addToBackStack(isBackStack);
        }
        transformation.commit();
    }

    private void changeFragmentByCache(Fragment fragment, String fragmentTag, int containerViewId, FragmentManager fragmentManager, FragmentTransaction transformation) {
        if (!TextUtils.isEmpty(currFragmentTag)) {
            Fragment currFragment = fragmentManager.findFragmentByTag(currFragmentTag);
            if (null != currFragment) {
                transformation.hide(currFragment);
            }
        }
        Fragment newFragment = fragmentManager.findFragmentByTag(fragmentTag);
        if (null == newFragment) {
            transformation.add(containerViewId, fragment, fragmentTag);
        } else {
            if (newFragment.isAdded()) {
                transformation.show(newFragment);
            } else {
                transformation.add(containerViewId, fragment, fragmentTag);
            }
        }
        currFragmentTag = fragmentTag;
        if (!tagList.contains(fragmentTag)) {
            tagList.add(fragmentTag);
        }
    }

    private void replaceFragment(Fragment fragment, int containerViewId, FragmentTransaction transformation) {
        transformation.replace(containerViewId, fragment);
    }
}
