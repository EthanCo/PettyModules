package com.zhk.com.android_design_support_library_test;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * TabLayout Demo
 * Created by YOLANDA on 2015/8/12
 */
public class TabLayoutActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private ViewPager viewPager;

    private int[] mStr = new int[]{R.string.str1, R.string.str2, R.string.str3};
    private List<TextView> mTxtList = new ArrayList<TextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        initView();
        initEvent();
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        /* 这个是纯粹加tab,没有手势效果
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        */

        viewPager = (ViewPager) findViewById(R.id.vp);
        //viewPager.setPageTransformer(true, new DepthPageTransformer());//为ViewPager添加动画效果,3.0及以上支持
        viewPager.setAdapter(new PagerAdapter() {

            //创建
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                TextView tv = new TextView(TabLayoutActivity.this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER; //此处相当于布局文件中的Android:layout_gravity属性
                tv.setLayoutParams(params);
                tv.setGravity(Gravity.CENTER); //此处相当于布局文件中的Android：gravity属性
                tv.setText(mStr[position]);
                tv.setTextSize(25);
                tv.setTextColor(0xFF000000);
                container.addView(tv);
                mTxtList.add(tv);
                return tv;
            }

            //销毁
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mTxtList.get(position));
            }

            /*ViewPager里面用了一个mItems(ArrayList)来存储每个page的信息(ItemInfo)，当界面要展示或者发生变化时，
             * 需要依据page的当前信息来调整，但此时只能通过view来查找，所以只能遍历mItems通过比较view和object来找到对应的ItemInfo。*/
            @Override
            public boolean isViewFromObject(View view, Object obj) {
                return view == obj;
            }

            //有多少页
            @Override
            public int getCount() {
                return mStr.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return getString(mStr[position]);
            }
        });
        tabLayout.setupWithViewPager(viewPager);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.hello_world, R.string.hello_world);
        drawer.setDrawerListener(mDrawerToggle);
    }

    private void initEvent() {
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
