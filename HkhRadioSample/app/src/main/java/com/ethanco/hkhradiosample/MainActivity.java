package com.ethanco.hkhradiosample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.live.radio.Radio;
import com.ximalaya.ting.android.opensdk.model.live.radio.RadioList;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.model.track.TrackHotList;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private XmPlayerManager playerManager;
    private List<Radio> radios;
    private List<Track> trancks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CommonRequest.getInstanse().init(MainActivity.this, "88b4ef2491ef9f405df7ceef7172c566");
        playerManager = XmPlayerManager.getInstance(MainActivity.this);
        playerManager.init();
        getRadios();
        getAlbumList();

        findViewById(R.id.btn_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Radio radio = radios.get(2);
                L.i("播放" + radio.getRadioName() + " 正在播放:" + radio.getProgramName());
                playerManager.playRadio(radio);
                //playerManager.playList(trancks, 0);
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
                radios = radioList.getRadios();
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

    private void getAlbumList() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(DTransferConstants.CATEGORY_ID, "0");
        //map.put(DTransferConstants.TAG_NAME, mTagName);
        //map.put(DTransferConstants.PAGE, 1);
        CommonRequest.getHotTracks(map, new IDataCallBack<TrackHotList>() {
            @Override
            public void onSuccess(TrackHotList trackHotList) {
                trancks = trackHotList.getTracks();
            }

            @Override
            public void onError(int i, String s) {
                L.e(s);
            }
        });
    }
}
