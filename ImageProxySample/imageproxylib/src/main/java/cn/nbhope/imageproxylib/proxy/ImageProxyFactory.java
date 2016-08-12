package cn.nbhope.imageproxylib.proxy;

import cn.nbhope.imageproxylib.abs.ImageProxy;

/**
 * @Description ImageProxy 工厂
 * Created by EthanCo on 2016/8/12.
 */
public class ImageProxyFactory {
    public static ImageProxy create(Type type) {
        if (type == Type.GLIDE) {
            return GlideProxy.getInstace();
        } else if (type == Type.PICASSO) {
            return PicassoProxy.getInstace();
        }
        throw new IllegalArgumentException("not found type:" + type);
    }
}
