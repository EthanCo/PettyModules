package com.ethanco.lib.bugly;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;


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

    public static void show(Context context, CharSequence title, CharSequence message,
                            DialogInterface.OnClickListener positiveListener,
                            DialogInterface.OnClickListener negativeListener) {
        show(context, title, message,
                context.getString(R.string.update), positiveListener,
                context.getString(R.string.another_time), negativeListener);
    }

    public static void show(Context context, @StringRes int title, @StringRes int message,
                            @StringRes int positiveText, DialogInterface.OnClickListener positiveListener,
                            @StringRes int negativeText, DialogInterface.OnClickListener negativeListener) {
        show(context, context.getString(title), context.getString(message),
                context.getString(positiveText), positiveListener,
                context.getString(negativeText), negativeListener);
    }

    public static void show(Context context, @StringRes int title, @StringRes int message,
                            DialogInterface.OnClickListener positiveListener,
                            DialogInterface.OnClickListener negativeListener) {
        show(context, context.getString(title), context.getString(message),
                context.getString(R.string.update), positiveListener,
                context.getString(R.string.another_time), negativeListener);
    }
}
