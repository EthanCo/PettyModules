package com.ethanco.lsn11_bitmaptest;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import static android.graphics.BitmapFactory.decodeResource;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //deocde 最终都会调用decodeStream()
    public void decode() {
        BitmapFactory.decodeFile("pathName");
        decodeResource(null, 0);
        BitmapFactory.decodeStream(null);
    }


    public void qualityCompress(View v) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img1, options);
        compressImageToFile(bitmap, new File("sdcard/qualityCompress.jpg"));
    }

    public void sizeCompress(View v) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img1, options);
        compressImageToFileBySize(bitmap, new File("sdcard/sizeCompress.jpg"));
    }

    public void sampleSizeCompress(View v) {
        compressBitmapToFileBySampleSize(getResources(),
                R.drawable.img1, new File("sdcard/sampleSizeCompress.jpg"));
    }

    /**
     * 质量压缩
     * 原理:通过算法扣掉图片中的某些点附近相近的像素，达到降低质量减少文件大小的目的。
     * 减小了图片质量
     * 注意:它其实只能实现对file的影响，对加载这个图片出来的bitmap内存是无法节省的，还是那么大。
     * 因为bitmap在内存中的大小是按照像素计算的，也就是width*height，对于质量压缩，并不会改变图片的真实像素。
     * <p>
     * 使用场景: 将图片压缩保存到本地，或者想图片上传到服务器，根据实际需求来。
     * <p>
     * AB
     * CD
     * 像素变为
     * AA
     * AA
     * 四个相同的像素
     */
    private void compressImageToFile(Bitmap bmp, File file) {
        int quality = 50;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 尺寸压缩
     * 通过减少单位尺寸的像素值，真正意义上的降低像素。1920*1080 压缩倍数4 -> 495*270
     * 使用场景:缓存缩略图，头像处理
     */
    public void compressImageToFileBySize(Bitmap bmp, File file) {
        int ratio = 4; //压缩尺寸倍数，值越大，图片的尺寸越小
        int quality = 100;
        Bitmap result = Bitmap.createBitmap(bmp.getWidth() / ratio,
                bmp.getHeight() / ratio, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(result);
        RectF rect = new RectF(0, 0, bmp.getWidth() / ratio, bmp.getHeight() / ratio);
        canvas.drawBitmap(bmp, null, rect, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        result.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置图片的采样率，降低图片像素。
     *
     * @param res
     * @param resId
     * @param file
     */
    public void compressBitmapToFileBySampleSize(Resources res, int resId, File file) {
        //数值越高，图片像素越低
        int inSampleSize = 8;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false; //为true时不会真正加载图片，而是得到图片的宽高信息。
        //采样率
        options.inSampleSize = inSampleSize;
        Bitmap bitmap = BitmapFactory.decodeResource(res, resId, options);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 终极压缩
     * 1.IOS拍照!M的图片要比安卓拍照出来的5M的图片一样清晰
     * 都是在同一个环境下，保存的都是JPEG. --- 其实是一样的
     *
     * 2.图片处理引擎
     * 95年 JPEG处理引擎，最初用于在PC上面处理图片的引擎。
     * 05年 Skia开源引擎，开发了一套基于JPEG处理引擎的第二次开发。便于浏览器的使用
     * 07年 Android上面用的是Skia引擎(阉割版) ---- 去掉了编码的哈夫曼算法，采用定长编码算法。
     * 但是解码还是保留了哈夫曼算法。
     * 导致图片处理后文件变大了
     * 理由: 当时由于CPU和内存在手机上都非常吃紧，性能差，由于哈夫曼算法非常吃CPU，被迫用了其他的算法
     *
     * 我们的优化:
     * 绕过安卓Bitmap API层，来自己编码实现 ---- 修复使用哈夫曼算法。
     *
     * 哈夫曼: 需要去扫描整个信息 (图片信息 -- 每一个像素包括ARGB)，要大量的计算，很吃CPU。
     *
     * argb
     * 一个像素点包含四个信息: alpha，red，green，blue
     *
     */
}
