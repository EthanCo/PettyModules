package com.ethanco.lib.bugly;

import android.content.Context;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

/**
 * @Description Bugly包装类
 * Created by EthanCo on 2016/11/10.
 */

public class BuglyWrap {
    /**
     * 进行Bugly初始化
     *
     * @param context
     * @param apiID   apiID
     * @param isDebug SDK调试模式开关，调试模式的行为特性如下：
     *                输出详细的Bugly SDK的Log；
     *                每一条Crash都会被立即上报；
     *                自定义日志将会在Logcat中输出。
     *                建议在测试阶段建议设置成true，发布时设置为false。
     * @param icon    状态栏、通知栏的图标
     */
    public static void init(final Context context, String apiID, boolean isDebug, int icon) {
        Beta.largeIconId = icon;
        Beta.smallIconId = icon;
        
        Bugly.init(context, apiID, isDebug);
    }

    /**
     * 进行Bugly初始化，使用自定义的Dialog UI
     *
     * @param context
     * @param apiID   apiID
     * @param isDebug SDK调试模式开关，调试模式的行为特性如下：
     *                输出详细的Bugly SDK的Log；
     *                每一条Crash都会被立即上报；
     *                自定义日志将会在Logcat中输出。
     *                建议在测试阶段建议设置成true，发布时设置为false。
     * @param icon    状态栏、通知栏的图标
     */
    public static void initAndCustomDialog(Context context, String apiID, boolean isDebug, int icon) {
        /**
         *  设置自定义升级对话框UI布局
         *  注意：因为要保持接口统一，需要用户在指定控件按照以下方式设置tag，否则会影响您的正常使用：
         *  标题：beta_title，如：android:tag="beta_title"
         *  升级信息：beta_upgrade_info  如： android:tag="beta_upgrade_info"
         *  更新属性：beta_upgrade_feature 如： android:tag="beta_upgrade_feature"
         *  取消按钮：beta_cancel_button 如：android:tag="beta_cancel_button"
         *  确定按钮：beta_confirm_button 如：android:tag="beta_confirm_button"
         *  详见layout/upgrade_dialog.xml
         */
        Beta.upgradeDialogLayoutId = R.layout.custom_update_dialog;

        /**
         * 设置自定义tip弹窗UI布局
         * 注意：因为要保持接口统一，需要用户在指定控件按照以下方式设置tag，否则会影响您的正常使用：
         *  标题：beta_title，如：android:tag="beta_title"
         *  提示信息：beta_tip_message 如： android:tag="beta_tip_message"
         *  取消按钮：beta_cancel_button 如：android:tag="beta_cancel_button"
         *  确定按钮：beta_confirm_button 如：android:tag="beta_confirm_button"
         *  详见layout/tips_dialog.xml
         */
        Beta.tipsDialogLayoutId = R.layout.custom_tip_dialog;
        init(context, apiID, isDebug, icon);
    }
}
