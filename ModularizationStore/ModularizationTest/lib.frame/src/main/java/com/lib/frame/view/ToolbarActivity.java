package com.lib.frame.view;

import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.lib.frame.view.toolbar.ToolbarManager;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;


/**
 * @Description toolbar Activity - 沉侵式
 * Created by EthanCo on 2016/4/14.
 */
public abstract class ToolbarActivity extends RxAppCompatActivity {

    //Activity的Toolbar是否已启用
    private boolean activityToolbarEnable = false;
    private ToolbarManager toolbarManager = new ToolbarManager(this);



    /**
     * 初始化initToolbar (需保证布局中有<include layout="@layout/include_toolbar" />)
     *
     * @param title             标题
     * @param displayHomeEnable 是否有返回按钮
     * @return
     */
    protected Toolbar initToolbar(String title, boolean displayHomeEnable) {
        activityToolbarEnable = displayHomeEnable;
        return toolbarManager.initToolbar(getWindow().getDecorView(), title, displayHomeEnable);
    }

    /**
     * 初始化initToolbar (需保证布局中有<include layout="@layout/include_toolbar" />)
     *
     * @param title             标题
     * @param displayHomeEnable 是否有返回按钮
     * @return
     */
    protected Toolbar initToolbar(@StringRes int title, boolean displayHomeEnable) {
        return initToolbar(getString(title), displayHomeEnable);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (activityToolbarEnable) {
                finish();
            }
            return activityToolbarEnable;
        }
        return super.onOptionsItemSelected(item);
    }
}
