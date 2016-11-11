package com.ethanco.mymaterialdialogtest._native;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.ethanco.mymaterialdialogtest.R;

import java.util.List;


/**
 * @Description 消息显示Dialog
 * Created by EthanCo on 2016/6/21.
 */
public class MessageDialog {

    /**
     * 普通消息对话框
     *
     */
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
                context.getString(R.string.sure), positiveListener,
                context.getString(R.string.cancle), negativeListener);
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
                context.getString(R.string.sure), positiveListener,
                context.getString(R.string.cancle), negativeListener);
    }

    /**
     * 显示 列表 Dialog
     *
     * @param context
     * @param title
     * @param items
     * @param listener
     */
    public static void showList(Context context, String title, CharSequence[] items, final DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = generateBuilder(context);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        builder.setItems(items, listener);
        builder.create().show();
    }

    /**
     * 显示 列表 Dialog
     *
     * @param context
     * @param items
     * @param listener
     */
    public static void showList(Context context, CharSequence[] items, final DialogInterface.OnClickListener listener) {
        showList(context, null, items, listener);
    }

    /**
     * 显示 列表 Dialog
     *
     * @param context
     * @param items
     * @param listener
     */
    public static void showList(Context context, List items, final DialogInterface.OnClickListener listener) {
        showList(context, null, items, listener);
    }

    /**
     * 显示 列表 Dialog
     *
     * @param context
     * @param title
     * @param items
     * @param listener
     */
    public static void showList(Context context, String title, List items, final DialogInterface.OnClickListener listener) {
        String[] arr = (String[]) items.toArray(new String[items.size()]);
        showList(context, title, arr, listener);
    }

    /**
     * 显示有EditText的Dialog
     *
     * @param context
     * @param title
     * @param positiveListener
     * @param negativeListener
     */
    public static void showInput(Context context, String title, final OnPositiveButtonListener positiveListener, DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater factory = LayoutInflater.from(context);
        final View textEntryView = factory.inflate(R.layout.dialog_input, null);
        final EditText etInput = (EditText) textEntryView.findViewById(R.id.et_dialog_input);
        builder.setTitle(title);
        builder.setView(textEntryView);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = etInput.getText().toString();
                if (null != positiveListener) {
                    positiveListener.onPositiveClick(dialog, which, text);
                }
            }
        });

        if (null != negativeListener) {
            builder.setNegativeButton("取消", negativeListener);
        }
        builder.create().show();
    }

    /**
     * 显示有EditText的Dialog
     *
     * @param context
     * @param title
     * @param positiveListener
     */
    public static void showInput(Context context, String title, OnPositiveButtonListener positiveListener) {
        showInput(context, title, positiveListener, null);
    }

    /**
     * 显示自定义的Dialog
     *
     * @param context
     * @param layoutRes        layoutId
     * @param title            标题，传null为不设置
     * @param positiveListener 确定 监听
     * @param negativeListener 取消 监听
     * @return
     */
    public static View showCustome(Context context, @LayoutRes int layoutRes, String title,
                                   DialogInterface.OnClickListener positiveListener,
                                   DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater factory = LayoutInflater.from(context);
        final View entity = factory.inflate(layoutRes, null);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        builder.setView(entity);
        if (null != negativeListener) {
            builder.setPositiveButton("确定", positiveListener);
        }
        if (null != negativeListener) {
            builder.setNegativeButton("取消", negativeListener);
        }
        builder.create().show();
        return entity;
    }

    /**
     * 显示自定义的Dialog
     *
     * @param context
     * @param layoutView       layoutView
     * @param title            标题，传null为不设置
     * @param positiveListener 确定 监听
     * @param negativeListener 取消 监听
     * @return
     */
    public static AlertDialog createCustome(Context context, View layoutView, String title,
                                            DialogInterface.OnClickListener positiveListener,
                                            DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        builder.setView(layoutView);
        if (null != negativeListener) {
            builder.setPositiveButton("确定", positiveListener);
        }
        if (null != negativeListener) {
            builder.setNegativeButton("取消", negativeListener);
        }
        AlertDialog dialog = builder.create();
        //dialog.show();
        return dialog;
    }
}
