package com.ethanco.nomediatest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lib.meteorplayer.MeteorPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPlay;
    private Button btnPause;
    private EditText etFileName;
    private MeteorPlayer meteorPlayer;
    private String TAG = "Z-NoMedia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFileName = (EditText) findViewById(R.id.et_filename);
        btnPlay = (Button) findViewById(R.id.btn_play);
        btnPause = (Button) findViewById(R.id.btn_pause);

        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_play:
                String path = NoMediaUtil.getNomedia(this).getPath() + File.separator + etFileName.getText().toString();
                File music = new File(path);
                if (music.exists()) {
                    Toast.makeText(getApplicationContext(), "开始播放", Toast.LENGTH_SHORT).show();
                    if (meteorPlayer == null) { //懒加载
                        meteorPlayer = new MeteorPlayer(this);
                    }
                    meteorPlayer.play(music.getPath());
                } else {
                    Toast.makeText(MainActivity.this, "文件不存在", Toast.LENGTH_SHORT).show();
                }

                List<String> specialSongs = new ArrayList<>();
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
                }
                break;
            case R.id.btn_pause:
                if (meteorPlayer != null) {
                    meteorPlayer.pause();
                }
                break;
            default:
        }
    }
}
