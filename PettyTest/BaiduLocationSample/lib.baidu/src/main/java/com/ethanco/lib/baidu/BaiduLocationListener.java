package com.ethanco.lib.baidu;

import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;

import java.util.ArrayList;
import java.util.List;

/**
 * 定位回调
 *
 * @author EthanCo
 * @since 2016/12/19
 */
public class BaiduLocationListener implements BDLocationListener {

    private List<ILocationListener> locationListeners = new ArrayList<>();
    private boolean isLocationOnce = true; //是否只定位一次

    public BaiduLocationListener(boolean isLocationOnce) {
        this.isLocationOnce = isLocationOnce;
    }

    public void addLocationListener(ILocationListener locationListener) {
        if (!locationListeners.contains(locationListener)) {
            locationListeners.add(locationListener);
        }
    }

    public void removeLocationListener(ILocationListener locationListener) {
        if (locationListeners.contains(locationListener)) {
            locationListeners.remove(locationListener);
        }
    }

    @Override
    public void onReceiveLocation(BDLocation location) {
        BaiduFacede.setLocation(location);

        //Receive Location
        StringBuffer sb = new StringBuffer(256);
        sb.append("time : ");
        sb.append(location.getTime());
        sb.append("\nerror code : ");
        sb.append(location.getLocType());
        sb.append("\nlatitude : ");
        sb.append(location.getLatitude());
        sb.append("\nlontitude : ");
        sb.append(location.getLongitude());
        sb.append("\nradius : ");
        sb.append(location.getRadius());
        if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
            sb.append("\nspeed : ");
            sb.append(location.getSpeed());// 单位：公里每小时
            sb.append("\nsatellite : ");
            sb.append(location.getSatelliteNumber());
            sb.append("\nheight : ");
            sb.append(location.getAltitude());// 单位：米
            sb.append("\ndirection : ");
            sb.append(location.getDirection());// 单位度
            sb.append("\naddr : ");
            sb.append(location.getAddrStr());
            sb.append("\nCountryCode : ");// 国家码
            sb.append(location.getCountryCode());
            sb.append("\nCountry : ");// 国家名称
            sb.append(location.getCountry());
            sb.append("\nprovince:");
            sb.append(location.getProvince());
            sb.append("\ncity:");
            sb.append(location.getCity());
            sb.append("\ncitycode : ");// 城市编码
            sb.append(location.getCityCode());
            sb.append("\ndescribe : ");
            sb.append("gps定位成功");

            locationSuccess(location);
        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
            sb.append("\naddr : ");
            sb.append(location.getAddrStr());
            sb.append("\nCountryCode : ");// 国家码
            sb.append(location.getCountryCode());
            sb.append("\nCountry : ");// 国家名称
            sb.append(location.getCountry());
            sb.append("\nprovince:");
            sb.append(location.getProvince());
            sb.append("\ncity:");
            sb.append(location.getCity());
            sb.append("\ncitycode : ");// 城市编码
            sb.append(location.getCityCode());
            //运营商信息
            sb.append("\noperationers : ");
            sb.append(location.getOperators());
            sb.append("\ndescribe : ");
            sb.append("网络定位成功");

            locationSuccess(location);
        } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
            sb.append("\ndescribe : ");
            sb.append("离线定位成功，离线定位结果也是有效的");

            locationSuccess(location);
        } else {
            if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            } else {
                sb.append("定位失败，未知原因");
            }

            for (ILocationListener locationListener : locationListeners) {
                locationListener.onLocationFaild(location.getLocType());
            }
        }
        sb.append("\nlocationdescribe : ");
        sb.append(location.getLocationDescribe());// 位置语义化信息
        List<Poi> list = location.getPoiList();// POI数据
        if (list != null) {
            sb.append("\npoilist size = : ");
            sb.append(list.size());
            for (Poi p : list) {
                sb.append("\npoi= : ");
                sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
            }
        }
        Log.i("BaiduLocationApiDem", sb.toString());
    }

    private void locationSuccess(BDLocation location) {
        Log.i("BaiduLocationApiDem", "locationSuccess");
        for (ILocationListener locationListener : locationListeners) {
            locationListener.onLocationSuccess(location);
        }
        BaiduFacede.getmLocationClient().stop();
    }
}
