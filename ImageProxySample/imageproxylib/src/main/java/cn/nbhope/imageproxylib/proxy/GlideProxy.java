package cn.nbhope.imageproxylib.proxy;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import cn.nbhope.imageproxylib.abs.ICreate;
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
    public ICreate load(String url) {
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

    public static class Creator implements ICreate {
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
        public void into(ImageView view) {
            creator.into(view);
        }
    }
}
