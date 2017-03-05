//package com.ethanco.simpleframe.view.adapter;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.ethanco.simpleframe.bean.Weather;
//import com.ethanco.simpleframe.frame.view.adapter.SimpleAdapter;
//
//import java.util.List;
//
///**
// * Created by EthanCo on 2016/4/4.
// */
//public class WeatherAdapter extends SimpleAdapter<WeatherAdapter.ItemViewHodler> {
//
//    private List<Weather.ResultEntity.DataEntity.WeatherEntity> weatherList;
//
//    public WeatherAdapter(List<Weather.ResultEntity.DataEntity.WeatherEntity> weatherList) {
//        this.weatherList = weatherList;
//    }
//
//
//    @Override
//    public ItemViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(ItemViewHodler holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return weatherList.size();
//    }
//
//    class ItemViewHodler extends RecyclerView.ViewHolder {
//
//        public ItemViewHodler(View itemView) {
//            super(itemView);
//        }
//    }
//}
