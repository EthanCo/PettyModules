package com.ethanco.mymaterialdialogtest.thirdparty;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * @Description 等待加载对话框
 * Created by EthanCo on 2016/3/22.
 */
public class LoadingDialog {
    private static MaterialDialog mDialog;

    public static void show(Context context) {
        show(context, "正在加载", "请稍等...");
    }

    public static void show(Context context, String title, String content) {
        dismiss();
        mDialog = new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .progress(true, 0)
                .show();
    }

    public static void show(Context context, int title, int content) {
        dismiss();
        mDialog = new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .progress(true, 0)
                .show();
    }

    public static void dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
