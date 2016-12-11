package com.ethanco.hkhsample;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.ethanco.hkhsample.databinding.ActivityMainBinding;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.PlayableModel;
import com.ximalaya.ting.android.opensdk.model.category.Category;
import com.ximalaya.ting.android.opensdk.model.category.CategoryList;
import com.ximalaya.ting.android.opensdk.model.live.program.Program;
import com.ximalaya.ting.android.opensdk.model.live.program.ProgramList;
import com.ximalaya.ting.android.opensdk.model.live.provinces.Province;
import com.ximalaya.ting.android.opensdk.model.live.provinces.ProvinceList;
import com.ximalaya.ting.android.opensdk.model.live.radio.Radio;
import com.ximalaya.ting.android.opensdk.model.live.radio.RadioCategory;
import com.ximalaya.ting.android.opensdk.model.live.radio.RadioCategoryList;
import com.ximalaya.ting.android.opensdk.model.live.radio.RadioList;
import com.ximalaya.ting.android.opensdk.model.live.radio.RadioListByCategory;
import com.ximalaya.ting.android.opensdk.model.tag.Tag;
import com.ximalaya.ting.android.opensdk.model.tag.TagList;
import com.ximalaya.ting.android.opensdk.model.track.CommonTrackList;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;
import com.ximalaya.ting.android.opensdk.player.receive.WireControlReceiver;
import com.ximalaya.ting.android.opensdk.player.service.IXmPlayerStatusListener;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerException;
import com.ximalaya.ting.android.opensdk.util.ModelUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<Radio> radios;
    private XmPlayerManager xmPlayerManager;
    private List<Program> programs;
    int index = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        method_3_2_1();
        method_3_2_2(1);
        getRankRadios();
        getRadios();
        getRadiosCategory();
        getRadiosByCategory();
        getProvinces();
        getProgram();

        xmPlayerManager = XmPlayerManager.getInstance(getApplication());
        xmPlayerManager.addPlayerStatusListener(new IXmPlayerStatusListener() {
            @Override
            public void onPlayStart() {
                L.i("开始播放");
            }

            @Override
            public void onPlayPause() {
                L.i("暂停播放");
            }

            @Override
            public void onPlayStop() {
                L.i("停止播放");
            }

            @Override
            public void onSoundPlayComplete() {
                L.i("播放完成");
            }

            @Override
            public void onSoundPrepared() {
                L.i("声音已准备好");
            }

            @Override
            public void onSoundSwitch(PlayableModel playableModel, PlayableModel playableModel1) {
                String originKind = "null";
                if (playableModel != null) {
                    originKind = playableModel.getKind();
                }
                String expectKind = "null";
                if (playableModel1 != null) {
                    expectKind = playableModel1.getKind();
                }
                L.i("切换了声音 原来:" + originKind + " 切换至: " + expectKind);
            }

            @Override
            public void onBufferingStart() {
                L.i("开始缓存");
            }

            @Override
            public void onBufferingStop() {
                L.i("缓存停止(结束)");
            }

            @Override
            public void onBufferProgress(int i) {
                L.i("缓存ing:" + i);
            }

            @Override
            public void onPlayProgress(int i, int i1) {
                L.i("播放进度 i:" + i + " i1:" + i1);
            }

            @Override
            public boolean onError(XmPlayerException e) {
                L.i("出现错误:" + e.getMessage());
                return false;
            }
        });

        binding.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                L.i("radios.size:" + radios.size());
                CommonTrackList commonTrackList = new CommonTrackList();
                ArrayList trackList = new ArrayList();
                for (Radio radio : radios) {
                    trackList.add(ModelUtil.radioToTrack(radio, false));
                }
                commonTrackList.setTracks(trackList);
                commonTrackList.setTotalCount(trackList.size());
                commonTrackList.setTotalPage(1);
                xmPlayerManager.playList(commonTrackList, index);
