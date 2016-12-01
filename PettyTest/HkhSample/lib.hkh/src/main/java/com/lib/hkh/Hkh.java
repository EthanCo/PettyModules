package com.lib.hkh;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.lib.hkh.security.AES;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;

/**
 * 门面
 *
 * @author EthanCo
 * @since 2016/11/23
 */

public class Hkh {
    public static final String PREFIX = "/'|][prefix*0--====";
    public static final String AES_KEY = "M0Tf/1Va#Gamjxjy";

    public static void init(@NonNull Context context) throws Exception {
        ApplicationInfo appInfo = context.getPackageManager()
                .getApplicationInfo(context.getPackageName(),
                        PackageManager.GET_META_DATA);
        String appSecret = appInfo.metaData.getString("appsecret");
        if (TextUtils.isEmpty(appSecret)) {
            throw new IllegalArgumentException("metaData appsecret 为空，请在AndroidManifest中填写metaData");
        }
        String appSecretDec = AES.Decrypt(appSecret, AES_KEY);
        appSecretDec = appSecretDec.replace(PREFIX, "");

        CommonRequest.getInstanse().init(context, appSecretDec);
    }
}
