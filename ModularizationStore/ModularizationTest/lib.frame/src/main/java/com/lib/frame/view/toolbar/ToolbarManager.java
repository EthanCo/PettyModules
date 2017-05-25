package com.lib.frame.view.toolbar;

import android.app.Activity;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

/**
 * Toolbar 管理者
 *
 * @author EthanCo
 * @since 2016/12/6
 */

public class ToolbarManager {
    private Activity context;

    public ToolbarManager(Activity context) {
        this.context = context;
    }

    /**
     * 初始化initToolbar (需保证布局中有<include layout="@layout/include_toolbar" />)
     *
     * @param title             标题
     * @param displayHomeEnable 是否有返回按钮
     * @param rootView          fragment rootView
     * @return
     */
    public Toolbar initToolbar(View rootView, String title, boolean displayHomeEnable) {
        Toolbar toolbar = (Toolbar) rootView.findViewById(com.lib.frame.R.id.toolbar);
        if (toolbar != null) {
            TextView tvTitle = (TextView) toolbar.findViewById(com.lib.frame.R.id.title);
            tvTitle.setText(title);

            //设置toolbar高度和内边距
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                toolbar.getLayoutParams().height = getAppBarHeight();
                toolbar.setPadding(toolbar.getPaddingLeft(),
                        getStatusBarHeight(),
                        toolbar.getPaddingRight(),
                        toolbar.getPaddingBottom());
            }


            ((AppCompatActivity) context).setSupportActionBar(toolbar);


            ActionBar actionbar = ((AppCompatActivity) context).getSupportActionBar();
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
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    private int dip2px(float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
