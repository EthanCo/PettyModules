package com.ethanco.hkhsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.category.Category;
import com.ximalaya.ting.android.opensdk.model.category.CategoryList;
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

        method_3_2_1();
        method_3_2_2(1);
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
            public void onError(int i, String s) {
                L.e(s);
            }
        });
    }
}
