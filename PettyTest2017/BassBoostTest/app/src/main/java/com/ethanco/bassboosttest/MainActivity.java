package com.ethanco.bassboosttest;

import android.media.MediaPlayer;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Android设置重低音
 */
public class MainActivity extends AppCompatActivity {

    private MediaPlayer mPlayer;
    private BassBoost mBass;
    private ViewGroup layout;
    private static final String TAG = "Z-Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (ViewGroup) findViewById(R.id.layout);

        mPlayer = MediaPlayer.create(this, R.raw.test2);
        setupBassBoost();
        mPlayer.start();
    }

    /**
     * 初始化重低音控制器
     */
    private void setupBassBoost() {
        // 以MediaPlayer的AudioSessionId创建BassBoost
        // 相当于设置BassBoost负责控制该MediaPlayer
        mBass = new BassBoost(Integer.MAX_VALUE, mPlayer.getAudioSessionId());
        // 设置启用重低音效果
        mBass.setEnabled(true);
        TextView bbTitle = new TextView(this);
        bbTitle.setText("重低音：");
        layout.addView(bbTitle);
        // 使用SeekBar做为重低音的调整工具
        final SeekBar bar = new SeekBar(this);
        // 重低音的范围为0～1000
        bar.setMax(1000);
        bar.setProgress(0);
        // 为SeekBar的拖动事件设置事件监听器
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //progress = progress - 500;
                int progress = bar.getProgress();
                Log.i(TAG, "progress:" + progress);
                // 设置重低音的强度
                mBass.setStrength((short) progress);
            }
        });
        layout.addView(bar);

        boolean supported = mBass.getStrengthSupported();
        Log.i(TAG, "supported:" + supported);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBass.release();
    }
}
