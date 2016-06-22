package cn.nbhope.imageproxylib.abs;

import android.widget.ImageView;

/**
 * @Description 图片建造者
 * Created by EthanCo on 2016/6/23.
 */
public interface ICreate {
    /**
     * 设置淡入淡出效果
     *
     * @return
     */
    ICreate crossFade();

    /**
     * 将图片加载到ImageView
     *
     * @param view
     */
    void into(ImageView view);
}
