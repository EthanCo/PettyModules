package com.ethanco.lib.bugly;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;


/**
 * @Description 消息显示Dialog
 * Created by EthanCo on 2016/6/21.
 */
public class UpdateDialog {
    public static void show(Context context, CharSequence title, CharSequence message,
                            String positiveText, DialogInterface.OnClickListener positiveListener,
                            String negativeText, DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder = generateBuilder(context);
        builder.setTitle(title)
                .setMessage(message);
        if (null != positiveListener) {
            builder.setPositiveButton(positiveText, positiveListener);
        }
        if (null != negativeListener) {
            builder.setNegativeButton(negativeText, negativeListener);
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @NonNull
    private static AlertDialog.Builder generateBuilder(Context context) {
        return new AlertDialog.Builder(context);
    }
}
