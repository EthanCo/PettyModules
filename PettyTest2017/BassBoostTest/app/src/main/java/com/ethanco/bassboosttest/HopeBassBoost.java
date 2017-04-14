package com.sdk.hopeplayer.utils;

import android.media.MediaPlayer;
import android.media.audiofx.BassBoost;
import android.support.annotation.IntRange;

/**
 * 高低音设置
 *
 * @author EthanCo
 * @since 2017/4/14
 */

public class HopeBassBoost {

    private BassBoost mBass;

    public HopeBassBoost(MediaPlayer mediaPlayer) {
        // 以MediaPlayer的AudioSessionId创建BassBoost
        // 相当于设置BassBoost负责控制该MediaPlayer
        mBass = new BassBoost(0, mediaPlayer.getAudioSessionId());
        // 设置启用重低音效果
        mBass.setEnabled(true);
    }

    public void setBoost(@IntRange(from = 0, to = 1000) int strength) {
        // 设置重低音的强度
        mBass.setStrength((short) strength);
    }

    public short getBoost() {
        return mBass.getRoundedStrength();
    }
}
