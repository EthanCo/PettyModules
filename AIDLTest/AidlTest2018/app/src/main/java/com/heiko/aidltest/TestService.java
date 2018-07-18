package com.heiko.aidltest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.heiko.aidltest.bean.Person;

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
            Log.i(TAG, "name:" + person.getName() + " age:" + person.getAge()+" height:"+person.getHeight());
        }
    }
}
