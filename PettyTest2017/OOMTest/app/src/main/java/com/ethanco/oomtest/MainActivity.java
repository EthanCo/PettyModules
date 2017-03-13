package com.ethanco.oomtest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * 处理OOM
 * 1.读取图片时进行适当压缩
 * 2.在适当的时候及时回收图片占用的内存
 * 3.不必要的时候避免图片的完整加载 inJustDecodeBounds设为true
 * 4.设定内存利用率的百分比，当实际的利用率偏离这个百分比的时候，虚拟机会在GC的时候调整堆内存大小，让实际占用率向个百分比靠拢
 * 5.自定义堆（Heap）内存大小，可以防止过于频繁的堆内存分配
 * 6.Android:largeHeap="true" 简单粗暴。这种方法允许应用需要耗费手机很多的内存空间，但却是最快捷的解决办法
 *
 * https://my.oschina.net/u/1389206/blog/324731
 * http://44289533.iteye.com/blog/1743592
 * http://www.tuicool.com/articles/JBFVFzJ
 * https://yq.aliyun.com/articles/28242
 * http://www.tuicool.com/articles/viIFfu
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0920/3478.html
 */
public class MainActivity extends AppCompatActivity {

    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;
    private ImageView img6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);
        img5 = (ImageView) findViewById(R.id.img5);
        img6 = (ImageView) findViewById(R.id.img6);

        img1.setImageResource(R.drawable.bg1);
        //img2.setImageResource(R.drawable.bg2);
        //img3.setImageResource(R.drawable.bg3);
        //img4.setImageResource(R.drawable.bg4);
        //img5.setImageResource(R.drawable.bg5);

        //解决加载图片 内存溢出的问题
        //Options 只保存图片尺寸大小，不保存图片到内存
        Bitmap bmp = null;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        //缩放的比例，宽度和高度的缩放比例，总缩放比例为2*2=4，缩放是很难按准备的比例进行缩放的，其值表明缩放的倍数，SDK中建议其值是2的指数值,值越大会导致图片不清晰
        opts.inSampleSize = 2;
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.bg6, opts);
        img6.setImageBitmap(bmp);
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("Z-onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("Z-onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("Z-onDestroy");
        if (img6 != null && img6.getDrawable() != null) {
            Bitmap bitmap = ((BitmapDrawable) img6.getDrawable()).getBitmap();
            img6.setImageDrawable(null);
            if (bitmap != null) {
                bitmap.recycle();
            }
        }
        System.gc();
    }
}
