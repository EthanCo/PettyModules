package com.ethanco.slidead;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhk on 2015/10/3.
 */
public class AdView extends LinearLayout {
    private ViewPager viewPager;
    private List<Uri> uriList;
    private List<String> titleList;
    private LayoutInflater mInflater;
    private DotView dotView;

    private static final int AD_AUTO_NEXT = 403;
    private TextView titleTv;

    public void setIsAutoNext(boolean isAutoNext) {
        this.isAutoNext = isAutoNext;
    }

    private boolean isAutoNext = true;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case AD_AUTO_NEXT:
                    if (isAutoNext) {
                        toNext();
                        handler.sendEmptyMessageDelayed(AD_AUTO_NEXT, 4000);
                    }
                    break;
            }
        }
    };

    public AdView(Context context) {
        super(context);
        init();
    }

    public AdView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AdView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //Fresco.initialize(getContext());
        View view = View.inflate(getContext(), R.layout.layout_ad, this);
        dotView = (DotView) view.findViewById(R.id.dotView_ad);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager_ad);
        titleTv = (TextView) findViewById(R.id.titleTv);
        mInflater = LayoutInflater.from(getContext());
        uriList = new ArrayList<>();
        titleList = new ArrayList<>();
    }

    public void addData(Uri uri, String title) {
        if (uri != null && title != null) {
            uriList.add(uri);
            titleList.add(title);
        }
    }

    /**
     * 显示
     *
     * @param isAutoNext 是否自动播放
     */
    public void show(boolean isAutoNext) {
        this.isAutoNext = isAutoNext;
        this.titleTv.setText(titleList.get(0));
        viewPager.setAdapter(new PagerAdapter() {

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                int roundPosition = getRoundPosition(position);
                View childView = mInflater.inflate(R.layout.ad_child_view, null);
                SimpleDraweeView imgChild = (SimpleDraweeView) childView.findViewById(R.id.img_ad_child_view);
                imgChild.setImageURI(uriList.get(roundPosition));
                container.addView(childView);
                return childView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                //container.removeView(childList.get(position));
                container.removeView((View) object);
            }

            @Override
            public boolean isViewFromObject(View view, Object obj) {
                return view == obj;
            }

            @Override
            public int getCount() {
                //return uriList.size();
                return Integer.MAX_VALUE;
            }
        });
        int initPosition = Integer.MAX_VALUE / uriList.size() / 2 * uriList.size();
        viewPager.setCurrentItem(initPosition); //初始位置

        dotView.setDotNumber(uriList.size());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dotView.setCurrFocus(position);
                titleTv.setText(titleList.get(getRoundPosition(position)));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        handler.sendEmptyMessageDelayed(AD_AUTO_NEXT, 4000);
    }

    private int getRoundPosition(int position) {
        return position % uriList.size();
    }

    public void toNext() {
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
    }

    public void toPrevious() {
        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
    }
}
