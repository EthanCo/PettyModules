package com.ethanco.materialbottomnavigationtest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;


/**
 * 教程链接 http://www.jb51.net/article/81403.htm
 */
public class MainActivity extends AppCompatActivity {

    private BottomNavigationBar bottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Home"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Books"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Music"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Movies & TV"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Games"))
                .initialise();

        //设置事件监听器TabChangeListener
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                replaceFragment(FragmentFactory.createFactory(position), R.id.layout_container, "");
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
            }
        });

        bottomNavigationBar.selectTab(0);
        replaceFragment(FragmentFactory.createFactory(0), R.id.layout_container, "");
    }

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
