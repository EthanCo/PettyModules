package com.ethanco.mediatest;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/*相关资料看
http://blog.csdn.net/shuaicike/article/details/39930823
http://blog.csdn.net/sundayzhong/article/details/52128226*/
public class MainActivity extends AppCompatActivity {

    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener = null;
    private AudioManager audioManager;
    private MediaPlayer mediaPlayer;
    private static final String TAG = "Z-Main";
    //TODO URL可能失效，如果失效了，要用其他的URL
    private static final String URL = "http://sinacloud.net/leisurealarmclock/%E5%A5%BD%E5%90%AC%E7%9A%84/Let%20Me%20Hear.mp3?KID=sina,2nq6ps3pIljbsxP2SfXV&Expires=1492683689&ssig=NduxI52tXd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Build.VERSION.SDK_INT表示当前SDK的版本，Build.VERSION_CODES.ECLAIR_MR1为SDK 7版本 ，
        //因为AudioManager.OnAudioFocusChangeListener在SDK8版本开始才有。
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ECLAIR_MR1) {
            mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        //失去焦点之后的操作
                        mediaPlayer.pause();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        //获得焦点之后的操作
                        play();
                    }
                }
            };
        }

        mediaPlayer = MediaPlayer.create(getApplication(), Uri.parse(URL));

        findViewById(R.id.btn_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
            }
        });

        findViewById(R.id.btn_pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
                abandonAudioFocus();
            }
        });

        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Stop之后，需要先prepare之后，才可以再调用start进行播放
                mediaPlayer.stop();
                abandonAudioFocus();
            }
        });

        findViewById(R.id.btn_start_second_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }

    private void play() {
        requestAudioFocus();
        mediaPlayer.start();
    }


    //要请求音频焦点，你必须从AudioManager mAudioMgr调用requestAudioFocus()
    private void requestAudioFocus() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.ECLAIR_MR1) {
            return;
        }
        if (audioManager == null)
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (audioManager != null) {
            Log.i(TAG, "Request audio focus");
            int ret = audioManager.requestAudioFocus(mAudioFocusChangeListener,
                    AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
            if (ret != AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                Log.i(TAG, "request audio focus fail. " + ret);
            }
        }
    }

    //放弃焦点 调用这个，上一个获得音频焦点的播放设备会继续进行播放
    private void abandonAudioFocus() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.ECLAIR_MR1) {
            return;
        }
        if (audioManager != null) {
            Log.i(TAG, "Abandon audio focus");
            audioManager.abandonAudioFocus(mAudioFocusChangeListener);
            audioManager = null;
        }
    }
}
