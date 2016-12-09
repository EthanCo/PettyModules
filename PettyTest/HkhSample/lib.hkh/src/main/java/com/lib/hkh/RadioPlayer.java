package com.lib.hkh;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ximalaya.ting.android.opensdk.model.track.CommonTrackList;
import com.ximalaya.ting.android.opensdk.model.track.TrackList;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;
import com.ximalaya.ting.android.opensdk.player.service.IXmPlayerStatusListener;

/**
 * 喜马拉雅 电台播放器
 *
 * @author EthanCo
 * @since 2016/12/9
 */

public class RadioPlayer implements IHkhPlayer {

    public static final String TAG = "Z-RadioPlayer";
    private XmPlayerManager playerManager;
    private int index;

    protected RadioPlayer(Context context) {
        init(context);
    }

    @Override
    public void init(Context context) {
        playerManager = XmPlayerManager.getInstance(context);
        playerManager.init();
    }

    @Override
    public void release() {
        playerManager.release();
    }

    @Override
    public void play() {
        playerManager.play();
    }

    @Override
    public void play(int index) {
        this.index = index;
        playerManager.play(index);
    }

    @Override
    public void pause() {
        playerManager.pause();
    }

    @Override
    public void playNext() {
        if (hasNextSound()) {
            index++;
            playerManager.play(index);
        }
    }

    @Override
    public void playPre() {
        if (hasPreSound()) {
            index--;
            playerManager.play(index);
        }
    }

    @Override
    public boolean isPlaying() {
        return playerManager.isPlaying();
    }

    @Override
    public void playList(@Nullable CommonTrackList list, int start) {
        this.index = start;
        playerManager.playList(list, start);
    }

    @Override
    public void playList(@Nullable TrackList list, int start) {
        this.index = start;
        playerManager.playList(list, start);
    }

    @Override
    public void addPlayerStatusListener(IXmPlayerStatusListener l) {
        playerManager.addPlayerStatusListener(l);
    }

    @Override
    public void removePlayerStatusListener(IXmPlayerStatusListener l) {
        playerManager.removePlayerStatusListener(l);
    }

    @Override
    public boolean containPlayerStatusListener(IXmPlayerStatusListener l) {
        return playerManager.containPlayerStatusListener(l);
    }

    @Override
    public boolean hasNextSound() {
        int nextIndex = index + 1;
        int size = playerManager.getPlayList().size();
        boolean result = nextIndex <= size - 1;
        Log.i(TAG, "hasNextSound size:" + size + " nextIndex=" + nextIndex + " result:" + result);
        return result;
    }

    @Override
    public boolean hasPreSound() {
        int preIndex = index - 1;
        int size = playerManager.getPlayList().size();
        Log.i(TAG, "hasPreSound size:" + size + " preIndex=" + preIndex);
        if (size == 0) {
            return false;
        }
        return preIndex >= 0;
    }
}
