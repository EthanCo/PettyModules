package com.ethanco.mylib;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * @Description Base TabActivity
 * Created by EthanCo on 2016/3/15.
 */
public abstract class BaseTabActivity extends AppCompatActivity {
    protected ViewPager viewPager;
    protected TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDelegate().setContentView(R.layout.layout_tab);//setContentView(R.layout.activity_tab);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager.setAdapter(setPagerAdapter()); //给ViewPager设置适配器
        tabLayout.setupWithViewPager(viewPager); //将TabLayout和ViewPager关联起来
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    /**
     * 设置PagerAdapter
     *
     * @return
     */
    protected abstract PagerAdapter setPagerAdapter();

    @Override
    public void setContentView(int layoutResID) {
        //super.setContentView(layoutResID);
        throw new IllegalStateException("在BaseTabActivity中已经设置了layout,请勿重新设置");
    }
}
