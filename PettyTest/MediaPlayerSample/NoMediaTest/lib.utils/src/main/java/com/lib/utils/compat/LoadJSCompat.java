package com.lib.utils.compat;

import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebView;

/**
 * Description  调用Js方式的兼容
 * Created by chenqiao on 2015/7/4.
 */
public class LoadJSCompat {

    /**
     * @param mWebview  需要进行加载JS的webview
     * @param jsUrl     Js的表达式
     * @param calllback Js返回值回调,不需回调或无返回值，传null
     */
    public static void loadJs(WebView mWebview, String jsUrl, ValueCallback<String> calllback) {
        if (Build.VERSION.SDK_INT >= 19) {
            mWebview.evaluateJavascript(jsUrl, calllback);
        } else {
            mWebview.loadUrl(jsUrl);
        }
    }
}
