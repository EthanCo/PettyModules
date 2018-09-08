package com.heiko.aidltest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.heiko.aidltest.bean.Person;
import com.heiko.aidltest.bean.RemotePlayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Z-AidlTest";
    private Button btnBasicTypes;
    private IMyAidlInterface mAidl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, TestService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mAidl = IMyAidlInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);

        btnBasicTypes = findViewById(R.id.btn_basicTypes);
        btnBasicTypes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mAidl.basicTypes(1, 2, true, 4, 5, "6");
                    Person person = new Person();
                    person.setName("Heiko");
                    person.setAge(20);
                    person.setHeight(171);
                    mAidl.operPerson(person);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        Button btnGetLocalList = findViewById(R.id.btn_get_local_list);
        btnGetLocalList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    RemotePlayList localList = mAidl.getLocalList();
                    Log.i(TAG, localList.toString());
                    Log.i(TAG, "songs.size:" + localList.getSongs().size());
                    Log.i(TAG, "songs:"+new Gson().toJson(localList.getSongs()));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
