package com.lib.gadget.dialog;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @Description 等待加载对话框
 * Created by EthanCo on 2016/3/22.
 */
public class LoadingDialog {
    private static Reference<MaterialDialog> dialogRef;

    public static void show(Context context) {
        show(context, "正在加载", "请稍等...");
    }

    public static void show(Context context, String title, String content) {
        dismiss();
        MaterialDialog dialog = new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .progress(true, 0)
                .show();
        dialogRef = new WeakReference(dialog);
    }

    public static void show(Context context, int title, int content) {
        dismiss();
        MaterialDialog dialog = new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .progress(true, 0)
                .show();
        dialogRef = new WeakReference(dialog);
    }

    public static void dismiss() {
        if (dialogRef != null) {
            MaterialDialog dialog = dialogRef.get();
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }
}
