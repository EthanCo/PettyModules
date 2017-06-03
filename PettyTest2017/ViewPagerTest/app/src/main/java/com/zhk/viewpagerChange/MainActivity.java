package com.zhk.viewpagerChange;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.zhk.transformer.StereoPagerTransformer;

import java.util.ArrayList;
import java.util.List;

/*
 * 次项目只在3.0及以上版本支持
 * */
public class MainActivity extends Activity {

    private ViewPager mViewPager;

    private int[] mImgIds = new int[]{R.drawable.guide1, R.drawable.guide2, R.drawable.guide3};
    private List<ImageView> mImages = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //没有标题
        setContentView(R.layout.activity_main);
        /*
         * layout中包名<android.support.v4.view.ViewPager>
		 * 小技巧：shift+ctrl+T ,查找 , 复制包名
		 * */
        mViewPager = (ViewPager) findViewById(R.id.vp);
        //为ViewPager添加动画效果,3.0及以上支持
//		mViewPager.setPageTransformer(true, null);
//		mViewPager.setPageTransformer(true, new DepthPageTransformer());
        //mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        //mViewPager.setPageTransformer(true, new AccordionTransformer()); //平移
        //mViewPager.setPageTransformer(true, new BackgroundToForegroundTransformer()); //缩放 在后
        //mViewPager.setPageTransformer(true, new CubeInTransformer()); //3D旋转 内
        //mViewPager.setPageTransformer(true, new CubeOutTransformer()); //3D旋转 外 1
//        mViewPager.setPageTransformer(true, new DefaultTransformer()); //默认
        //mViewPager.setPageTransformer(true, new DepthPageTransformer()); //卡片
 //       mViewPager.setPageTransformer(true, new DrawFromBackTransformer()); //透明渐变 2
//        mViewPager.setPageTransformer(true, new FlipHorizontalTransformer()); //旋转 中心竖轴
//        mViewPager.setPageTransformer(true, new FlipVerticalTransformer()); //旋转 中心横轴
//        mViewPager.setPageTransformer(true, new ForegroundToBackgroundTransformer()); //缩放 在前
//        mViewPager.setPageTransformer(true, new ParallaxPageTransformer(1));
//        mViewPager.setPageTransformer(true, new RotateDownTransformer()); //平面旋转 以下为中心点
//        mViewPager.setPageTransformer(true, new RotateUpTransformer()); //平面旋转 以上为中心点
//        mViewPager.setPageTransformer(true, new StackTransformer()); //栈 先进后出效果
//        mViewPager.setPageTransformer(true, new TabletTransformer()); //Table
//        mViewPager.setPageTransformer(true, new ZoomInTransformer()); //Table
//        mViewPager.setPageTransformer(true, new ZoomOutSlideTransformer()); //Table
//        mViewPager.setPageTransformer(true, new ZoomOutTranformer()); //Table
//        mViewPager.setPageTransformer(true, new GalleryTransformer());
        //mViewPager.setPageTransformer(true, new StackPageTransformer(mViewPager));
        int deviceWidth = getResources().getDisplayMetrics().widthPixels;
        mViewPager.setPageTransformer(true,new StereoPagerTransformer(deviceWidth)); //3D反转动画 https://juejin.im/post/591d80cd570c35006980a32e
		/*
            ViewPager预加载机制:最多保存3个Page
		*/
        mViewPager.setAdapter(new PagerAdapter() {

            //创建 类似于BaseAdapter的getView方法
            //用来将数据设置给view的
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(mImgIds[position]);
                imageView.setScaleType(ScaleType.CENTER_CROP);
                container.addView(imageView); ///////////////////////////注意这句话，一定要有!!!!!!!不能使用LayoutInflater.inflater，与listview类似，因为inflater会直接填充到viewpager上，而我们应该在addview到viewpager的一个pager上
                mImages.add(imageView);
                return imageView;
            }

            //销毁 必须重写，否则销毁的时候会报错(需注释super.destroy)，super中会抛一个异常要求必须重写该方法
            //positon : 当前需要销毁的page的索引
            //object : 当前需要销毁的page
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mImages.get(position));
                //container.removeView((View) object); 这个也可以
            }

            /*	viewPager最多缓存3个，超过则会执行此方法进行销毁
                true 表示不去创建，使用缓存 false 表示重写创建
                ViewPager里面用了一个mItems(ArrayList)来存储每个page的信息(ItemInfo)，当界面要展示或者发生变化时，
             * 需要依据page的当前信息来调整，但此时只能通过view来查找，所以只能遍历mItems通过比较view和object来找到对应的ItemInfo。*/
            @Override
            public boolean isViewFromObject(View view, Object obj) {
                //view:当前的view obj:将要进来的view
                return view == obj;
            }

            //有多少页
            @Override
            public int getCount() {
                return mImgIds.length;
            }
        });
    }
}
