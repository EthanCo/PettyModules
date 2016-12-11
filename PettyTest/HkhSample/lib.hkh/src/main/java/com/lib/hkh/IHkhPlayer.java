package com.lib.hkh;

import android.content.Context;
import android.support.annotation.Nullable;

import com.ximalaya.ting.android.opensdk.model.track.CommonTrackList;
import com.ximalaya.ting.android.opensdk.model.track.TrackList;
import com.ximalaya.ting.android.opensdk.player.service.IXmPlayerStatusListener;

/**
 * 播放器 接口
 *
 * @author EthanCo
 * @since 2016/12/9
 */

public interface IHkhPlayer {
    void init(Context context);

    void release();

    void play();

    void play(int index);

    void pause();

    void playNext();

    void playPre();

    boolean isPlaying();

    void playList(@Nullable CommonTrackList list, int start);

    void playList(@Nullable TrackList list, int start);

    void addPlayerStatusListener(IXmPlayerStatusListener l);

    void removePlayerStatusListener(IXmPlayerStatusListener l);

    boolean containPlayerStatusListener(IXmPlayerStatusListener l);

    boolean hasNextSound();

    boolean hasPreSound();
}
