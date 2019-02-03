package com.heiko.amaptest.clusterv3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.heiko.amaptest.R;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Heiko
 * @date 2019/2/2
 */
public class ClusterRenderFactory {

    private ClusterRender bikeClusterRender;
    private ClusterRender parkClusterRender;

    ClusterRender createBikeClusterRender(final Context context) {
        if (bikeClusterRender == null) {
            bikeClusterRender = new ClusterRender() {
                private Map<Integer, Drawable> mBackDrawAbles = new HashMap<>();

                @Override
                public Drawable getDrawAble(int clusterNum) {
                    int radius = ClusterUtils.dp2px(context, 80);
                    if (clusterNum == 1) {
                        Drawable bitmapDrawable = mBackDrawAbles.get(1);
                        if (bitmapDrawable == null) {
                            bitmapDrawable = context.getResources().getDrawable(
                                    R.drawable.icon_openmap_mark);
                            mBackDrawAbles.put(1, bitmapDrawable);
                        }

                        return bitmapDrawable;
                    } else if (clusterNum < 5) {

                        Drawable bitmapDrawable = mBackDrawAbles.get(2);
                        if (bitmapDrawable == null) {
                            bitmapDrawable = new BitmapDrawable(null, drawCircle(radius,
                                    Color.RED)); //Color.argb(159, 210, 154, 6)
                            mBackDrawAbles.put(2, bitmapDrawable);
                        }

                        return bitmapDrawable;
                    } else if (clusterNum < 10) {
                        Drawable bitmapDrawable = mBackDrawAbles.get(3);
                        if (bitmapDrawable == null) {
                            bitmapDrawable = new BitmapDrawable(null, drawCircle(radius,
                                    Color.RED)); //Color.argb(199, 217, 114, 0)
                            mBackDrawAbles.put(3, bitmapDrawable);
                        }

                        return bitmapDrawable;
                    } else {
                        Drawable bitmapDrawable = mBackDrawAbles.get(4);
                        if (bitmapDrawable == null) {
                            bitmapDrawable = new BitmapDrawable(null, drawCircle(radius,
                                    Color.RED)); //Color.argb(235, 215, 66, 2)
                            mBackDrawAbles.put(4, bitmapDrawable);
                        }

                        return bitmapDrawable;
                    }
                }
            };
        }
        return bikeClusterRender;
    }

    ClusterRender createParkClusterRender(final Context context) {
        if (parkClusterRender == null) {
            parkClusterRender = new ClusterRender() {
                private Map<Integer, Drawable> mBackDrawAbles = new HashMap<>();

                @Override
                public Drawable getDrawAble(int clusterNum) {
                    int radius = ClusterUtils.dp2px(context, 80);
                    if (clusterNum == 1) {
                        Drawable bitmapDrawable = mBackDrawAbles.get(1);
                        if (bitmapDrawable == null) {
                            bitmapDrawable = context.getResources().getDrawable(
                                    R.mipmap.ic_park_point);
                            mBackDrawAbles.put(1, bitmapDrawable);
                        }

                        return bitmapDrawable;
                    } else if (clusterNum < 5) {

                        Drawable bitmapDrawable = mBackDrawAbles.get(2);
                        if (bitmapDrawable == null) {
                            bitmapDrawable = new BitmapDrawable(null, drawCircle(radius,
                                    Color.BLUE)); //Color.argb(159, 210, 154, 6)
                            mBackDrawAbles.put(2, bitmapDrawable);
                        }

                        return bitmapDrawable;
                    } else if (clusterNum < 10) {
                        Drawable bitmapDrawable = mBackDrawAbles.get(3);
                        if (bitmapDrawable == null) {
                            bitmapDrawable = new BitmapDrawable(null, drawCircle(radius,
                                    Color.BLUE)); //Color.argb(199, 217, 114, 0)
                            mBackDrawAbles.put(3, bitmapDrawable);
                        }

                        return bitmapDrawable;
                    } else {
                        Drawable bitmapDrawable = mBackDrawAbles.get(4);
                        if (bitmapDrawable == null) {
                            bitmapDrawable = new BitmapDrawable(null, drawCircle(radius,
                                    Color.BLUE)); //Color.argb(235, 215, 66, 2)
                            mBackDrawAbles.put(4, bitmapDrawable);
                        }

                        return bitmapDrawable;
                    }
                }
            };
        }
        return parkClusterRender;
    }

    private Bitmap drawCircle(int radius, int color) {
        Bitmap bitmap = Bitmap.createBitmap(radius * 2, radius * 2,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        RectF rectF = new RectF(0, 0, radius * 2, radius * 2);
        paint.setColor(color);
        canvas.drawArc(rectF, 0, 360, true, paint);
        return bitmap;
    }
}
