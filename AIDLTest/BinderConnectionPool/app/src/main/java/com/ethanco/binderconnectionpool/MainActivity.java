package com.ethanco.binderconnectionpool;

import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

//将每个业务模块的Binder请求统一转发到远程Service中去执行，从而避免重复创建Service的过程
//将所有的AIDL放在同一个Service中去管理。
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Z-Main";
    private ISecurityCenter mSecurityCenter;
    private ICompute mCompute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //为什么用子线程?
        //CountDownLatch 将bindService这一异步操作转换成了同步操作，这就意味着Binder方法的调用过程可能是耗时的，因此不建议在主线程执行。
        new Thread() {
            @Override
            public void run() {
                super.run();

                BinderPoolUtil binderPool = BinderPoolUtil.getInsance(MainActivity.this);
                IBinder securityBinder = binderPool.queryBinder(BinderPoolService.BINDER_SECURITY_CENTER);

                mSecurityCenter = (ISecurityCenter) SecurityCenterImpl.asInterface(securityBinder);
                Log.i(TAG, "visit ISecurityCenter");
                String msg = "hello world-安卓";
                Log.i(TAG, "content:" + msg);
                try {
                    String password = mSecurityCenter.encrypt(msg);
                    Log.i(TAG, "encrypt:" + password);
                    Log.i(TAG, "decrypt:" + mSecurityCenter.decrypt(password));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Log.i(TAG, "visit ICompute");
                IBinder computeBinder = binderPool.queryBinder(BinderPoolService.BINDER_COMPUTE);
                mCompute = ComputeImpl.asInterface(computeBinder);
                try {
                    Log.i(TAG, "3+5=" + mCompute.add(3, 5));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
