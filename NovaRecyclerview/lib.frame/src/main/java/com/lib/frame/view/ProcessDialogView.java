package com.lib.frame.view;

/**
 * @Description 等待对话框View 当View需要等待dialog时，实现这个接口
 * Created by EthanCo on 2016/6/13.
 */
public interface ProcessDialogView {
    void showProgressDialog();

    void dismissProgressDialog();
}
