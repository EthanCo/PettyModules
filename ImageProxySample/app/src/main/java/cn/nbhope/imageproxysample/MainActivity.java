package cn.nbhope.imageproxysample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import cn.nbhope.imageproxylib.abs.ImageProxy;
import cn.nbhope.imageproxylib.proxy.ImageProxyFactory;
import cn.nbhope.imageproxylib.proxy.Type;
import cn.nbhope.imageproxylib.transform.CircleTransform;

public class MainActivity extends AppCompatActivity {

    private ImageView img1;
    private ImageView img2;
    private ImageView img3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);

        String url1 = "http://www.bz55.com/uploads1/allimg/120312/1_120312100435_8.jpg";
        String url2 = "http://img.taopic.com/uploads/allimg/121123/235047-1211231F02793.jpg";

        //Glide.with(this).load("").into(img1);
        //Picasso.with(this).load("").into(img2);

        //ImageProxy imageProxy = ImageProxyFactory.create(Type.PICASSO);
        ImageProxy imageProxy = ImageProxyFactory.create(Type.GLIDE);

        imageProxy.with(this).load(url1).into(img1);
        imageProxy.with(this).load(url2).into(img2);

        Glide.with(this).load(url1).transform(new CircleTransform(this)).into(img3);

    }
}
