package com.bocai.boc_tongxing.imagetest;

import android.content.Context;

import com.squareup.picasso.Picasso;

/**
 * @Description 图片加载工具类
 * Created by win7 on 2016/5/13.
 */
public class ImageUtil implements ILoader {

    private final Picasso imageUtil;

    public ImageUtil(Picasso imageUtil) {
        this.imageUtil = imageUtil;
    }

    public ICreator load(String url) {
        com.squareup.picasso.RequestCreator creator = imageUtil.load(url);
        return new Creator(creator);
    }

    public static ImageUtil with(Context context) {
        Picasso util = Picasso.with(context);
        return new ImageUtil(util);
    }

    public static class Creator implements ICreator {
        private final com.squareup.picasso.RequestCreator creator;

        public Creator(com.squareup.picasso.RequestCreator creator) {
            this.creator = creator;
        }

        @Override
        public void into(ImgView view) {
            creator.into(view);
        }
    }
}
