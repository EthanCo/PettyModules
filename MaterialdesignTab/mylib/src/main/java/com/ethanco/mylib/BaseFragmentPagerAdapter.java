package com.ethanco.mylib;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @Description Base FragmentPagerAdapter
 * Created by EthanCo on 2016/3/15.
 */
public abstract class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context context;

    public BaseFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public abstract Fragment getItem(int position);

    @Override
    public int getCount() {
        return getTabTitles().length;
    }

    public abstract String[] getTabTitles();

    @Override
    public CharSequence getPageTitle(int position) {
        return getTabTitles()[position];
    }
}
