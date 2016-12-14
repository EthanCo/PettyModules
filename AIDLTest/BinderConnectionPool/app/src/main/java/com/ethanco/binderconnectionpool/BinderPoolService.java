package com.ethanco.binderconnectionpool;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class BinderPoolService extends Service {

    public static final int BINDER_COMPUTE = 0;
    public static final int BINDER_SECURITY_CENTER = 1;

    private IBinder mBinderPool = new BinderPoolImpl();
    public static final String TAG = "Z-BinderPoolService";

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return mBinderPool;
    }

    public static class BinderPoolImpl extends IBinderPool.Stub {

        public BinderPoolImpl() {
            super();
        }

        @Override
        public IBinder queryBinder(int binderCode) throws RemoteException {
            IBinder binder = null;
            switch (binderCode) {
                case BINDER_SECURITY_CENTER: {
                    binder = new SecurityCenterImpl();
                    break;
                }
                case BINDER_COMPUTE: {
                    binder = new ComputeImpl();
                    break;
                }
                default:
                    break;
            }

            return binder;
        }
    }
}
