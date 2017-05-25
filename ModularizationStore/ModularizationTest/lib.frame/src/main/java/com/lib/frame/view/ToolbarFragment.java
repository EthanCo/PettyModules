package com.lib.frame.view;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.lib.frame.view.toolbar.ToolbarManager;
import com.trello.rxlifecycle.components.support.RxFragment;

/**
 * Fragment Toolbar 基类
 * Created by EthanCo on 2016/12/6.
 */
@SuppressWarnings("unchecked")
public abstract class ToolbarFragment extends RxFragment {
    private ToolbarManager toolbarManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        toolbarManager = new ToolbarManager(getActivity());
    }

    /**
     * 初始化initToolbar (需保证布局中有<include layout="@layout/include_toolbar" />)
     *
     * @param title             标题
     * @param displayHomeEnable 是否有返回按钮
     * @param rootView          fragment rootView
     * @return
     */
    protected Toolbar initToolbar(View rootView, String title, boolean displayHomeEnable) {
        setHasOptionsMenu(true); //使 fragment的onOptionsItemSelected 启用
        return toolbarManager.initToolbar(rootView, title, displayHomeEnable);
    }

    /**
     * 初始化initToolbar (需保证布局中有<include layout="@layout/include_toolbar" />)
     *
     * @param title             标题
     * @param displayHomeEnable 是否有返回按钮
     * @param rootView          fragment rootView
     * @return
     */
    protected Toolbar initToolbar(View rootView, @StringRes int title, boolean displayHomeEnable) {
        return initToolbar(rootView, getString(title), displayHomeEnable);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getFragmentManager().popBackStack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
