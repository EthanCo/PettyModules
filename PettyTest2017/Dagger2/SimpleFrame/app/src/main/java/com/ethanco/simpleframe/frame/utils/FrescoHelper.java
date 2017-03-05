package com.ethanco.simpleframe.frame.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * Fresco的帮助类
 * <p>
 * Created by EthanCo on 2016/1/10.
 */
public class FrescoHelper {
    /**
     * 获取Bitmap的回调
     *
     * @param uri
     * @param context
     * @param baseBitmapDataSubscriber
     */
    public static void callBitmap(Uri uri, final Context context, BaseBitmapDataSubscriber baseBitmapDataSubscriber) {
        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();

        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        DataSource<CloseableReference<CloseableImage>>
                dataSource = imagePipeline.fetchDecodedImage(imageRequest, context);
        dataSource.subscribe(baseBitmapDataSubscriber, CallerThreadExecutor.getInstance());
    }

    /**
     * 获得获取Bitmap的回调 - 只处理获取bitmap成功
     * @param uri
     * @param context
     * @param simpleBitmapDataSubscriber
     */
    public static void callBitmapSimple(Uri uri, final Context context, final SimpleBitmapDataSubscriber simpleBitmapDataSubscriber) {
        callBitmap(uri, context, new BaseBitmapDataSubscriber() {
            @Override
            protected void onNewResultImpl(Bitmap bitmap) {
                simpleBitmapDataSubscriber.onNewResultImpl(bitmap);
            }

            @Override
            protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                L.e("FrescoHelper", "onFailureImpl: ");
            }
        });
    }

    public interface SimpleBitmapDataSubscriber {
        void onNewResultImpl(Bitmap bitmap);
    }
}
