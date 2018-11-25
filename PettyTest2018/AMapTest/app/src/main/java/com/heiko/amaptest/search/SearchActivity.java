package com.heiko.amaptest.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.services.core.PoiItem;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.poisearch.SubPoiItem;
import com.heiko.amaptest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索地点
 */
public class SearchActivity extends AppCompatActivity implements PoiSearch.OnPoiSearchListener, Inputtips.InputtipsListener {

    private Button btnSearch;
    private EditText etSearch;
    private TextView tvSearchInfo;
    private PoiSearch.Query query;
    private PoiSearch poiSearch;
    private String TAG = "Z-Search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btnSearch = (Button) findViewById(R.id.btn_search);
        etSearch = (EditText) findViewById(R.id.et_search);
        tvSearchInfo = (TextView) findViewById(R.id.tv_search_info);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** 关键字检索POI **/

                String text = etSearch.getText().toString();
                query = new PoiSearch.Query(text, "", "宁波市");
                //keyWord表示搜索字符串，
                //第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
                //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
                query.setPageSize(10);// 设置每页最多返回多少条poiitem
                query.setPageNum(1);//设置查询页码
                query.setCityLimit(true);
                poiSearch = new PoiSearch(SearchActivity.this, query);
                poiSearch.setOnPoiSearchListener(SearchActivity.this);
                poiSearch.searchPOIAsyn();

                /** 输入内容自动提示 **/

                //第二个参数传入null或者“”代表在全国进行检索，否则按照传入的city进行检索
                InputtipsQuery inputquery = new InputtipsQuery(text, "宁波市");
                inputquery.setCityLimit(true);//限制在当前城市
                Inputtips inputTips = new Inputtips(SearchActivity.this, inputquery);
                inputTips.setInputtipsListener(SearchActivity.this);
                inputTips.requestInputtipsAsyn();
            }
        });
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        ArrayList<PoiItem> pois = poiResult.getPois();
        for (PoiItem poiItem : pois) {
            Log.i(TAG, "onPoiSearched:"+poiItem.getCityName()+" "+poiItem.getAdName()+" "+poiItem.getTitle());
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {
        List<SubPoiItem> pois = poiItem.getSubPois();
        for (SubPoiItem subPoiItem : pois) {
            Log.i(TAG, "onPoiItemSearched"+subPoiItem.getSubName());
        }
    }

    @Override
    public void onGetInputtips(List<Tip> list, int i) {
        for (Tip tip : list) {
            Log.i(TAG, "onGetInputtips"+tip.getAddress()+" "+tip.getName());
        }
    }
}
