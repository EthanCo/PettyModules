package com.lib.imageproxy.proxy;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntegerRes;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.target.SimpleTarget;
import com.lib.imageproxy.abs.ICreator;
import com.lib.imageproxy.abs.ILoader;
import com.lib.imageproxy.abs.ImageProxy;

import java.io.File;

/**
 * @Description Glide代理
 * Created by EthanCo on 2016/6/23.
 */
@SuppressWarnings("unchecked")
class GlideProxy extends ImageProxy {

    private GlideProxy() {
    }

    private static class SingleTonHolder {
        private static GlideProxy sInstance = new GlideProxy();
    }

    public static GlideProxy getInstace() {
        return SingleTonHolder.sInstance;
    }

    public ILoader with(Context context) {
        return new Loader(Glide.with(context));
    }

    public ILoader with(Activity activity) {
        return new Loader(Glide.with(activity));
    }

    public ILoader with(Fragment fragment) {
        return new Loader(Glide.with(fragment.getActivity()));
    }

    public ILoader with(android.app.Fragment fragment) {
        return new Loader(Glide.with(fragment.getActivity()));
    }

    private static class Loader implements ILoader {

        private RequestManager proxy;

        public Loader(RequestManager proxy) {
            this.proxy = proxy;
        }

        @Override
        public ICreator load(String url) {
            DrawableTypeRequest<String> creator = proxy.load(url);
            return new Creator(creator);
        }

        @Override
        public ICreator load(Uri uri) {
            DrawableTypeRequest<Uri> creator = proxy.load(uri);
            return new Creator(creator);
        }

        @Override
        public ICreator load(File file) {
            DrawableTypeRequest<File> creator = proxy.load(file);
            return new Creator(creator);
        }

        @Override
        public ICreator load(@IntegerRes Integer resourceId) {
            DrawableTypeRequest<Integer> creator = proxy.load(resourceId);
            return new Creator(creator);
        }

        @Override
        public ICreator load(byte[] model) {
            DrawableTypeRequest<byte[]> creator = proxy.load(model);
            return new Creator(creator);
        }

        @Override
        public <V> ICreator load(V model) {
            DrawableTypeRequest<V> creator = proxy.load(model);
            return new Creator(creator);
        }
    }

    private static class Creator implements ICreator {
        private final DrawableTypeRequest creator;

        public Creator(DrawableTypeRequest creator) {
            this.creator = creator;
        }

        @Override
        public Creator crossFade() {
            creator.crossFade();
            return this;
        }

        @Override
        public Creator centerCrop() {
            creator.centerCrop();
            return this;
        }

        @Override
        public Creator override(int width, int height) {
            creator.override(width, height);
            return this;
        }

        @Override
        public ICreator thumbnail(float sizeMultiplier) {
            creator.thumbnail(sizeMultiplier);
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
        public Creator error(@DrawableRes int resourceId) {
            creator.error(resourceId);
            return this;
        }

        @Override
        public Creator error(Drawable drawable) {
            creator.error(drawable);
            return this;
        }

        @Override
        public void into(ImageView view) {
            creator.into(view);
        }

        @Override
        public void into(SimpleTarget<Bitmap> simpleTarget) {
            creator.asBitmap().into(simpleTarget);
        }

    }
}
