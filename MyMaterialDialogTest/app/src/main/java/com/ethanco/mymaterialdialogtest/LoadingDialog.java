package com.ethanco.mymaterialdialogtest;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * @Description 等待加载对话框
 * Created by EthanCo on 2016/3/22.
 */
public class LoadingDialog {
    private static MaterialDialog mDialog;

    public static void show(Context context) {
        dismiss();
        mDialog = new MaterialDialog.Builder(context)
                .title("正在加载")
                .content("请稍等...")
                .progress(true, 0)
                .show();
    }

    public static void dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
