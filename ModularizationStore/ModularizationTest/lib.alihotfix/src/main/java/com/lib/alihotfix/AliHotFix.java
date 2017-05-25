package com.lib.alihotfix;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.taobao.hotfix.HotFixManager;
import com.taobao.hotfix.PatchLoadStatusListener;
import com.taobao.hotfix.util.PatchStatusCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 阿里百川HotFix 门面
 *
 * @author EthanCo
 * @since 2016/12/21
 */

public class AliHotFix {
    public static final String TAG = "Z-AliHotFix";
    public static final int REQUEST_EXTERNAL_STORAGE_PERMISSION = 123111;
    public static String appVersion;
    public static String appId;
    public static List<PatchLoadStatusListener> patchLoadStatusListeners = new ArrayList<>();

    public static void init(Application application, String appId) {
        initApp(application, appId);
        initHotFix(application);
    }

    //检查是否有新Patch
    public static void queryNewHotPatch() {
        HotFixManager.getInstance().queryNewHotPatch();
    }

    //清空本地补丁
    public static void cleanPatches() {
        HotFixManager.getInstance().cleanPatches(true);
    }

    /**
     * 如果本地补丁放在了外部存储卡中, 6.0以上需要申请读外部存储卡权限才能够使用. 应用内部存储则不受影响
     * 回调结果在Activity的onRequestPermissionsResult中调用AliHotFix.onRequestPermissionsResult()接口
     */
    public static void requestExternalStoragePermission(Activity context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_EXTERNAL_STORAGE_PERMISSION);
            }
        }
    }

    public static void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults, Context context) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE_PERMISSION:
                if (grantResults.length <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, "local external storage patch is invalid as not read external storage permission", Toast.LENGTH_SHORT).show();
                    //updateConsole("local external storage patch is invalid as not read external storage permission");
                }
                break;
            default:
        }
    }

    private static void initApp(Application application, String _appId) {
        appId = _appId;
        try {
            appVersion = application.getPackageManager().getPackageInfo(application.getPackageName(), 0).versionName;
        } catch (Exception e) {
            appVersion = "1.0.0";
        }
    }

    private static void initHotFix(Application application) {
        HotFixManager.getInstance().setContext(application)
                .setAppVersion(appVersion)
                .setAppId(appId)
                .setAesKey(null)
                .setSupportHotpatch(true)
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onload(final int mode, final int code, final String info, final int handlePatchVersion) {
                        HandlePatchLoad(mode, code, info);
                        notifyPatchLoadListener(mode, code, info, handlePatchVersion);
                    }
                }).initialize();
    }

    private static void notifyPatchLoadListener(final int mode, final int code, final String info, final int handlePatchVersion) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                for (PatchLoadStatusListener patchLoadStatusListener : patchLoadStatusListeners) {
                    patchLoadStatusListener.onload(mode, code, info, handlePatchVersion);
                }
            }
        });
    }

    private static void HandlePatchLoad(int mode, int code, String info) {
        // 补丁加载回调通知
        if (code == PatchStatusCode.CODE_SUCCESS_LOAD) {
            Log.i(TAG, "initHotfix 表明补丁加载成功");
            //表明补丁加载成功
        } else if (code == PatchStatusCode.CODE_ERROR_NEEDRESTART) {
            Log.i(TAG, "initHotfix 表明新补丁生效需要重启");
            //表明新补丁生效需要重启. 业务方可自行实现逻辑, 提示用户或者强制重启, 建议: 用户可以监听进入后台事件, 然后应用自杀
        } else if (code == PatchStatusCode.CODE_ERROR_INNERENGINEFAIL) {
            Log.i(TAG, "initHotfix 内部引擎加载异常, 推荐此时清空本地补丁, 但是不清空本地版本号, 防止失败补丁重复加载");
            //内部引擎加载异常, 推荐此时清空本地补丁, 但是不清空本地版本号, 防止失败补丁重复加载
            HotFixManager.getInstance().cleanPatches(false);
        } else {
            Log.i(TAG, "initHotfix 其它信息, 查看PatchStatusCode类说明 mode:" + mode + " code:" + code + "info:" + info);
            //其它信息, 查看PatchStatusCode类说明
        }
    }

    public static void addPatchLoadStatusListener(PatchLoadStatusListener listener) {
        if (!patchLoadStatusListeners.contains(listener)) {
            patchLoadStatusListeners.add(listener);
        }
    }

    public static void removePatchLoadStatusListener(PatchLoadStatusListener listener) {
        if (patchLoadStatusListeners.contains(listener)) {
            patchLoadStatusListeners.remove(listener);
        }
    }
}
