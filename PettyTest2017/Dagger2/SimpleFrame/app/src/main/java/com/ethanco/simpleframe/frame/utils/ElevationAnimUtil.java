package com.ethanco.simpleframe.frame.utils;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

/**
 * Elevation 升降 动画
 * <p/>
 * Created by EthanCo on 2016/1/22.
 */
public class ElevationAnimUtil {

    private static final int DEFUALT_MIN_ELEVATION = 1; //默认的最小的elevation
    private static final int DEFUALT_DEC_ELEVATION = 20; //默认的增加的elevation

    private ElevationAnimUtil() {
    }

    private static ElevationAnimUtil sInstance = new ElevationAnimUtil();

    public static ElevationAnimUtil getInstance() {
        return sInstance;
    }

    /**
     * 获得Elevation下降的Animator
     *
     * @param targetView
     * @param duration   动画的总时间
     * @return
     */
    public static ValueAnimator getDropElevationValue(final View targetView, int duration) {
        return getDropElevationValue(targetView, duration, DEFUALT_MIN_ELEVATION, DEFUALT_DEC_ELEVATION);
    }

    /**
     * 获得Elevation下降的Animator
     *
     * @param targetView
     * @param duration
     * @param mimElevation 最小的Elevation
     * @param decElevation 最大的Elevation
     * @return
     */
    public static ValueAnimator getDropElevationValue(final View targetView, int duration, final int mimElevation, final int decElevation) {
        ValueAnimator decElevationValueAnim = ValueAnimator.ofInt(1);
        decElevationValueAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    targetView.setElevation(fraction * decElevation + mimElevation);
                }
            }
        });
        decElevationValueAnim.setDuration(duration);
        return decElevationValueAnim;
    }

    /**
     * 获得Elevation上升的Animator
     *
     * @param targetView
     * @param duration
     * @param mimElevation 最小的Elevation
     * @param maxElevation 最大的Elevation
     * @return
     */
    public static ValueAnimator getRiseElevationValue(final View targetView, int duration, final int mimElevation, final int maxElevation) {
        ValueAnimator addElevationValueAnim = ValueAnimator.ofInt(1);
        addElevationValueAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    targetView.setElevation((1 - fraction) * maxElevation + mimElevation);
                }
            }
        });
        addElevationValueAnim.setDuration(duration);
        return addElevationValueAnim;
    }

    /**
     * 获得Elevation上升的Animator
     *
     * @param targetView
     * @param duration
     * @return
     */
    public static ValueAnimator getRiseElevationValue(final View targetView, int duration) {
        return getRiseElevationValue(targetView, duration, DEFUALT_MIN_ELEVATION, DEFUALT_DEC_ELEVATION);
    }
}
