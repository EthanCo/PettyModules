package com.ethanco.alihotfixtest;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by wuer on 16/10/18.
 */
public class BaseBug {
    /**
     * patch测试, 假如BaseBug.test()方法有bug, 修复后的具体范例请参考BaseBug.md文件
     */
    public static void test(Context context) {
        Toast.makeText(context.getApplicationContext(), "PPP apk...", Toast.LENGTH_SHORT).show();
    }
}

