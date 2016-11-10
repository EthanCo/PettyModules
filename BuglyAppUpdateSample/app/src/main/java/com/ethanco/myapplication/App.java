package com.ethanco.myapplication;

import android.app.Application;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

/**
 * Created by EthanCo on 2016/11/9.
 */

public class App extends Application {
    public static final String LINE_BREAK = "\n";

    @Override
    public void onCreate() {
        super.onCreate();


        /*在application中初始化时设置监听，监听策略的收取*/
//        Beta.upgradeListener = new UpgradeListener() {
//            @Override
//            public void onUpgrade(int ret, UpgradeInfo strategy, boolean isManual, boolean isSilence) {
//                if (strategy != null) {
//                    Intent i = new Intent();
//                    i.setClass(getApplicationContext(), UpdateActivity.class);
//                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(i);
//                } else {
//                    Toast.makeText(App.this, "已是最新版本", Toast.LENGTH_LONG).show();
//                }
//            }
//        };
//
//
//        /* 设置更新状态回调接口 */
//        Beta.upgradeStateListener = new UpgradeStateListener() {
//            @Override
//            public void onUpgradeSuccess(boolean isManual) {
//                Toast.makeText(getApplicationContext(), "更新成功", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onUpgradeFailed(boolean isManual) {
//                Toast.makeText(getApplicationContext(), "更新失败", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onUpgrading(boolean isManual) {
//                Toast.makeText(getApplicationContext(), "UPGRADE_CHECKING", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onUpgradeNoVersion(boolean isManual) {
//                Toast.makeText(getApplicationContext(), "已是最新版本", Toast.LENGTH_SHORT).show();
//            }
//        };

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
         * 第三个参数为SDK调试模式开关，调试模式的行为特性如下：
         输出详细的Bugly SDK的Log；每一条Crash都会被立即上报；自定义日志将会在Logcat中输出。
         建议在测试阶段建议设置成true，发布时设置为false。
         */
        Bugly.init(getApplicationContext(), "7f525d2734", false);

//        /**
//         * 设置自定义tip弹窗UI布局
//         * 注意：因为要保持接口统一，需要用户在指定控件按照以下方式设置tag，否则会影响您的正常使用：
//         *  标题：beta_title，如：android:tag="beta_title"
//         *  提示信息：beta_tip_message 如： android:tag="beta_tip_message"
//         *  取消按钮：beta_cancel_button 如：android:tag="beta_cancel_button"
//         *  确定按钮：beta_confirm_button 如：android:tag="beta_confirm_button"
//         *  详见layout/tips_dialog.xml
//         */
//        Beta.tipsDialogLayoutId = R.layout.tips_dialog;

    }
}
