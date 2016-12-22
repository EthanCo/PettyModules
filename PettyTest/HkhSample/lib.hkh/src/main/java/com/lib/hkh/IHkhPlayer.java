package com.lib.hkh;

import android.content.Context;
import android.support.annotation.Nullable;

import com.ximalaya.ting.android.opensdk.model.live.radio.Radio;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.model.track.TrackList;
import com.ximalaya.ting.android.opensdk.player.service.IXmPlayerStatusListener;

import java.util.List;

/**
 * 播放器 接口
 * 接口含义 详情看喜马拉雅SDK 4.6 播放器控制
 *
 * @author EthanCo
 * @since 2016/12/9
 */

public interface IHkhPlayer {
    void init(Context context);

    void release();

    void play();

    void play(int index);

    void playRadio(Radio radio);

    void pause();

    void playNext();

    void playPre();

    boolean isPlaying();

    int getCurrPlayType();

    int getPlayCurrPositon();

    List<Track> getPlayList();

    //void playList(@Nullable CommonTrackList list, int start);

    void playList(@Nullable TrackList list, int start);

    void addPlayerStatusListener(IXmPlayerStatusListener l);

    void removePlayerStatusListener(IXmPlayerStatusListener l);

    boolean containPlayerStatusListener(IXmPlayerStatusListener l);

    boolean hasNextSound();

    boolean hasPreSound();
}
