package cn.nbhope.imageproxylib.proxy;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntegerRes;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;

import cn.nbhope.imageproxylib.abs.ICreator;
import cn.nbhope.imageproxylib.abs.ILoader;
import cn.nbhope.imageproxylib.abs.ImageProxy;

/**
 * @Description Picasso代理
 * Created by EthanCo on 2016/6/23.
 */
class PicassoProxy extends ImageProxy {

    private PicassoProxy() {
    }

    private static class SingleTonHolder {
        private static PicassoProxy sInstance = new PicassoProxy();
    }

    public static PicassoProxy getInstace() {
        return SingleTonHolder.sInstance;
    }

    @Override
    public ILoader with(Context context) {
        return new Loader(Picasso.with(context));
    }

    @Override
    public ILoader with(Activity activity) {
        return new Loader(Picasso.with(activity));
    }

    @Override
    public ILoader with(Fragment fragment) {
        return new Loader(Picasso.with(fragment.getActivity()));
    }

    @Override
    public ILoader with(android.app.Fragment fragment) {
        return new Loader(Picasso.with(fragment.getActivity()));
    }


    private static class Loader implements ILoader {

        private Picasso proxy;

        public Loader(Picasso proxy) {
            this.proxy = proxy;
        }

        @Override
        public ICreator load(String url) {
            RequestCreator creator = proxy.load(url);
            return new Creator(creator);
        }

        @Override
        public ICreator load(Uri uri) {
            RequestCreator creator = proxy.load(uri);
            return new Creator(creator);
        }

        @Override
        public ICreator load(File file) {
            RequestCreator creator = proxy.load(file);
            return new Creator(creator);
        }

        @Override
        public ICreator load(@IntegerRes Integer resourceId) {
            RequestCreator creator = proxy.load(resourceId);
            return new Creator(creator);
        }

        @Override
        public ICreator load(byte[] model) {
            throw new IllegalArgumentException("Picasso 不支持 byte[]");
        }

        @Override
        public <V> ICreator load(V model) {
            throw new IllegalArgumentException("Picasso 不支持 子定义类型");
        }
    }


    public static class Creator implements ICreator {
        private final RequestCreator creator;

        public Creator(RequestCreator creator) {
            this.creator = creator;
        }

        @Override
        public Creator crossFade() {
            creator.noFade();
            return this;
        }

        @Override
        public ICreator centerCrop() {
            creator.centerCrop();
            return this;
        }

        @Override
        public ICreator override(int width, int height) {
            creator.resize(width, height);
            return this;
        }

        @Override
        public ICreator thumbnail(float sizeMultiplier) {
            //picasso can not
            return this;
        }


        @Override
        public ICreator placeholder(@DrawableRes int resourceId) {
            creator.placeholder(resourceId);
            return this;
        }

        @Override
        public ICreator placeholder(Drawable drawable) {
            creator.placeholder(drawable);
            return this;
        }

        @Override
        public ICreator error(@DrawableRes int resourceId) {
            creator.error(resourceId);
            return this;
        }

        @Override
        public ICreator error(Drawable drawable) {
            creator.error(drawable);
            return this;
        }

        @Override
        public ICreator transform(BitmapTransformation... transformations) {
            //picasso can not
            return this;
        }

        @Override
        public void into(ImageView view) {
            creator.into(view);
        }
    }
}
