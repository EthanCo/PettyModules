package com.ethanco.lib.bugly;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.download.DownloadTask;

/**
 * @Description 更新Activity (透明)
 * Created by EthanCo on 2016/11/9.
 */
@Deprecated
public class UpdateActivity extends Activity {

    public static final String LINE_BREAK = "\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        /*获取下载任务，初始化界面信息*/
        String okMessage = updateBtn(Beta.getStrategyTask());
        String cancelMessage = "下次再说";

        String title = Beta.getUpgradeInfo().title;
        String version = Beta.getUpgradeInfo().versionName;
        String size = String.valueOf(Beta.getUpgradeInfo().fileSize);
        String time = String.valueOf(Beta.getUpgradeInfo().publishTime);
        String explain = Beta.getUpgradeInfo().newFeature;
        //Beta.getStrategyTask().getSavedLength(); //已保存的大小

        String message = "版本:" + version + LINE_BREAK +
                "包大小:" + size + LINE_BREAK +
                "更新说明:" + LINE_BREAK + explain + LINE_BREAK;

        UpdateDialog.show(this, title, message, okMessage, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DownloadTask task = Beta.startDownload();
                updateBtn(task);
                if (task.getStatus() == DownloadTask.DOWNLOADING) {
                    UpdateActivity.this.finish();
                }
            }
        }, cancelMessage, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Beta.cancelDownload();
                UpdateActivity.this.finish();
            }
        });

       /*     *//*注册下载监听，监听下载事件*//*
        Beta.registerDownloadListener(new DownloadListener() {
            @Override
            public void onReceive(DownloadTask task) {
                updateBtn(task);
                tv.setText(task.getSavedLength() + "");
            }

            @Override
            public void onCompleted(DownloadTask task) {
                updateBtn(task);
                tv.setText(task.getSavedLength() + "");
            }

            @Override
            public void onFailed(DownloadTask task, int code, String extMsg) {
                updateBtn(task);
                tv.setText("failed");

            }
        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

            /*注销下载监听*/
        //Beta.unregisterDownloadListener();
    }


    public String updateBtn(DownloadTask task) {

            /*根据下载任务状态设置按钮*/
        switch (task.getStatus()) {
            case DownloadTask.INIT:
            case DownloadTask.DELETED:
            case DownloadTask.FAILED: {
                return "开始下载";
            }
            case DownloadTask.COMPLETE: {
                return "安装";
            }
            case DownloadTask.DOWNLOADING: {
                return "暂停";
            }
            case DownloadTask.PAUSED: {
                return "继续下载";
            }
            default:
                return "开始下载";
        }
    }

    public <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }
}
