package com.lib.hkh;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ximalaya.ting.android.opensdk.model.live.radio.Radio;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.model.track.TrackList;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;
import com.ximalaya.ting.android.opensdk.player.service.IXmPlayerStatusListener;

import java.util.ArrayList;
import java.util.List;

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
    private List<ITrackChangeListener> radioChangeListeners = new ArrayList<>();

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
        onRadioChange();
    }

    @Override
    public void play(int index) {
        this.index = index;
        playerManager.play(index);
        onRadioChange();
    }

    @Override
    public void playRadio(Radio radio) {
        this.index = 0;
        playerManager.playRadio(radio);
        onRadioChange();
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
            onRadioChange();
        }
    }

    @Override
    public void playPre() {
        if (hasPreSound()) {
            index--;
            playerManager.play(index);
            onRadioChange();
        }
    }

    @Override
    public boolean isPlaying() {
        return playerManager.isPlaying();
    }

    @Override
    public int getCurrPlayType() {
        return playerManager.getCurrPlayType();
    }

    @Override
    public int getPlayCurrPositon() {
        return index;
    }

    @Override
    public List<Track> getPlayList() {
        return playerManager.getPlayList();
    }

    @Override
    public void playList(@Nullable TrackList list, int start) {
        this.index = start;
        playerManager.playList(list, start);
        onRadioChange();
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
    public void addTrackChangeListener(ITrackChangeListener l) {
        if (!radioChangeListeners.contains(l)) {
            radioChangeListeners.add(l);
        }
    }

    @Override
    public void removeTrackChangeListener(ITrackChangeListener l) {
        if (radioChangeListeners.contains(l)) {
            radioChangeListeners.remove(l);
        }
    }

    @Override
    public boolean containPlayerStatusListener(IXmPlayerStatusListener l) {
        return playerManager.containPlayerStatusListener(l);
    }

    private void onRadioChange() {
        for (ITrackChangeListener radioChangeListener : radioChangeListeners) {
            radioChangeListener.onTrackChange();
        }
    }

    @Override
    public boolean hasNextSound() {
        int nextIndex = index + 1;
        int size = getPlayList().size();
        boolean result = nextIndex <= size - 1;
        Log.i(TAG, "hasNextSound size:" + size + " nextIndex=" + nextIndex + " result:" + result);
        return result;
    }

    @Override
    public boolean hasPreSound() {
        int preIndex = index - 1;
        int size = getPlayList().size();
        Log.i(TAG, "hasPreSound size:" + size + " preIndex=" + preIndex);
        if (size == 0) {
            return false;
        }
        return preIndex >= 0;
    }
}
