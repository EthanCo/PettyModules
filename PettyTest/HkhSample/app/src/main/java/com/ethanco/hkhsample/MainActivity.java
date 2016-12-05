package com.ethanco.hkhsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.category.Category;
import com.ximalaya.ting.android.opensdk.model.category.CategoryList;
import com.ximalaya.ting.android.opensdk.model.live.radio.Radio;
import com.ximalaya.ting.android.opensdk.model.live.radio.RadioCategory;
import com.ximalaya.ting.android.opensdk.model.live.radio.RadioCategoryList;
import com.ximalaya.ting.android.opensdk.model.live.radio.RadioList;
import com.ximalaya.ting.android.opensdk.model.tag.Tag;
import com.ximalaya.ting.android.opensdk.model.tag.TagList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //method_3_2_1();
        //method_3_2_2(1);
        //getRankRadios();
        getRadios();
        //getRadiosCategory();
    }

    //3.2.1 获取喜马拉雅内容分类
    private void method_3_2_1() {
        Map<String, String> map = new HashMap<>();
        CommonRequest.getCategories(map, new IDataCallBack<CategoryList>() {
            @Override
            public void onSuccess(CategoryList response) {
                List<Category> categorys = response.getCategories();
                StringBuilder sb = new StringBuilder();
                for (Category category : categorys) {
                    sb.append(category.getCategoryName());
                    sb.append(",");
                }

                L.i(sb.toString());
            }

            @Override
            public void onError(int code, String message) {
                L.e(message);
            }
        });
    }

    /**
     * 3.2.2 获取专辑标签或者声音标签
     *
     * @param categoryId 分类 为0时表示热门分类
     */
    private void method_3_2_2(int categoryId) {
        Map<String, String> map = new HashMap<>();
        map.put(DTransferConstants.CATEGORY_ID, String.valueOf(categoryId));
        map.put(DTransferConstants.TYPE, String.valueOf(0));
        CommonRequest.getTags(map, new IDataCallBack<TagList>() {

            @Override
            public void onSuccess(TagList tagList) {
                List<Tag> tags = tagList.getTagList();
                StringBuilder sb = new StringBuilder();
                for (Tag tag : tags) {
                    sb.append(tag.getTagName());
                    sb.append(",");
                }

                L.i(sb.toString());
            }

            @Override
            public void onError(int code, String s) {
                L.e(s);
            }
        });
    }

    /**
     * 获取电台排行榜
     */
    private void getRankRadios() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(DTransferConstants.RADIO_COUNT, String.valueOf(9));
        CommonRequest.getRankRadios(map, new IDataCallBack<RadioList>() {
            @Override
            public void onSuccess(RadioList radioList) {
                List<Radio> radios = radioList.getRadios();
                StringBuilder sb = new StringBuilder();
                for (Radio radio : radios) {
                    sb.append(radio.getRadioName());
                    sb.append(",");
                }

                L.i(sb.toString());
            }

            @Override
            public void onError(int code, String s) {
                L.e(s);
            }
        });
    }

    private void getRadios() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(DTransferConstants.RADIOTYPE, String.valueOf(3));
        map.put(DTransferConstants.PROVINCECODE, String.valueOf(310000));
        map.put(DTransferConstants.PAGE, String.valueOf(1));
        CommonRequest.getRadios(map, new IDataCallBack<RadioList>() {
            @Override
            public void onSuccess(RadioList radioList) {
                List<Radio> radios = radioList.getRadios();
                StringBuilder sb = new StringBuilder();
                for (Radio radio : radios) {
                    sb.append(radio.getRadioName());
                    sb.append(",");
                }

                L.i(sb.toString());
            }

            @Override
            public void onError(int i, String s) {
                L.e(s);
            }
        });
    }

    private void getRadiosCategory() {
        Map<String, String> map = new HashMap<String, String>();
        CommonRequest.getRadioCategory(map, new IDataCallBack<RadioCategoryList>() {
            @Override
            public void onSuccess(RadioCategoryList categoryList) {
                List<RadioCategory> radioCategories = categoryList.getRadioCategories();
                StringBuilder sb = new StringBuilder();
                for (RadioCategory radioCategory : radioCategories) {
                    sb.append(radioCategory.getRadioCategoryName());
                    sb.append(",");
                }
                L.i(sb.toString());
            }

            @Override
            public void onError(int code, String message) {
                L.e(message);
            }
        });
    }
}
