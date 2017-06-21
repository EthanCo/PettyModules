package com.ethanco.nomediatest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lib.meteorplayer.MeteorPlayer;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPlay;
    private Button btnPlay2;
    private Button btnPause;
    private Button btnStop;
    private EditText etFileName;
    private MeteorPlayer meteorPlayer;
    private Button btnPlayAssets;
    private EditText etAssetsFileName;
    private String TAG = "Z-NoMedia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFileName = (EditText) findViewById(R.id.et_filename);
        etAssetsFileName = (EditText) findViewById(R.id.et_assets_filename);
        btnPlay = (Button) findViewById(R.id.btn_play);
        btnPlay2 = (Button) findViewById(R.id.btn_play2);
        btnPlayAssets = (Button) findViewById(R.id.btn_play_assets);
        btnPause = (Button) findViewById(R.id.btn_pause);
        btnStop = (Button) findViewById(R.id.btn_stop);

        btnPlay.setOnClickListener(this);
        btnPlay2.setOnClickListener(this);
        btnPlayAssets.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_play:
                play(true);

                /*List<String> specialSongs = new ArrayList<>();
                String noMediaDir = NoMediaUtil.getNomedia(this).getPath();
                File noMediaFile = new File(noMediaDir);
                if (noMediaFile.isDirectory()) {
                    File[] files = noMediaFile.listFiles();
                    for (File music1 : files) {
                        if (music1 == null) continue;
                        if (meteorPlayer.isSupportGenre(music1)) {
                            specialSongs.add(music1.getName());
                        }
                    }
                }*/
                break;
            case R.id.btn_play2:
                play(false);
                break;
            case R.id.btn_play_assets:
                String fileName = etAssetsFileName.getText().toString();
                //String fileName = "alarms/Platinum.ogg"; //多级目录也支持
                initMeteorPlayer();
                meteorPlayer.playAssets(fileName, false);
                break;
            case R.id.btn_pause:
                if (meteorPlayer != null) {
                    meteorPlayer.pause();
                }
                break;
            case R.id.btn_stop:
                if (meteorPlayer != null) {
                    meteorPlayer.stop();
                }
            default:
        }
    }

    private void play(boolean recordLast) {
        String path = NoMediaUtil.getNomedia(this).getPath() + File.separator + etFileName.getText().toString();
        File music = new File(path);
        if (music.exists()) {
            Toast.makeText(getApplicationContext(), "开始播放", Toast.LENGTH_SHORT).show();
            initMeteorPlayer();
            meteorPlayer.play(music.getPath(), recordLast);
        } else {
            Toast.makeText(MainActivity.this, "文件不存在", Toast.LENGTH_SHORT).show();
        }
    }

    private void initMeteorPlayer() {
        if (meteorPlayer == null) { //懒加载
            meteorPlayer = new MeteorPlayer(this);
        }
    }
}
