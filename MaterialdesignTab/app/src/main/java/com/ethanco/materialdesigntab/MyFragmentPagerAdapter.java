package com.ethanco.materialdesigntab;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.ethanco.mylib.BaseFragmentPagerAdapter;

/**
 * @Description MyFragmentPagerAdapter
 * Created by EthanCo on 2016/3/15.
 */
public class MyFragmentPagerAdapter extends BaseFragmentPagerAdapter {
    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm, context);
    }

    @Override
    public Fragment getItem(int position) {
        Log.i("Z-SimpleAdapter", "getItem position: " + position); //Adapter会预加载，所以刚进入的时候会调用两次，position=0和position=1
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public String[] getTabTitles() {
        return new String[]{"tab1", "tab2", "tab3"};
    }
}
