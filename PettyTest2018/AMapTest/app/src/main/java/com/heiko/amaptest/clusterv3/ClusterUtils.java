package com.heiko.amaptest.clusterv3;

import android.content.Context;

/**
 * TODO
 *
 * @author Heiko
 * @date 2019/2/2
 */
public class ClusterUtils {
    static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
