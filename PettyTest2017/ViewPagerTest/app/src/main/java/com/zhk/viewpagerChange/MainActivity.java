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
 * ����Ŀֻ��3.0�����ϰ汾֧��
 * */
public class MainActivity extends Activity {

    private ViewPager mViewPager;

    private int[] mImgIds = new int[]{R.drawable.guide1, R.drawable.guide2, R.drawable.guide3};
    private List<ImageView> mImages = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //û�б���
        setContentView(R.layout.activity_main);
        /*
         * layout�а���<android.support.v4.view.ViewPager>
		 * С���ɣ�shift+ctrl+T ,���� , ���ư���
		 * */
        mViewPager = (ViewPager) findViewById(R.id.vp);
        //ΪViewPager��Ӷ���Ч��,3.0������֧��
//		mViewPager.setPageTransformer(true, null);
//		mViewPager.setPageTransformer(true, new DepthPageTransformer());
        //mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        //mViewPager.setPageTransformer(true, new AccordionTransformer()); //ƽ��
        //mViewPager.setPageTransformer(true, new BackgroundToForegroundTransformer()); //���� �ں�
        //mViewPager.setPageTransformer(true, new CubeInTransformer()); //3D��ת ��
        //mViewPager.setPageTransformer(true, new CubeOutTransformer()); //3D��ת �� 1
//        mViewPager.setPageTransformer(true, new DefaultTransformer()); //Ĭ��
        //mViewPager.setPageTransformer(true, new DepthPageTransformer()); //��Ƭ
 //       mViewPager.setPageTransformer(true, new DrawFromBackTransformer()); //͸������ 2
//        mViewPager.setPageTransformer(true, new FlipHorizontalTransformer()); //��ת ��������
//        mViewPager.setPageTransformer(true, new FlipVerticalTransformer()); //��ת ���ĺ���
//        mViewPager.setPageTransformer(true, new ForegroundToBackgroundTransformer()); //���� ��ǰ
//        mViewPager.setPageTransformer(true, new ParallaxPageTransformer(1));
//        mViewPager.setPageTransformer(true, new RotateDownTransformer()); //ƽ����ת ����Ϊ���ĵ�
//        mViewPager.setPageTransformer(true, new RotateUpTransformer()); //ƽ����ת ����Ϊ���ĵ�
//        mViewPager.setPageTransformer(true, new StackTransformer()); //ջ �Ƚ����Ч��
//        mViewPager.setPageTransformer(true, new TabletTransformer()); //Table
//        mViewPager.setPageTransformer(true, new ZoomInTransformer()); //Table
//        mViewPager.setPageTransformer(true, new ZoomOutSlideTransformer()); //Table
//        mViewPager.setPageTransformer(true, new ZoomOutTranformer()); //Table
//        mViewPager.setPageTransformer(true, new GalleryTransformer());
        //mViewPager.setPageTransformer(true, new StackPageTransformer(mViewPager));
        int deviceWidth = getResources().getDisplayMetrics().widthPixels;
        mViewPager.setPageTransformer(true,new StereoPagerTransformer(deviceWidth)); //3D��ת���� https://juejin.im/post/591d80cd570c35006980a32e
		/*
            ViewPagerԤ���ػ���:��ౣ��3��Page
		*/
        mViewPager.setAdapter(new PagerAdapter() {

            //���� ������BaseAdapter��getView����
            //�������������ø�view��
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(mImgIds[position]);
                imageView.setScaleType(ScaleType.CENTER_CROP);
                container.addView(imageView); ///////////////////////////ע����仰��һ��Ҫ��!!!!!!!����ʹ��LayoutInflater.inflater����listview���ƣ���Ϊinflater��ֱ����䵽viewpager�ϣ�������Ӧ����addview��viewpager��һ��pager��
                mImages.add(imageView);
                return imageView;
            }

            //���� ������д���������ٵ�ʱ��ᱨ��(��ע��super.destroy)��super�л���һ���쳣Ҫ�������д�÷���
            //positon : ��ǰ��Ҫ���ٵ�page������
            //object : ��ǰ��Ҫ���ٵ�page
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mImages.get(position));
                //container.removeView((View) object); ���Ҳ����
            }

            /*	viewPager��໺��3�����������ִ�д˷�����������
                true ��ʾ��ȥ������ʹ�û��� false ��ʾ��д����
                ViewPager��������һ��mItems(ArrayList)���洢ÿ��page����Ϣ(ItemInfo)��������Ҫչʾ���߷����仯ʱ��
             * ��Ҫ����page�ĵ�ǰ��Ϣ������������ʱֻ��ͨ��view�����ң�����ֻ�ܱ���mItemsͨ���Ƚ�view��object���ҵ���Ӧ��ItemInfo��*/
            @Override
            public boolean isViewFromObject(View view, Object obj) {
                //view:��ǰ��view obj:��Ҫ������view
                return view == obj;
            }

            //�ж���ҳ
            @Override
            public int getCount() {
                return mImgIds.length;
            }
        });
    }
}
