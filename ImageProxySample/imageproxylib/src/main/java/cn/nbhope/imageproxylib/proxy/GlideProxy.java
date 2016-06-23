package cn.nbhope.imageproxylib.proxy;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import cn.nbhope.imageproxylib.abs.ICreator;
import cn.nbhope.imageproxylib.abs.ImageProxy;

/**
 * @Description Glide代理
 * Created by EthanCo on 2016/6/23.
 */
public class GlideProxy extends ImageProxy<RequestManager> {
    private GlideProxy() {
    }

    private static class SingleTonHolder {
        private static GlideProxy sInstance = new GlideProxy();
    }

    public static GlideProxy getInstace() {
        return SingleTonHolder.sInstance;
    }

    @Override
    public ICreator load(String url) {
        DrawableTypeRequest<String> creator = proxy.load(url);
        return new Creator(creator);
    }

    public GlideProxy with(Context context) {
        proxy = Glide.with(context);
        return SingleTonHolder.sInstance;
    }

    public GlideProxy with(Activity activity) {
        proxy = Glide.with(activity);
        return SingleTonHolder.sInstance;
    }

    public GlideProxy with(Fragment fragment) {
        proxy = Glide.with(fragment.getActivity());
        return SingleTonHolder.sInstance;
    }

    public GlideProxy with(android.app.Fragment fragment) {
        proxy = Glide.with(fragment.getActivity());
        return SingleTonHolder.sInstance;
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
    }
}
