package com.morgoo.facade;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.morgoo.droidplugin.pm.PluginManager;
import com.morgoo.facade.callback.FoundPluginCallback;
import com.morgoo.facade.callback.InstallCallback;
import com.morgoo.facade.callback.QueryCallback;
import com.morgoo.helper.compat.PackageManagerCompat;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * DroidPlugin 门面
 *
 * @author EthanCo
 * @since 2016/12/26
 */
public class DroidPluginFacade {
    public static final String TAG = "Z-DroidPluginFacade";
    public static final int REQUEST_PERMISSIONS_DROID = 99662233;

    //是否已安装 (比较耗时，建议使用isInstalledAsync()在子线程执行)
    public static boolean isInstalled(ApkItem item) {
        try {
            return null != PluginManager.getInstance().getPackageInfo(item.packageInfo.packageName, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    //是否已安装 在子线程执行
    public static void isInstalledAsync(final ApkItem item, final QueryCallback queryCallback) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                final boolean result = isInstalled(item);
                if (queryCallback != null) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            queryCallback.onQueryResult(result);
                        }
                    });
                }
            }
        }.start();
    }

    //安装
    public static synchronized void installPlugin(final ApkItem item, final InstallCallback installCallback) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                item.installing = true;
                try {
                    boolean isInstall = isInstalled(item);
                    int flag = isInstall ? PackageManagerCompat.INSTALL_REPLACE_EXISTING : 0;
                    final int re = PluginManager.getInstance().installPackage(item.apkfile, flag);
                    item.installing = false;

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            if (installCallback != null) {
                                installCallback.onInstallResult(re);
                            }
                    /*switch (re) {
                        case PluginManager.INSTALL_FAILED_NO_REQUESTEDPERMISSION:
                            Toast.makeText(getActivity(), "安装失败，文件请求的权限太多", Toast.LENGTH_SHORT).show();
                            break;
                        case INSTALL_FAILED_NOT_SUPPORT_ABI:
                            Toast.makeText(getActivity(), "宿主不支持插件的abi环境，可能宿主运行时为64位，但插件只支持32位", Toast.LENGTH_SHORT).show();
                            break;
                        case INSTALL_SUCCEEDED:
                            Toast.makeText(getActivity(), "安装完成", Toast.LENGTH_SHORT).show();
                            adapter.notifyDataSetChanged();
                            break;
                    }*/
                        }
                    });
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public static void startLoad(Activity context, final FoundPluginCallback foundPluginCallback) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            DroidPluginFacade.startLoadInner(context, foundPluginCallback);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                context.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSIONS_DROID);
            }
        }
    }

    public static void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults, Context context, final FoundPluginCallback foundPluginCallback) {
        if (requestCode == REQUEST_PERMISSIONS_DROID) {
            if (permissions != null && permissions.length > 0) {
                for (int i = 0; i < permissions.length; i++) {
                    String permisson = permissions[i];
                    int grantResult = grantResults[i];
                    if (Manifest.permission.READ_EXTERNAL_STORAGE.equals(permisson)) {
                        if (grantResult == PackageManager.PERMISSION_GRANTED) {
                            DroidPluginFacade.startLoadInner(context, foundPluginCallback);
                        } else {
                            Toast.makeText(context, "没有授权，无法使用", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }

    public static void startLoadInner(final Context context, final FoundPluginCallback foundPluginCallback) {
        /*if (!isViewCreated) {
            return;
        }*/
        if (foundPluginCallback == null) {
            return;
        }

        new Thread("ApkScanner") {
            @Override
            public void run() {
                File file = Environment.getExternalStorageDirectory();

                List<File> apks = new ArrayList<>(10);
                File[] files = file.listFiles();
                if (files != null) {
                    for (File apk : files) {
                        if (isPlugin(apk)) {
                            apks.add(apk);
                        }
                    }
                }

                /*file = new File(Environment.getExternalStorageDirectory(), "360Download");
                if (file.exists() && file.isDirectory()) {
                    File[] files1 = file.listFiles();
                    if (files1 != null) {
                        for (File apk : files1) {
                            if (apk.exists() && apk.getPath().toLowerCase().endsWith(".apk")) {
                                apks.add(apk);
                            }
                        }
                    }
                }*/

                PackageManager pm = context.getPackageManager();
                for (final File apk : apks) {
                    try {
                        if (isPlugin(apk)) {
                            final PackageInfo info = pm.getPackageArchiveInfo(apk.getPath(), 0);
                            if (info != null) { // && isViewCreated
                                try {
                                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                                        @Override
                                        public void run() {
                                            foundPluginCallback.onFoundPlugin(new ApkItem(context, info, apk.getPath()));
                                            //adapterWrap.add(new ApkItem(getActivity(), info, apk.getPath()));
                                        }
                                    });
                                } catch (Exception e) {
                                }
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }.start();
    }

    //是否是插件
    private static boolean isPlugin(File apk) {
        return apk.exists() && apk.getPath().toLowerCase().endsWith(".apk");
    }

    //启动Plugin
    public static void actionStartPlugin(Context context, ApkItem item) {
        if (isInstalled(item)) {
            PackageManager pm = context.getPackageManager();
            Intent intent = pm.getLaunchIntentForPackage(item.packageInfo.packageName);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            Log.e(TAG, "actionStartPlugin 请先安装插件");
        }
    }

    //卸载已安装插件
    public static boolean uninstallPlugin(String packageName) {
        try {
            PluginManager.getInstance().deletePackage(packageName, 0);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    //获取已安装的插件 PackageInfo
    public static List<PackageInfo> getInstalledPackages() {
        try {
            return PluginManager.getInstance().getInstalledPackages(0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    //获取已安装的插件
    public static List<ApkItem> getInstalledPlugins(Context context) {
        List<ApkItem> apkItems = new ArrayList<>();
        try {
            final List<PackageInfo> infos = PluginManager.getInstance().getInstalledPackages(0);
            final PackageManager pm = context.getPackageManager();
            for (final PackageInfo info : infos) {
                apkItems.add(new ApkItem(pm, info, info.applicationInfo.publicSourceDir));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return apkItems;
    }
}
