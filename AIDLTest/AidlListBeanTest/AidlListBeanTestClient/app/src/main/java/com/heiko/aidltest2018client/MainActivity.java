package com.heiko.aidltest2018client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.heiko.aidltest.IMyAidlInterface;
import com.heiko.aidltest.bean.RemotePlayList;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Z-AidlClient";
    private Button btnLocalList;
    private IMyAidlInterface mAidl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = new Intent();
        intent.setAction("com.heiko.TestService");
        intent.addCategory(Intent.CATEGORY_DEFAULT);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent = createExplicitFromImplicitIntent(this, intent);
        }
        
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mAidl = IMyAidlInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);

        btnLocalList = findViewById(R.id.btn_local_list);
        btnLocalList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    RemotePlayList localList = mAidl.getLocalList();
                    LogUtil.i(TAG,localList.toString());
                    LogUtil.i(TAG,"songs.size:" + localList.getSongs().size());
                    LogUtil.i(TAG,"songs:" + new Gson().toJson(localList.getSongs()));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);

        // Set the component to be explicit
        explicitIntent.setComponent(component);

        return explicitIntent;
    }
}
