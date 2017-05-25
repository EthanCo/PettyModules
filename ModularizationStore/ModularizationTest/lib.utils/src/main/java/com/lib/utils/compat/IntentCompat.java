package com.lib.utils.compat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;

import java.util.List;

/**
 * Intent Service兼容
 *
 * @author EthanCo
 * @since 2016/12/28
 */

public class IntentCompat {
    public static Intent getExplicitIntent(Context context, Intent implicitIntent) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            // Retrieve all services that can match the given intent
            PackageManager pm = context.getPackageManager();
            List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);
            // Make sure only one match was found
            if (resolveInfo == null || resolveInfo.size() != 1) {
                return null;
            }
            // Get component info and create ComponentName
            ResolveInfo serviceInfo = resolveInfo.get(0);
            String packageName = serviceInfo.serviceInfo.packageName;
            String className = serviceInfo.serviceInfo.name;
            ComponentName component = new ComponentName(packageName, className);
            // Create a new intent. Use the old one for extras and such reuse
            Intent explicitIntent = new Intent(implicitIntent);
            // Set the component to be explicit
            explicitIntent.setComponent(component);
            return explicitIntent;
        } else {
            return implicitIntent;
        }
    }
}
