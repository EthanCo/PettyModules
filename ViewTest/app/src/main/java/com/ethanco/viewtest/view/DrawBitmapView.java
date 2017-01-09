package com.ethanco.viewtest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by EthanCo on 2016/12/20.
 */

public class DrawBitmapView extends View {
    private final Paint mPaint;
    private Bitmap bitmap1;
    private Bitmap bitmap2;

    public DrawBitmapView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bitmap1 == null) {
            bitmap1 = createBitmap();
        }
        if (bitmap2 == null) {
            bitmap2 = createBitmap();
        }

        //绘制到Bitmap上
        Canvas canvas1 = new Canvas(bitmap1);
        canvas1.drawRect(0, 0, 150, 200, mPaint);

        //绘制到Bitmap上
        Canvas canvas2 = new Canvas(bitmap2);
        canvas2.drawCircle(80, 80, 80, mPaint);

        //真正绘制到canvas上
        canvas.drawBitmap(bitmap1, 50, 50, mPaint);
        canvas.drawBitmap(bitmap2, 50, 300, mPaint);
    }

    private Bitmap createBitmap() {
        return Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
    }
}
