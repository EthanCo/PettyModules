package com.lib.imageproxy.abs;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.request.target.SimpleTarget;

/**
 * @Description 图片建造者
 * Created by EthanCo on 2016/6/23.
 */
public interface ICreator {
    /**
     * 设置淡入淡出效果
     *
     * @return
     */
    ICreator crossFade();

    /**
     * ScaleType - centerCrop
     *
     * @return
     */
    ICreator centerCrop();

    /**
     * 指定大小
     *
     * @param width
     * @param height
     * @return
     */
    ICreator override(int width, int height);

    /**
     * 先加载缩略图，再加载完整的图片
     *
     * @param sizeMultiplier 缩略图比例 0-1
     * @return
     */
    ICreator thumbnail(float sizeMultiplier);

    /**
     * 设置占位图
     *
     * @param resourceId
     * @return
     */
    ICreator placeholder(@DrawableRes int resourceId);

    /**
     * 设置占位图
     *
     * @param drawable
     * @return
     */
    ICreator placeholder(Drawable drawable);

    /**
     * 设置加载错误时显示的图片
     *
     * @param resourceId
     * @return
     */
    ICreator error(@DrawableRes int resourceId);

    /**
     * 设置加载错误时显示的图片
     *
     * @param drawable
     * @return
     */
    ICreator error(Drawable drawable);

    /**
     * 将图片加载到ImageView
     *
     * @param view
     */
    void into(ImageView view);

    /**
     * 取得Bitmap进行处理
     *
     * @param simpleTarget
     */
    void into(SimpleTarget<Bitmap> simpleTarget);
}
