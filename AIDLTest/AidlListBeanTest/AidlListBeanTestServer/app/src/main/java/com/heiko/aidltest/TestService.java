package com.heiko.aidltest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.heiko.aidltest.bean.Person;
import com.heiko.aidltest.bean.RemotePlayList;
import com.heiko.aidltest.bean.RemoteSong;

import java.util.ArrayList;
import java.util.List;

/**
 * TestService
 *
 * @author EthanCo
 * @since 2018/7/18
 */
public class TestService extends Service {
    private static final String TAG = "Z-TestService";

    private MyBinder myBinder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public class MyBinder extends IMyAidlInterface.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            Log.i(TAG, "basicTypes anInt:" + anInt + " aLong:" + aLong + " aBoolean:" + aBoolean + " aFloat:" + aFloat + " aDouble:" + aDouble + " aString:" + aString);
        }

        @Override
        public void operPerson(Person person) throws RemoteException {
            Log.i(TAG, "name:" + person.getName() + " age:" + person.getAge() + " height:" + person.getHeight());
        }

        @Override
        public RemotePlayList getLocalList() throws RemoteException {
            List<RemoteSong> songs = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                RemoteSong song = new RemoteSong();
                song.setId(i);
                song.setDuration(1000 + i);
                song.setTitle("标题" + i);

                song.setFileName("文件名" + i);
//                song.setProgress(i);
//                song.setType(i % 2);

                songs.add(song);
            }

            RemotePlayList playList = new RemotePlayList();
            playList.setId(123);
            playList.setName("主歌单");
            playList.setSongs(songs);
            return playList;
        }
    }
}
