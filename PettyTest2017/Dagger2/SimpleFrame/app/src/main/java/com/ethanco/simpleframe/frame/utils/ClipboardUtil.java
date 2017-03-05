package com.ethanco.simpleframe.frame.utils;

import android.content.ClipData;
import android.content.Context;
import android.os.Build;

/**
 * Description  剪贴板工具类
 * Created by chenqiao on 2015/9/22.
 */
public class ClipboardUtil {
    /**
     * 剪切板
     */
    private static android.content.ClipboardManager mClipboardManagerNew;
    /**
     * 剪切板
     */
    private static android.text.ClipboardManager mClipboardManagerOld;

    public static void copyTextToClipboard(Context context, CharSequence content) {
        if (Build.VERSION.SDK_INT > 10 && mClipboardManagerNew == null) {
            mClipboardManagerNew = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        } else if (mClipboardManagerOld == null) {
            mClipboardManagerOld = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        }
        if (Build.VERSION.SDK_INT > 10) {
            ClipData clipData = ClipData.newPlainText(content, content);
            mClipboardManagerNew.setPrimaryClip(clipData);
        } else {
            mClipboardManagerOld.setText(content);
        }
    }
}
