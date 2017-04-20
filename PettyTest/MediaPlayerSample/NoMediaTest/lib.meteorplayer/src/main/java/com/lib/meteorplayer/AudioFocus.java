package com.lib.meteorplayer;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;

/**
 * 声音焦点操作类
 *
 * @author EthanCo
 * @since 2017/4/20
 */

public class AudioFocus {
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener;
    private Context context;

    public AudioFocus(AudioManager.OnAudioFocusChangeListener listener, Context context) {
        this.audioFocusChangeListener = listener;
        this.context = context;
    }

    //要请求音频焦点
    public void requestFocus() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.ECLAIR_MR1) {
            return;
        }
        if (audioManager == null)
            audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (audioManager != null) {
            LogUtil.i("Request audio focus");
            int ret = audioManager.requestAudioFocus(audioFocusChangeListener,
                    AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
            if (ret != AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                LogUtil.i("request audio focus fail. " + ret);
            }
        }
    }

    //放弃焦点 调用这个，上一个获得音频焦点的播放设备会继续进行播放
    public void abandonFocus() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.ECLAIR_MR1) {
            return;
        }
        if (audioManager != null) {
            LogUtil.i("Abandon audio focus");
            audioManager.abandonAudioFocus(audioFocusChangeListener);
            audioManager = null;
        }
    }
}
