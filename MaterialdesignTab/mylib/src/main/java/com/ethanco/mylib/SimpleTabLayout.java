package com.ethanco.mylib;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * @Description Simple TabLayout，基于Material Design TabLayout 进行的封装
 * Created by EthanCo on 2016/3/15.
 */
public class SimpleTabLayout extends FrameLayout {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public SimpleTabLayout(Context context) {
        super(context);
        initView(context);
    }

    public SimpleTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SimpleTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.layout_tab, this);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
    }

    /**
     * 设置Adapter
     *
     * @param adapter
     */
    public void setAdapter(PagerAdapter adapter) {
        viewPager.setAdapter(adapter); //给ViewPager设置适配器
        tabLayout.setupWithViewPager(viewPager); //将TabLayout和ViewPager关联起来
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }
}
