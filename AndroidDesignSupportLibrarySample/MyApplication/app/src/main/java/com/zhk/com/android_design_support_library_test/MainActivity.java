package com.zhk.com.android_design_support_library_test;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * 综合Demo
 * Created by YOLANDA on 2015/8/11
 */
public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fabBtn;
    private CoordinatorLayout rootLayout;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle mDrawerListener;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Button btnShowDrawerActivity;
    private Button btnShowTabActivity;
    private Button btnScrollFlagsTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initInstances();
    }

    private void initInstances() {
        rootLayout = (CoordinatorLayout) findViewById(R.id.rootLayout);
        fabBtn = (FloatingActionButton) findViewById(R.id.fabBtn); //FAB 按钮
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(rootLayout, R.string.hello_world, Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() { //Snackbar 相当于Toast

                    @Override
                    public void onClick(View v) {

                    }
                }).show();
            }
        });

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle(getString(R.string.app_name)); //使用CollapsingToolbarLayout会覆盖toolbar的title,所以要重新设置

        btnShowDrawerActivity = (Button) findViewById(R.id.btnDrawerLayout);
        btnShowDrawerActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DrawerLayoutActivity.class);
                startActivity(intent);
            }
        });

        btnShowTabActivity = (Button) findViewById(R.id.btnTabLayout);
        btnShowTabActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TabLayoutActivity.class);
                startActivity(intent);
            }
        });

        btnScrollFlagsTest = (Button) findViewById(R.id.btnScrollFlagsTest);
        btnScrollFlagsTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ScrollFlagsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //设置toolbar为actionbar


        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerListener = new ActionBarDrawerToggle(this, drawer, R.string.hello_world, R.string.hello_world);
        drawer.setDrawerListener(mDrawerListener); //设置侧滑菜单
        //使Actionbar的返回按钮生效
        ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeButtonEnabled(true);
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //将ActionBarToggle与DrawerLayout的状态同步
        //将ActionBarDrawerToggle中的drawer图标设置为ActionBar中的Home-Button的图标
        mDrawerListener.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerListener.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerListener.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
