package com.ethanco.mymaterialdialogtest._native;

import android.app.ProgressDialog;
import android.content.Context;

import com.ethanco.mymaterialdialogtest.R;


/**
 * @Description 等待加载对话框
 * Created by EthanCo on 2016/3/22.
 */
public class LoadingDialog {
    private static ProgressDialog mDialog;

    public static void show(Context context) {
        show(context, R.string.dialog_loading, R.string.dialog_please_wait);
    }

    public static void show(Context context, String title, String message) {
        dismiss();
        mDialog = ProgressDialog.show(context, title, message, true, true);
    }

    public static void show(Context context, int title, int message) {
        show(context, context.getString(title), context.getString(message));
    }

    public static void dismiss() {
        if (isShow()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    public static boolean isShow() {
        return mDialog != null && mDialog.isShowing();
    }
}
