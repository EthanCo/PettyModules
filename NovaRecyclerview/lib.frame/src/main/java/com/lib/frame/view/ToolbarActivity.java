package com.lib.frame.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.lib.frame.R;


/**
 * @Description toolbar Activity - 沉侵式
 * Created by EthanCo on 2016/4/14.
 */
public abstract class ToolbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化initToolbar (需保证布局中有<include layout="@layout/include_toolbar" />)
     *
     * @param title             标题
     * @param displayHomeEnable 是否有返回按钮
     * @return
     */
    protected Toolbar initToolbar(String title, boolean displayHomeEnable) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            TextView tvTitle = (TextView) toolbar.findViewById(R.id.title);
            tvTitle.setText(title);

            //设置toolbar高度和内边距
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                toolbar.getLayoutParams().height = getAppBarHeight();
                toolbar.setPadding(toolbar.getPaddingLeft(),
                        getStatusBarHeight(),
                        toolbar.getPaddingRight(),
                        toolbar.getPaddingBottom());
            }

            setSupportActionBar(toolbar);


            ActionBar actionbar = getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(displayHomeEnable);
            actionbar.setDisplayShowHomeEnabled(displayHomeEnable);
            actionbar.setDisplayShowTitleEnabled(true);
        }
        return toolbar;
    }

    private int getAppBarHeight() {
        return dip2px(40) + getStatusBarHeight();
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    private int dip2px(float dipValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
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
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
