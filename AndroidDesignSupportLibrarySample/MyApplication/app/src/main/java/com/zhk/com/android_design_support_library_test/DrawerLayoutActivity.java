package com.zhk.com.android_design_support_library_test;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * 侧滑菜单Demo
 */
public class DrawerLayoutActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    MyDrawerListener drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drader);

        initInstances();
    }

    private void initInstances() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new MyDrawerListener(this, drawerLayout, R.string.hello_world, R.string.hello_world);
        drawerLayout.setDrawerListener(drawerToggle);


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public class MyDrawerListener extends ActionBarDrawerToggle {

        public MyDrawerListener(Activity activity, DrawerLayout drawerLayout, int openDrawerContentDescRes, int closeDrawerContentDescRes) {
            super(activity, drawerLayout, openDrawerContentDescRes, closeDrawerContentDescRes);
        }

        @Override //当侧滑菜单打开
        public void onDrawerOpened(View drawerView) {
            getSupportActionBar().setTitle(R.string.please_choice);
            super.onDrawerOpened(drawerView);
        }

        @Override //当侧滑菜单关闭
        public void onDrawerClosed(View drawerView) {
            getSupportActionBar().setTitle(R.string.app_name);
            super.onDrawerClosed(drawerView);
        }

        @Override //当侧滑菜单滑动
        public void onDrawerSlide(View drawerView, float slideOffset) {
            super.onDrawerSlide(drawerView, slideOffset);
            //Toast.makeText(getApplicationContext(),"onDrawerSlide",Toast.LENGTH_SHORT).show();
        }

        @Override //当侧滑菜单状态改变
        public void onDrawerStateChanged(int newState) {
            super.onDrawerStateChanged(newState);
            //Toast.makeText(getApplicationContext(), "onDrawerStateChanged", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    //当Activity彻底运行起来之后回调onPostCreate方法。onCreate onStart onPostCreate onResume onPostResume
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //将ActionBarToggle与DrawerLayout的状态同步
        //将ActionBarDrawerToggle中的drawer图标设置为ActionBar中的Home-Button的图标
        drawerToggle.syncState();//该方法会自动和actionBar关联, 将开关的图片显示在了action上，如果不设置，也可以有抽屉的效果，不过是默认的图标
    }

    @Override //当配置改变 比如转屏等
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) //先执行drawerTaggle的onOptionsItemSelected (toolbar的返回按钮也在其中)
            return true;

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
