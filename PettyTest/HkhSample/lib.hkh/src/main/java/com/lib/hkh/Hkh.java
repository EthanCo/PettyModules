package com.lib.hkh;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.lib.hkh.security.AES;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.model.live.radio.Radio;
import com.ximalaya.ting.android.opensdk.model.track.CommonTrackList;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.model.track.TrackList;
import com.ximalaya.ting.android.opensdk.util.ModelUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 门面
 *
 * @author EthanCo
 * @since 2016/11/23
 */

public class Hkh {
    public static final String PREFIX = "/'|][prefix*0--====";
    public static final String AES_KEY = "M0Tf/1Va#Gamjxjy";
    private static RadioPlayer player;
    private static Context context;

    public static void init(@NonNull Context _context) {
        context = _context;
        try {
            ApplicationInfo appInfo = _context.getPackageManager()
                    .getApplicationInfo(_context.getPackageName(),
                            PackageManager.GET_META_DATA);
            String appSecret = appInfo.metaData.getString("appsecret");
            if (TextUtils.isEmpty(appSecret)) {
                throw new IllegalArgumentException("metaData appsecret 为空，请在AndroidManifest中填写metaData");
            }
            String appSecretDec = AES.Decrypt(appSecret, AES_KEY);
            appSecretDec = appSecretDec.replace(PREFIX, "");

            CommonRequest.getInstanse().init(_context, appSecretDec);

            //播放器初始化
            player = new RadioPlayer(_context);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Context getContext() {
        return context;
    }

    public static IHkhPlayer getPlayer() {
        return player;
    }

    //Radio列表转换为TrackList
    public static TrackList radioToTrack(List<Radio> radios) {
        TrackList trackList = new TrackList();
        ArrayList tracks = new ArrayList();
        for (Radio radio : radios) {
            tracks.add(radioToTrack(radio, false));
        }
        trackList.setTracks(tracks);
        return trackList;
    }

    //Radio列表转换为CommonTrackList
    public static CommonTrackList radioToCommonTrack(List<Radio> radios) {
        CommonTrackList commonTrackList = new CommonTrackList();
        ArrayList trackList = new ArrayList();
        for (Radio radio : radios) {
            trackList.add(radioToTrack(radio, false));
        }
        commonTrackList.setTracks(trackList);
        commonTrackList.setTotalCount(1);
        commonTrackList.setTotalPage(1);
        return commonTrackList;
    }

    //Radio转化为Track
    public static Track radioToTrack(Radio radio, boolean isActivity) {
        return ModelUtil.radioToTrack(radio, isActivity);
    }
}
