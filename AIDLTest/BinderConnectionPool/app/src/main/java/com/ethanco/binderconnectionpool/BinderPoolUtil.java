package com.ethanco.binderconnectionpool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.concurrent.CountDownLatch;

//Binder连接池的具体实现
//1.单例，可在Application中初始化，提升程序体验
//2.BinderPool中有断线重连的机制，当远程服务以外终止，BinderPool会重新建立连接。 [如果业务模块中的Binder调用出现了异常，也需要手动去重写获取最新的Binder对象]
public class BinderPoolUtil {
    private static final String TAG = "BinderPool";
    public static final int BINDER_NONE = -1;

    private Context mContext;
    private IBinderPool mBinderPool;
    private static volatile BinderPoolUtil sInstance;
    private CountDownLatch mConnectBinderPoolCountDownLatch;

    private BinderPoolUtil(Context context) {
        mContext = context.getApplicationContext();
        connectBinderPoolService();
    }

    public static BinderPoolUtil getInsance(Context context) {
        if (sInstance == null) {
            synchronized (BinderPoolUtil.class) {
                if (sInstance == null) {
                    sInstance = new BinderPoolUtil(context);
                }
            }
        }
        return sInstance;
    }

    private synchronized void connectBinderPoolService() {
        //CountDownLatch 将bindService这一异步操作转换成了同步操作
        mConnectBinderPoolCountDownLatch = new CountDownLatch(1);
        Intent service = new Intent(mContext, BinderPoolService.class);
        mContext.bindService(service, mBinderPoolConnection,
                Context.BIND_AUTO_CREATE);
        try {
            mConnectBinderPoolCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * query binder by binderCode from binder pool
     * 
     * @param binderCode
     *            the unique token of binder
     * @return binder who's token is binderCode<br>
     *         return null when not found or BinderPoolService died.
     */
    public IBinder queryBinder(int binderCode) {
        IBinder binder = null;
        try {
            if (mBinderPool != null) {
                binder = mBinderPool.queryBinder(binderCode);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return binder;
    }

    private ServiceConnection mBinderPoolConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // ignored.
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinderPool = IBinderPool.Stub.asInterface(service);
            try {
                mBinderPool.asBinder().linkToDeath(mBinderPoolDeathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mConnectBinderPoolCountDownLatch.countDown();
        }
    };

    private IBinder.DeathRecipient mBinderPoolDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.w(TAG, "binder died.");
            mBinderPool.asBinder().unlinkToDeath(mBinderPoolDeathRecipient, 0);
            mBinderPool = null;
            connectBinderPoolService();
        }
    };
}