//                xmPlayerManager.playRadio(radios.get(3));
//                Radio radio = new Radio();
//                xmPlayerManager.playActivityRadio((Radio) programs);
            }
        });

        binding.btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xmPlayerManager.pause();
            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (xmPlayerManager.hasNextSound()) {
                    L.i("下一首" + (++index));
                    xmPlayerManager.play(index);
                    //xmPlayerManager.playNext();
                } else {
                    Toast.makeText(MainActivity.this, "已到最后", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (xmPlayerManager.hasPreSound()) {
                    L.i("上一首" + (--index));
                    xmPlayerManager.play(index);
                    //xmPlayerManager.playPre();
                } else {
                    Toast.makeText(MainActivity.this, "已到最前", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //3.2.1 获取喜马拉雅内容分类
    private void method_3_2_1() {
        Map<String, String> map = new HashMap<>();
        CommonRequest.getCategories(map, new IDataCallBack<CategoryList>() {
            @Override
            public void onSuccess(CategoryList response) {
                List<Category> categorys = response.getCategories();
                StringBuilder sb = new StringBuilder();
                for (Category category : categorys) {
                    sb.append(category.getCategoryName());
                    sb.append(",");
                }

                L.i(sb.toString());
            }

            @Override
            public void onError(int code, String message) {
                L.e(message);
            }
        });
    }

    /**
     * 3.2.2 获取专辑标签或者声音标签
     *
     * @param categoryId 分类 为0时表示热门分类
     */
    private void method_3_2_2(int categoryId) {
        Map<String, String> map = new HashMap<>();
        map.put(DTransferConstants.CATEGORY_ID, String.valueOf(categoryId));
        map.put(DTransferConstants.TYPE, String.valueOf(0));
        CommonRequest.getTags(map, new IDataCallBack<TagList>() {

            @Override
            public void onSuccess(TagList tagList) {
                List<Tag> tags = tagList.getTagList();
                StringBuilder sb = new StringBuilder();
                for (Tag tag : tags) {
                    sb.append(tag.getTagName());
                    sb.append(",");
                }

                L.i(sb.toString());
            }

            @Override
            public void onError(int code, String s) {
                L.e(s);
            }
        });
    }

    /**
     * 获取电台排行榜
     */
    private void getRankRadios() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(DTransferConstants.RADIO_COUNT, String.valueOf(9));
        CommonRequest.getRankRadios(map, new IDataCallBack<RadioList>() {
            @Override
            public void onSuccess(RadioList radioList) {
                radios = radioList.getRadios();
                StringBuilder sb = new StringBuilder();
                for (Radio radio : radios) {
                    sb.append(radio.getRadioName());
                    sb.append(",");
                }

                L.i(sb.toString());
            }

            @Override
            public void onError(int code, String s) {
                L.e(s);
            }
        });
    }

    private void getRadios() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(DTransferConstants.RADIOTYPE, String.valueOf(3));
        map.put(DTransferConstants.PROVINCECODE, String.valueOf(310000));
        map.put(DTransferConstants.PAGE, String.valueOf(1));
        CommonRequest.getRadios(map, new IDataCallBack<RadioList>() {
            @Override
            public void onSuccess(RadioList radioList) {
                List<Radio> radios = radioList.getRadios();
                StringBuilder sb = new StringBuilder();
                for (Radio radio : radios) {
                    sb.append(radio.getRadioName());
                    sb.append(",");
                }

                L.i(sb.toString());
            }

            @Override
            public void onError(int i, String s) {
                L.e(s);
            }
        });
    }

    private void getRadiosCategory() {
        Map<String, String> map = new HashMap<String, String>();
        CommonRequest.getRadioCategory(map, new IDataCallBack<RadioCategoryList>() {
            @Override
            public void onSuccess(RadioCategoryList categoryList) {
                List<RadioCategory> radioCategories = categoryList.getRadioCategories();
                StringBuilder sb = new StringBuilder();
                for (RadioCategory radioCategory : radioCategories) {
                    sb.append(radioCategory.getId() + ".");
                    sb.append(radioCategory.getRadioCategoryName());
                    sb.append(",");
                }
                L.i(sb.toString());
            }

            @Override
            public void onError(int code, String message) {
                L.e(message);
            }
        });
    }

    private void getRadiosByCategory() {
        Map<String, String> maps = new HashMap<String, String>();
        maps.put(DTransferConstants.RADIO_CATEGORY_ID, "1");
        maps.put(DTransferConstants.PAGE, String.valueOf(1));
        CommonRequest.getRadiosByCategory(maps, new IDataCallBack<RadioListByCategory>() {
            @Override
            public void onSuccess(RadioListByCategory object) {
                List<Radio> radios = object.getRadios();
                StringBuilder sb = new StringBuilder();
                for (Radio radio : radios) {
                    sb.append(radio.getRadioName());
                    sb.append(",");
                }

                L.i(sb.toString());
            }

            @Override
            public void onError(int code, String message) {
                L.e(message);
            }
        });
    }

    //3.3.1 获取直播省市列表
    private void getProvinces() {
        Map<String, String> map = new HashMap<>();
        CommonRequest.getProvinces(map, new IDataCallBack<ProvinceList>() {
            @Override
            public void onSuccess(ProvinceList provinceList) {
                List<Province> provinces = provinceList.getProvinceList();
                StringBuilder sb = new StringBuilder();
                for (Province province : provinces) {
                    sb.append(province.getProvinceName() + " Code:" + province.getProvinceCode());
                    sb.append(",");
                }
                L.i(sb.toString());
            }

            @Override
            public void onError(int i, String s) {
                L.e(s);
            }
        });
    }

    //3.3.4 获取直播节目详情
    private void getProgram() {
        Map<String, String> map = new HashMap<>();
        map.put(DTransferConstants.RADIOID, String.valueOf(61));
        CommonRequest.getProgram(map, new IDataCallBack<ProgramList>() {
            @Override
            public void onSuccess(ProgramList programList) {
                programs = programList.getmProgramList();
                StringBuilder sb = new StringBuilder();
                for (Program program : programs) {
                    sb.append("Id:" + program.getProgramId() + "Name:" + program.getProgramName());
                    sb.append(",");
                }
                L.i(sb.toString());
            }

            @Override
            public void onError(int i, String s) {
                L.e(s);
            }
        });
    }

    private void iiii(final Context context) {
        final MediaButtonReceiver wireControlReceiver = new MediaButtonReceiver();

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            MediaSession mSession = new MediaSession(context, "MusicService");
            mSession.setCallback(new MediaSession.Callback() {
                @Override
                public boolean onMediaButtonEvent(Intent mediaButtonIntent) {
                    wireControlReceiver.onReceive(context, mediaButtonIntent);
                    return super.onMediaButtonEvent(mediaButtonIntent);
                }
            });
            mSession.setFlags(MediaSession.FLAG_HANDLES_MEDIA_BUTTONS
                    | MediaSession.FLAG_HANDLES_TRANSPORT_CONTROLS);

            Intent mediaButtonIntent = new Intent(Intent.ACTION_MEDIA_BUTTON);
            ComponentName mediaButtonReceiverComponent = new ComponentName(context, WireControlReceiver.class);
            mediaButtonIntent.setComponent(mediaButtonReceiverComponent);
            PendingIntent mediaPendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 0, mediaButtonIntent, 0);
            mSession.setMediaButtonReceiver(mediaPendingIntent);

            AudioAttributes.Builder audioAttributesBuilder = new AudioAttributes.Builder();
            audioAttributesBuilder.setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC);
            mSession.setPlaybackToLocal(audioAttributesBuilder.build());

            mSession.setActive(true);

            PlaybackState state = new PlaybackState.Builder()
                    .setActions(
                            PlaybackState.ACTION_PLAY
                                    | PlaybackState.ACTION_PLAY_PAUSE
                                    | PlaybackState.ACTION_PLAY_FROM_MEDIA_ID
                                    | PlaybackState.ACTION_PAUSE
                                    | PlaybackState.ACTION_SKIP_TO_NEXT
                                    | PlaybackState.ACTION_SKIP_TO_PREVIOUS)
                    .setState(PlaybackState.STATE_PLAYING, 0, 1,
                            SystemClock.elapsedRealtime()).build();

            mSession.setPlaybackState(state);
        } else {
            //获得AudioManager对象
            AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            ComponentName mbCN = new ComponentName(getPackageName(), MediaButtonReceiver.class.getName());
            mAudioManager.registerMediaButtonEventReceiver(mbCN);
        }
    }
}
